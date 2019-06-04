package com.spring.security.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/showLoginPage")
	public String loginPage() {
		return "plain-login";
	}
	@GetMapping("/custom-access-denied")
	public String customeAccessDenied() {
		return "access-denied";
	}

}
