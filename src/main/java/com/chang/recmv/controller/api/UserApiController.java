package com.chang.recmv.controller.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@Autowired
	private HttpSession session;

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원가입 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/ckDupId")
	public ResponseEntity<String> ckDupId(@RequestParam("id") String id) throws Exception {	
		logger.info("User: ckDupId(@RequestParam(\"id\") String id) 시작");
		logger.info("아이디 중복확인: " + id);
		String dbId = service.ckDupId(id);
		logger.info("User: ckDupId(@RequestParam(\"id\") String id) 끝");

		return new ResponseEntity<String>(dbId, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signupPOST(@RequestBody User user) throws Exception {
		logger.info("User: signupPOST(@RequestBody User user) 시작");
		logger.info("회원가입: " + user);
		service.addUser(user);
		logger.info("User: signupPOST(@RequestBody User user) 끝");
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원가입 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@PostMapping("/login")
	public ResponseEntity<String> loginPOST(@RequestBody User user) throws Exception {
		logger.info("User: loginPOST(@RequestBody User user) 시작");
		logger.info("로그인: " + user);
		String id = service.ckLogin(user);
		if(id != null)
			session.setAttribute("id", id);
		logger.info("User: loginPOST(@RequestBody User user) 끝");	
		
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@PutMapping("/update")
	public ResponseEntity<String> updateUserPUT(@RequestBody User user) throws Exception {
		logger.info("User: updateUserPUT(@RequestBody User user) 시작");
		logger.info("회원수정 후: " + user);
		String id = (String)session.getAttribute("id");
		String pw = service.readUser(id).getPw();
		service.updateUser(user, pw);
		logger.info("User: updateUserPUT(@RequestBody User user) 끝");	
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUserDELETE(@RequestBody User user) throws Exception {
		logger.info("User: deleteUserDELETE(@RequestBody User user) 시작");
		// 작성된 리뷰 모두 삭제 후 회원탈퇴
		service.deleteRevs(user.getId());
		service.deleteUser(user);
		session.invalidate();
		logger.info("회원삭제 후: " + user);		
		logger.info("User: deleteUserDELETE(@RequestBody User user) 끝");	
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}