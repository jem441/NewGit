package com.example.services;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.FileIO;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.logging.Logging;
import com.example.models.Employee;

public class EmployeeService {

	private String file;
	private FileIO<Employee> io;
	private List<Employee> emp = new ArrayList<Employee>();
	
	public void Employee() {
		emp.add(new Employee("James", "password"));
	}
	
	public Employee getEmployeeByUserName(String username) {
		for(int i=0; i<emp.size(); i++) {
			Employee e = emp.get(i);
			if(e.getUsername().equals(username)) {
				return e;
			}
		}
		return null;
		
		
	}
	public EmployeeService(String file) {
		this.file = file;
		this.io = new FileIO<Employee>(file);
	}
	
	public Employee logIn(String username, String password) throws InvalidCredentialsException {
		ArrayList<Employee> employee;
		
		try {
			employee = io.readObject();
		} catch (FileNotFoundException e) {
			employee = new ArrayList<Employee>();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		for(int i=0; i<employee.size(); i++) {
			if(employee.get(i).getPassword().equals(username)) {
				if(employee.get(i).getPassword().equals(password)) {
					Logging.logger.info("Employee: " + username + " was logged in");
					System.out.println("Employee was signed in");
					return employee.get(i);
				} else {
					Logging.logger.warn("User tried logging into an Employee account with invalid credentials");
					throw new InvalidCredentialsException();
				}
			}
		}
		Logging.logger.warn("User tried using a login that does not exist");
		throw new UserDoesNotExistException();
	}
}
