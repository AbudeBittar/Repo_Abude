package com.grokonez.spring.restapi.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
	@Id
	private String id;

	private String name;
	private String streetName;
	private int streetNumber;
	private int phoneNumber;
	private String email;


	public Customer() {
	}

	

	public Customer(String name, String streetName,
			         int streetNumber, int phoneNumber, String email) {
		
		this.name = name;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}



	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

 
	// JSON-Format
	@Override
	public String toString() {
		return "Customer [id=" + id 
				   + ", name=" + name 
		    + ", street name=" + streetName
		  + ", street number=" + streetNumber
		  +  ", phone number=" + phoneNumber
		  +         ", email=" + email  + "]";
	}

}
