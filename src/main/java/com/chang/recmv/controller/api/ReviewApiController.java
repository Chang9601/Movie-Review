package com.chang.recmv.controller.api;

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
import com.chang.recmv.model.Movie;
import com.chang.recmv.model.Review;
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

	@PostMapping("/reviews/movies/{id}")
	public String create(@Valid ReviewDto reviewDto, Errors errors, Model model, @PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		/* redirect 때문인지 오류 메시지 작동 X
		 * if(errors.hasErrors()) { model.addAttribute("reviewDto", reviewDto);
		 * 		Map<String, String> validationResult = reviewService.validateReview(errors);
		 * 		for(String key : validationResult.keySet()) model.addAttribute(key,
		 * 		validationResult.get(key)); return "redirect:/write/" + id; 
		 * }
		 */

		System.out.println("리뷰 쓰기: " + reviewDto);

		Movie movie = movieService.findById(id);
		reviewService.save(reviewDto, principalDetails.getUser(), movie);

		return "redirect:/";
	}

	/*
	 * @PutMapping("/review/{id}") public String update(@Valid ReviewDto reviewDto,
	 * Errors errors, Model model, @PathVariable int id, @AuthenticationPrincipal
	 * PrincipalDetails principalDetails) {
	 * 
	 * redirect 때문인지 오류 메시지 작동 X if(errors.hasErrors()) {
	 * model.addAttribute("reviewDto", reviewDto); Map<String, String>
	 * validationResult = reviewService.validateReview(errors); for(String key :
	 * validationResult.keySet()) model.addAttribute(key,
	 * validationResult.get(key)); return "redirect:/write/" + id; }
	 * 
	 * System.out.println("리뷰 수정: " + reviewDto);
	 * 
	 * Movie movie = movieService.findById(id); reviewService.save(reviewDto,
	 * principalDetails.getUser(), movie);
	 * 
	 * return "redirect:/review/" + String.valueOf(id); }
	 */

	@PutMapping("/reviews/{id}/update")
	public @ResponseBody ResponseDto<Integer> update(@RequestBody Review review, @PathVariable int id) {
		reviewService.update(review, id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), id); // 변경한 리뷰로 이동		
	}
	
	@DeleteMapping("/reviews/{id}")
	public @ResponseBody ResponseDto<Integer> delete(@PathVariable int id) {
		reviewService.delete(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 0);			
	}
}