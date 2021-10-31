package com.chang.recmv.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chang.recmv.model.Rev;
import com.chang.recmv.service.RevService;

@RestController
public class RevController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

	//@Autowired
	//private RevService service;
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰 메인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/rev/main")
	public ModelAndView main(HttpSession session) throws Exception {		
		logger.info("Rev: main() 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/rev/main");
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:./login");
			return mav;
		}		
		mav.addObject("id", id);
		logger.info("Rev: main() 끝");		
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰 메인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	@GetMapping("/rev/write")
	public ModelAndView writeGET(HttpSession session) throws Exception {
		logger.info("Rev : writeGET(HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/rev/write");
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:/user/login");
			return mav;			
		}			
		mav.addObject("id", id);
		logger.info("Rev : writeGET(HttpSession session) 끝");

		return mav;
	}
	
	@PostMapping("/rev/write")
	public ModelAndView writePOST(Rev rev) throws Exception {
		logger.info("Rev : writePOST(Rev rev, HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("redirect:./main");		
		logger.info("리뷰: " + rev);
		logger.info("rev : writePOST(Rev rev, HttpSession session) 끝");
		
		return mav;
	}
	
	//@GetMapping("/rev/update")
	
	
	
}
