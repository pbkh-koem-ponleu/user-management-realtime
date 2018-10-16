package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserWebController {
	
	@RequestMapping("/me/users")
	public String user() {
		return "users";
	}
}
