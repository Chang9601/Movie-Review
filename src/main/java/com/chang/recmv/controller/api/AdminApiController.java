package com.chang.recmv.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chang.recmv.controller.UserController;
import com.chang.recmv.model.User;
import com.chang.recmv.service.AdminService;

@RestController
@RequestMapping("/api/admin/*")
public class AdminApiController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AdminService service;

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 관리자 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@PutMapping("/update/{num}")
	public ResponseEntity<String> adminUpdateUserPUT(@RequestBody User user, @PathVariable Integer num)
			throws Exception {
		logger.info("User: adminUpdateUserPUT(@RequestBody User user, @PathVariable Integer num) 시작");
		logger.info("관리자 회원수정 후: " + user);
		service.adminUpdateUser(user, num);
		logger.info("User: adminUpdateUserPUT(@RequestBody User user, @PathVariable Integer num) 끝");

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 관리자 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 관리자 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@DeleteMapping("/delete/{num}")
	public ResponseEntity<String> adminDeleteUserDELETE(@PathVariable Integer num) throws Exception {
		logger.info("Admin: adminDeleteUserDELETE(@PathVariable Integer num) 시작");
		// 회원번호로 작성된 리뷰 모두 삭제
		service.adminDeleteRevs(num);
		// 회원삭제
		service.adminDeleteUser(num);
		logger.info("Admin: adminDeleteUserDELETE(@PathVariable Integer num) 끝");

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 관리자 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}