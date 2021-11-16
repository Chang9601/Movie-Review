package com.chang.recmv.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.Paging;
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
	public String main(Model model, Criteria cri) throws Exception {
		logger.info("Rev: main(Model model, Criteria cri) 시작");
		Paging page = new Paging(cri, service.getNumAllRev());
		model.addAttribute("page", page);
		model.addAttribute("revs", service.getAllRev(cri));
		logger.info("페이징: " + page);
		logger.info("Rev: main(Model model, Criteria cri) 끝");

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
		Integer num = service.readNum(id);
		model.addAttribute("id", id);
		model.addAttribute("userNum", num);
		//logger.info("아이디: " + id + ", 번호: " + num);
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

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰조회 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/read/{num}")
	public String readRev(@PathVariable Integer num, Model model, Integer currentPageNum) throws Exception {
		logger.info("Rev : readRev(@PathVariable Integer num, Model model, Integer currentPageNum) 시작");
		String id = (String)session.getAttribute("id");
		logger.info("페이지 번호: " + currentPageNum);
		// 리뷰를 작성한 사용자의 번호
		Integer userNum = service.readRev(num).getUserNum();
		// 현재 로그인한 사용자의 번호
		model.addAttribute("num", service.readNum(id));
		// 리뷰를 작성한 사용자의 아이디
		model.addAttribute("id", service.readId(userNum));
		model.addAttribute("currentPageNum", currentPageNum);
		model.addAttribute("rev", service.readRev(num));
		logger.info("리뷰조회: " + service.readRev(num));
		logger.info("Rev : readRev(@PathVariable Integer num, Model model, Integer currentPageNum) 끝");
		
		return "rev/read";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰조회 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	@GetMapping("/update/{num}")
	public String updateRevGet(@PathVariable Integer num, Model model) throws Exception {
		logger.info("Rev: updateRevGet(@PathVariable Integer num, Model model) 시작");
		String id = (String)session.getAttribute("id");
		if(id == null) 
			return "redirect:/user/login";	
		// 리뷰를 작성한 사용자의 번호
		Integer userNum = service.readRev(num).getUserNum();
		// 리뷰를 작성한 사용자의 아이디
		model.addAttribute("id", service.readId(userNum));		
		model.addAttribute("rev", service.readRev(num));
		logger.info("리뷰수정 전: " + service.readRev(num));
		logger.info("Rev: updateRevGet(@PathVariable Integer num, Model model) 끝");
		
		return "rev/update";
	}	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
}
