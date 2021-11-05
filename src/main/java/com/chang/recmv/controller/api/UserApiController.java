package com.chang.recmv.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chang.recmv.controller.UserController;
import com.chang.recmv.model.User;
import com.chang.recmv.service.UserService;

@RestController
@RequestMapping("/api/user/*")
public class UserApiController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

	@Autowired
	private UserService service;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signupPost(@RequestBody User user) throws Exception {
		logger.info("signPost(@RequestBody User user) 시작");
		logger.info("회원가입: " + user);
		service.addUser(user);
		logger.info("signPost(@RequestBody User user) 끝");
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	@GetMapping("/ckDupId")
	public ResponseEntity<String> ckDupId(@RequestParam("id") String id) throws Exception {
		logger.info("ckDupId(@RequestParam String id) 시작");
		logger.info("아이디 중복확인: " + id);
		String dbId = service.ckDupId(id);
		logger.info("ckDupId(@RequestParam String id) 끝");

		return new ResponseEntity<String>(dbId, HttpStatus.OK);
	}
}
