package com.example.demo.model;

public class User {
	int id;
	
	String name;
	
	String gender;
	
	String email;
	
	String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", address=" + address
				+ "]";
	}

	public User(User _user) {
		this.id 		= _user.id;
		this.name 		= _user.name;
		this.gender 	= _user.gender;
		this.email 		= _user.gender;
		this.address 	= _user.address;
	}

	public User(int id, String name, String gender, String email, String address) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.address = address;
	}
	
	
	
}
