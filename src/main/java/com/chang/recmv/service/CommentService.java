package com.chang.recmv.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.chang.recmv.dto.CommentDto;
import com.chang.recmv.model.Comment;
import com.chang.recmv.model.Review;
import com.chang.recmv.model.User;
import com.chang.recmv.repository.CommentRepository;

@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	@Transactional(readOnly = true)
	public Map<String, String> validateComment(Errors errors) {
		Map<String, String> validationResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String key = String.format("valid_%s", error.getField());
			validationResult.put(key, error.getDefaultMessage());
		}
		
		return validationResult;
	}
	
	@Transactional
	public void save(CommentDto commentDto, Review review, User user) {
		Comment comment = commentDto.toEntity();
		comment.setUser(user);
		comment.setReview(review);
		
		commentRepository.save(comment);
	}
}