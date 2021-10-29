package com.chang.recmv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 
	
	// http://localhost:8088/recmv/signup
	@GetMapping("/signup")
	public ModelAndView signupGET() throws Exception {
		ModelAndView mav = new ModelAndView("thymeleaf/user/signup");
		
		logger.info("C: signupGET() 호출");
		logger.info("C: signupGET() 종료");
		
		return mav;
	}
	
	// http://localhost:8088/recmv/signup	
	@PostMapping("/signup")
	public String signupPOST() throws Exception {
		System.out.println("C: signupPOST() 호출");
		
		
		
		
		System.out.println("C: signupPOST() 종료");
		
		return "";
	}
}
