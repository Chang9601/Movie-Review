package com.chang.recmv.controller.api;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chang.recmv.config.auth.PrincipalDetails;
import com.chang.recmv.dto.ReviewDto;
import com.chang.recmv.model.Movie;
import com.chang.recmv.service.MovieService;
import com.chang.recmv.service.ReviewService;

@Controller
public class ReviewApiController {
	
	private final ReviewService reviewService;
	
	private final MovieService movieService;
	
	public ReviewApiController(ReviewService reviewService, MovieService movieService) {
		this.reviewService = reviewService;
		this.movieService = movieService;
	}
	
	@PostMapping("/write/{id}")
	public String write(@Valid ReviewDto reviewDto, Errors errors, Model model, @PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		/*
		 * redirect 때문인지 오류 메시지 작동 X
		if(errors.hasErrors()) {
			model.addAttribute("reviewDto", reviewDto);
			
			Map<String, String> validationResult = reviewService.validateReview(errors);
			for(String key : validationResult.keySet())
				model.addAttribute(key, validationResult.get(key));
			return "redirect:/write/" + id;
		}
		*/
		System.out.println("리뷰: " + reviewDto);
		
		Movie movie = movieService.findById(id);
		reviewService.save(reviewDto, principalDetails.getUser(), movie);
		
		return "redirect:/";
	}
}