package com.chang.recmv.controller.api;

//import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chang.recmv.controller.UserController;
import com.chang.recmv.model.Rev;
import com.chang.recmv.service.RevService;

@RestController
@RequestMapping("/api/rev/*")
public class RevApiController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

	@Autowired
	private RevService service;
	
	//@Autowired
	//private HttpSession session;

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰쓰기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@PostMapping("/write")
	public ResponseEntity<String> writePost(@RequestBody Rev rev) throws Exception {
		logger.info("User: writePost(@RequestBody Rev rev) 시작");
		logger.info("리뷰쓰기: " + rev);	
		service.writeRev(rev);
		logger.info("User: writePost(@RequestBody Rev rev) 끝");
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰쓰기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

}
