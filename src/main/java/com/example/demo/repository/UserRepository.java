package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.User;

public interface UserRepository {
	public List<User> get();
	
	public User create(User _user);
	
	public User update(Long _id, User _user);
	
	public String delete(int _id);
	
	public String deleteAll();
	
	public User login(String _name, String _password);
}
