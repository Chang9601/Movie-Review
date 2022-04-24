package com.chang.recmv.controller.api;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chang.recmv.config.auth.PrincipalDetails;
import com.chang.recmv.dto.CommentDto;
import com.chang.recmv.service.CommentService;
import com.chang.recmv.service.ReviewService;

@Controller
public class CommentApiController {
	
	private final CommentService commentService;
	
	private final ReviewService reviewService;
	
	public CommentApiController(CommentService commentService, ReviewService reviewService) {
		this.commentService = commentService;
		this.reviewService = reviewService;
	}

	@PostMapping("/api/reviews/{id}/comments")
	public String create(@PathVariable int id, @Valid CommentDto commentDto, Errors errors, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("내용: " + commentDto.getContent());
		
		if(errors.hasErrors()) {
			model.addAttribute("commentDto", commentDto);
			
			Map<String, String> validationResult = commentService.validateComment(errors);
			for(String key : validationResult.keySet())
				model.addAttribute(key, validationResult.get(key));
			return "redirect:/reviews/" + id;
		}
		
		commentService.save(commentDto, reviewService.findById(id), principalDetails.getUser());

		return "redirect:/reviews/" + id;	
	}
}