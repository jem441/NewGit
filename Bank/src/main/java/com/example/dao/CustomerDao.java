package com.example.dao;

import com.example.models.Customer;

public interface CustomerDao {

	Customer getUserByUsername(String username);
	Customer addUser(Customer a);
}
