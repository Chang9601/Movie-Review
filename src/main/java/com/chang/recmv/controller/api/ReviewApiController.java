package com.chang.recmv.controller.api;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chang.recmv.config.auth.PrincipalDetails;
import com.chang.recmv.dto.ResponseDto;
import com.chang.recmv.dto.ReviewDto;
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
	
	@PostMapping("/api/movies/{id}/reviews")
	public String create(@Valid ReviewDto reviewDto, Errors errors, Model model, @PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		if(errors.hasErrors()) {
			model.addAttribute("reviewDto", reviewDto);
			
			Map<String, String> validationResult = reviewService.validateReview(errors);
			for(String key : validationResult.keySet())
				model.addAttribute(key, validationResult.get(key));
			return "redirect:/movies/" + id + "/reviews";
		}
		
		reviewService.save(reviewDto, principalDetails.getUser(), movieService.findById(id));

		return "redirect:/";
	}
	
	@PutMapping("/api/reviews/{id}/update")
	public @ResponseBody ResponseDto<Integer> update(@RequestBody ReviewDto reviewDto, @PathVariable int id) {
		reviewService.update(reviewDto, id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), id); // 변경한 리뷰로 이동		
	}

	@DeleteMapping("/api/reviews/{id}")
	public @ResponseBody ResponseDto<Integer> delete(@PathVariable int id) {
		reviewService.delete(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 0);			
	}
}