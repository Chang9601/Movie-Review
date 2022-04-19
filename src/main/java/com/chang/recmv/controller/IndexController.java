package com.chang.recmv.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chang.recmv.config.auth.PrincipalDetails;
import com.chang.recmv.service.MovieService;

@Controller
public class IndexController {
	
	private final MovieService movieService;
	
	public IndexController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model, @PageableDefault Pageable pageable) throws Exception { // 세션 정보 접근
		System.out.println("세션 값: " + principalDetails);
		model.addAttribute("movies", movieService.findAll(pageable));
		
		return "index";
	}
}