package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	private Long id = 1l;
	
	@RequestMapping("/users")
	public List<User> get() {
		return userService.get();
	}
	
	@PostMapping("/users")
	public User create() {
		String name = "name "+ id;
		String gender = id%2==0?"male":"female";
		String email = "kp"+id+"@gmail.com";
		String address = id%2==0?"PP":"SVR";
		User user = new User(id, name, gender, email, address);
		id++;
		return userService.create(user);
	}
	
	@PatchMapping("/users/{id}")
	public User update(@PathVariable Long id, User _user) {
		return this.userService.update(id, _user);
	}
	
	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable int id) {
		return userService.delete(id);
	}
}
