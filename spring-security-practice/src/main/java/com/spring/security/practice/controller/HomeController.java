package com.spring.security.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home1";
	}

	@GetMapping("/manager")
	public String managers() {
		return "manager";
	}

	@GetMapping("/admin")
	public String admins() {
		return "admin";
	}
}
