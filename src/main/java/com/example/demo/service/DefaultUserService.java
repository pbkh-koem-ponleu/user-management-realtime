package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Component
public class DefaultUserService implements UserService {

	private List<User> users = new ArrayList<>();
	
	@Override
	public List<User> get() {
		return this.users;
	}

	@Override
	public User create(User _user) {
		this.users.add(_user);
		return _user;
	}

	@Override
	public User update(int _id) {
		return null;
	}

	@Override
	public String delete(int _id) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == _id) {
				users.remove(i);
				return "success removed " + _id;
			}
		}
		return "not found";
	}

}
