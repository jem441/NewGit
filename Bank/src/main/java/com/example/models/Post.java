package com.example.models;

import java.io.Serializable;

public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	private String customer;
	private String content;

	public Post() {
		
	}
	
	public Post(String customer, String content) {
		this.content = content;
		this.customer = customer;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Post [customer=" + customer + ", content=" + content + "]";
	}
	
}
