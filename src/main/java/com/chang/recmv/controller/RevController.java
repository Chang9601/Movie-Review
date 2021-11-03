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

	@Autowired
	private RevService service;
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰메인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/rev/main")
	public ModelAndView main(HttpSession session) throws Exception {		
		logger.info("Rev: main() 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/rev/main");
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:/user/login");
			return mav;
		}		
		mav.addObject("id", id);
		logger.info("Rev: main() 끝");		
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰메인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰작성 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/rev/write")
	public ModelAndView writeGET(HttpSession session) throws Exception {
		logger.info("Rev: writeGET(HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/rev/write");
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:/user/login");
			return mav;			
		}			
		mav.addObject("id", id);
		logger.info("Rev: writeGET(HttpSession session) 끝");

		return mav;
	}
	
	@PostMapping("/rev/write")
	public ModelAndView writePOST(Rev rev) throws Exception {
		logger.info("Rev: writePOST(Rev rev, HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("redirect:./all");		
		logger.info("쓰기: " + rev);
		service.writeRev(rev);
		logger.info("Rev: writePOST(Rev rev, HttpSession session) 끝");
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰작성 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰목록 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/rev/all")
	public ModelAndView getAllRev(HttpSession session) throws Exception {
		logger.info("Rev: getAllRev(HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/rev/all");				
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:/user/login");
			return mav;			
		}		 
		mav.addObject("revs", service.getAllRev());
		logger.info("Rev: getAllRev(HttpSession session) 끝");		
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰목록 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰읽기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/rev/read")
	public ModelAndView readRev(Integer num, HttpSession session) throws Exception {
		logger.info("Rev : readRev(Integer num, HttpSession session) 시작");
		//logger.info("번호: " + num);
		ModelAndView mav = new ModelAndView("thymeleaf/rev/read");
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:/user/login");
			return mav;			
		}
		mav.addObject("id", id);
		mav.addObject("rev", service.readRev(num));
		logger.info("읽기: " + service.readRev(num));
		logger.info("Rev : readRev(Integer num, HttpSession session) 끝");
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰읽기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	@GetMapping("/rev/update")
	public ModelAndView updateRevGET(Integer num, HttpSession session) throws Exception {
		logger.info("Rev : updateRevGET(Integer num, HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/rev/update");
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:/user/login");
			return mav;			
		}
		mav.addObject("rev", service.readRev(num));
		logger.info("수정 전: " + service.readRev(num));
		logger.info("Rev : updateRevGET(Integer num, HttpSession session) 끝");
		
		return mav;
	}	
	
	@PostMapping("/rev/update")
	public ModelAndView updateRevPOST(Rev rev) throws Exception {
		logger.info("Rev : updateRevPOST(Integer num, HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("redirect:./read?num=" + rev.getNum());
		logger.info("수정 후: " + rev);
		service.updateRev(rev);
		logger.info("Rev : updateRevPOST(Integer num, HttpSession session) 끝");
		
		return mav;
	}	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰삭제 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	@PostMapping("/rev/delete")
	public ModelAndView deleteRev(Integer num) throws Exception {
		logger.info("Rev : deleteRev() 시작");
		ModelAndView mav = new ModelAndView("redirect:./all");
		service.deleteRev(num);
		logger.info("Rev : deleteRev() 끝");
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰삭제 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
}
