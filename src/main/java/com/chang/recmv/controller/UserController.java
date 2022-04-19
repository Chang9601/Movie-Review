package com.chang.recmv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/join")
	public String join() throws Exception {
		return "user/join";
	}	
	
	@GetMapping("/login")
	public String login() throws Exception {
		return "user/login";
	}	
}
