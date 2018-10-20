package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Component
public class DefaultUserService implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	private List<User> users = new ArrayList<>();
	private int id = 1;
	
	@Override
	public List<User> get() {
		return this.userRepository.get();
	}

	@Override
	public User create(User _user) {
//		_user.setId(id);
//		id++;
//		this.users.add(_user);
		return this.userRepository.create(_user);
	}

	@Override
	public User update(Long _id, User _user) {
		return this.userRepository.update(_id, _user);
	}

	@Override
	public String delete(int _id) {
//		for (int i = 0; i < users.size(); i++) {
//			if (users.get(i).getId() == _id) {
//				users.remove(i);
//				return "success removed " + _id;
//			}
//		}
		return userRepository.delete(_id);
	}
	
	@Override
	public String deleteUsers() {
//		users.clear();
		return this.userRepository.deleteAll();
	}

	@Override
	public User login(String _name, String _password) {
		try {
			return this.userRepository.login(_name, _password);
		} catch (Exception e) {
			User user = new User(null, "annonymouse", null, null, null);
			return user;
		}
	}

}
