package com.chang.recmv.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chang.recmv.controller.UserController;
import com.chang.recmv.model.User;

public class AdminApiController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 관리자 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@PutMapping("/update/{num}")
	public ResponseEntity<String> updateUserPUT(@RequestBody User user) throws Exception {
		logger.info("User: updateUserPUT(@RequestBody User user) 시작");
		logger.info("회원수정 후: " + user);
		String id = (String)session.getAttribute("id");
		String pw = service.readUser(id).getPw();
		service.updateUser(user, pw);
		logger.info("User: updateUserPUT(@RequestBody User user) 끝");	
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 관리자 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 관리자 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@DeleteMapping("/delete/{num}")
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
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 관리자 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}