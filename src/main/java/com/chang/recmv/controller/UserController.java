package com.chang.recmv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.chang.recmv.service.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}	
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}	
	
	@GetMapping("/users")
	public String list(@RequestParam String username, Model model) {
		model.addAttribute("user", userService.findByUsername(username));
		
		return "user/read";
	}
	
	@GetMapping("/users/{id}/update")
	public String update(@PathVariable int id, Model model) {
		model.addAttribute("user", userService.findById(id));	
		
		return "user/update";
	}
}
