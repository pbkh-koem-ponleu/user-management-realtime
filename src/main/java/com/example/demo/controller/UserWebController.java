package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserWebController {
	
	@Value("${socket.io.host}")
	String serverHost;
	
	@Value("${socket.io.port}")
	Integer socketIOPort;
	
	@RequestMapping("/me/users")
	public String user() {
		return "tables-basic";
	}
	
	@RequestMapping("/web/users")
	public String userWeb() {
		return "users";
	}
}
