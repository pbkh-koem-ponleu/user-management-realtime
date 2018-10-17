package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.User;

public interface UserRepository {
	public List<User> get();
	
	public User create(User _user);
	
	public User update(int _id);
	
	public String delete(int _id);
	
	public String deleteAll();
}
