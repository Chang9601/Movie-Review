package com.chang.recmv.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chang.recmv.config.auth.PrincipalDetails;
import com.chang.recmv.service.MovieService;
import com.chang.recmv.service.ReviewService;

@Controller
public class ReviewController {
	
	private final MovieService movieService;
	
	private final ReviewService reviewService;
	
	public ReviewController(MovieService movieService, ReviewService reviewService) {
		this.movieService = movieService;
		this.reviewService = reviewService;
	}
	
	@GetMapping("/reviews")
	public String reviews(Model model, @PageableDefault Pageable pageable) throws Exception {
		model.addAttribute("reviews", reviewService.findAll(pageable));

		return "review/reviews";
	}	
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // 미리 접근 권한 요구
	@GetMapping("/review/write/{id}")
	public String write(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		System.out.println("사용자 세션: " + principalDetails.getUser());
		model.addAttribute("movie", movieService.findById(id));
		
		return "review/write";
	}
	
	@GetMapping("/review/{id}")
	public String read(@PathVariable int id, Model model) {
		reviewService.updateView(id); 
		model.addAttribute("review", reviewService.findById(id));
		
		return "review/read";
	}

	@GetMapping("/review/update/{id}")
	public String update(@PathVariable int id, Model model) {
		model.addAttribute("review", reviewService.findById(id));
		
		return "review/update";
	}
}