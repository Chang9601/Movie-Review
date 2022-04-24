package com.chang.recmv.controller;


import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chang.recmv.service.MovieService;

@Controller
public class IndexController {
	
	private final MovieService movieService;
	
	public IndexController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping({"", "/"})
	public String index( Model model, @PageableDefault Pageable pageable) { // 세션 정보 접근
		model.addAttribute("movies", movieService.findAll(pageable));
		
		return "index";
	}
}