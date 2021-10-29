package com.chang.recmv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chang.recmv.model.User;
import com.chang.recmv.service.UserService;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 
	
	
	@Autowired
	private UserService service;
	
	// http://localhost:8088/recmv/signup
	@GetMapping("/signup")
	public ModelAndView signupGET() throws Exception {
		logger.info("C: signupGET() 호출");
		
		ModelAndView mav = new ModelAndView("thymeleaf/user/signup");
		
		logger.info("C: signupGET() 종료");
			
		return mav;
	}
		
	@PostMapping("/signup")
	public ModelAndView signupPOST(User user) throws Exception {
		logger.info("C: signupPOST() 호출");
		
		logger.info("사용자: " + user);
		ModelAndView mav = new ModelAndView("thymeleaf/user/login");
		//service.addUser(user);
		
		logger.info("C: signupPOST() 종료");
		
		return mav;
	}
	
	@PostMapping("/ckDupId")
	public String ckDupId(String id) throws Exception {
		logger.info("C: ckDupId() 호출");
		
		String ret = service.ckDupId(id);
		
		logger.info("C: ckDupId() 종료");
		
		return ret;
	}
	
	
	
	// http://localhost:8088/recmv/login
	@GetMapping("/login")
	public ModelAndView loginGET() throws Exception {
		ModelAndView mav = new ModelAndView("thymeleaf/user/login");
		
		
		return mav;
	}
	
	@PostMapping("/login")
	public ModelAndView loginPOST() throws Exception {
		ModelAndView mav = new ModelAndView("thymeleaf/user/main");
		
		return mav;
	}
}
