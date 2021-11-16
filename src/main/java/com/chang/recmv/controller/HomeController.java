package com.chang.recmv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.Paging;
import com.chang.recmv.service.RevService;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

	@Autowired
	private RevService service;
	
	@GetMapping("/")
	public String start(Model model, Criteria cri) throws Exception {
		/*logger.info("Home: start(Model model) 시작");
		Paging page = new Paging(cri, service.getNumAllRev());
		model.addAttribute("page", page);
		model.addAttribute("revs", service.getAllRev(cri));
		//logger.info("페이징: " + page);
		logger.info("Home: start(Model model) 끝");
		 */
		return "index";
	}
}
