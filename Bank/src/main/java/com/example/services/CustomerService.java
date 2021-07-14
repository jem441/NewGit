package com.example.services;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.FileIO;
//
//
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.exceptions.UserNameAlreadyExistsException;
import com.example.logging.Logging;
import com.example.models.Customer;
//import com.example.models.User;

public class CustomerService {

	private String file;
	private FileIO<Customer> io;
	
	public CustomerService(String file) {
		this.file = file;
		this.io = new FileIO<Customer>(file);
	}

	public Customer signUp(String firstName, String lastName, String password) {
		ArrayList<Customer> customers;
		
		try {
			customers = io.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Creating a blank users array");
			customers = new ArrayList<Customer>();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Customer a = new Customer(firstName, lastName, password);
		
		//this checks to see if the users username is already stored
		for(int i=0; i<customers.size(); i++) {
			if(customers.get(i).getUsername().equals(a.getUsername())) {
				Logging.logger.warn("Username created that already exists in the database");
				throw new UserNameAlreadyExistsException();
			}
		}
		
		customers.add(a);
		io.writeObject(customers);
		return a;
	}
	
	public Customer login(String username, String password) throws InvalidCredentialsException {
		ArrayList<Customer> customers;
		
		try {
			customers = io.readObject();
		} catch (FileNotFoundException e) {
			customers = new ArrayList<Customer>();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		for(int i=0; i<customers.size(); i++) {
			if(customers.get(i).getUsername().equals(username)) {
				if(customers.get(i).getPassword().equals(password)) {
					Logging.logger.info("Customer: " + username + " was logged in");
					System.out.println("Customer was signed in");
					return customers.get(i);
				} else {
					Logging.logger.warn("Customer tried logging in with invalid credentials");
					throw new InvalidCredentialsException();
				}
			}
		}
		Logging.logger.warn("User tried logging in that does not exist");
		throw new UserDoesNotExistException();
	}
	
	public List<Customer> getAllCustomers(){
		ArrayList<Customer> customers;
		try {
			customers = io.readObject();
		} catch (Exception e) {
			customers = new ArrayList<Customer>();
		}
		return customers;
	}
}
