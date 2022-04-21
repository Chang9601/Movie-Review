package com.chang.recmv.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chang.recmv.service.MovieService;

@Controller
public class MovieController {
	
	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping("/movies")
	public String movies(@RequestParam String query, Model model, @PageableDefault Pageable pageable) throws Exception { // 세션 정보 접근
		model.addAttribute("movies", movieService.findByTitleContaining(query, pageable));
		
		return "movie/movies";
	}
}