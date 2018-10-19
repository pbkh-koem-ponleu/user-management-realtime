package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
	public List<User> get();
	
	public User create(User _user);
	
	public User update(Long _id, User _user);
	
	public String delete(int _id);
	
	public String deleteUsers();
	
	public User login(String _name, String _password);
}
