package com.chang.recmv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chang.recmv.model.User;
import com.chang.recmv.service.UserService;

@RestController
public class TestController {

	@Autowired
	private UserService service;
	
	// http://localhost:8088/recmv/ctl
	@GetMapping("/ctl")
	public String ctl() {
		User u = User.builder()
				.id("csup96")
				.pw("1234")
				.build();
		System.out.println("User: " + u);
		return "IT WORKS";
	}
	
	// http://localhost:8088/recmv/all
	@GetMapping("/all")
	public ModelAndView testUser() throws Exception {
		ModelAndView mav = new ModelAndView("thymeleaf/user/alluser");
		mav.addObject("list", service.getAll());	
		//for(User u : service.getAll())
			//System.out.println("ID: " + u.getId() + ", PW: " + u.getPw() + ", EMAIL: " + u.getEmail());		
		return mav;
	}
}
