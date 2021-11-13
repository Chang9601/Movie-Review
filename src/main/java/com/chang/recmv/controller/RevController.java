package com.chang.recmv.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chang.recmv.model.Rev;
import com.chang.recmv.service.RevService;

@Controller
@RequestMapping("/rev/*")
public class RevController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

	@Autowired
	private RevService service;
	
	@Autowired
	private HttpSession session;
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰메인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/main")
	public String main(Model model) throws Exception {		
		logger.info("Rev: main(Model model) 시작");
		String id = (String)session.getAttribute("id");
	//	if(id == null) 
		//	return "redirect:/user/login";		
		model.addAttribute("id", id);
		logger.info("Rev: main(Model model) 끝");		
		
		return "rev/main";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰메인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰작성 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/write")
	public String writeGet(Model model) throws Exception {
		logger.info("Rev: writeGet(Model model) 시작");
		String id = (String)session.getAttribute("id");
		if(id == null) 
			return "redirect:/user/login";
		model.addAttribute("id", id);
		logger.info("Rev: writeGet(Model model) 끝");

		return "rev/write";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰작성 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰목록 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	/*@GetMapping("/all")
	public ModelAndView getAllRev(Model model) throws Exception {
		logger.info("Rev: getAllRev() 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/rev/all");				
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:/user/login");
			return mav;			
		}		 
		mav.addObject("revs", service.getAllRev());
		logger.info("Rev: getAllRev() 끝");		
		
		return mav;
	}*/
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰목록 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰읽기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/read/{num}")
	public String readRev(@PathVariable Integer num, Model model) throws Exception {
		logger.info("Rev : readRev(@PathVariable Integer num, Model model) 시작");
		//logger.info("숫자: " + num);
		String id = (String)session.getAttribute("id");
		//if(id == null) 
			//return "redirect:/user/login";
		model.addAttribute("rev", service.readRev(num));
		logger.info("리뷰읽기: " + service.readRev(num));
		logger.info("Rev : readRev(@PathVariable Integer num, Model model) 끝");
		
		return "rev/read";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰읽기 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	@GetMapping("/update/{num}")
	public String updateRevGET(@PathVariable Integer num, Model model) throws Exception {
		logger.info("Rev: updateRevGET(@PathVariable Integer num, Model model) 시작");
		String id = (String)session.getAttribute("id");
		if(id == null) 
			return "redirect:/user/login";		
		model.addAttribute("rev", service.readRev(num));
		logger.info("수정 전: " + service.readRev(num));
		logger.info("Rev: updateRevGET(@PathVariable Integer num, Model model) 끝");
		
		return "rev/update";
	}	
	/*
	@PostMapping("/rev/update")
	public ModelAndView updateRevPOST(Rev rev) throws Exception {
		logger.info("Rev : updateRevPOST(Integer num, HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("redirect:./read?num=" + rev.getNum());
		logger.info("수정 후: " + rev);
		service.updateRev(rev);
		logger.info("Rev : updateRevPOST(Integer num, HttpSession session) 끝");
		
		return mav;
	}*/	
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
