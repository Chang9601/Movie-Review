package com.chang.recmv.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.chang.recmv.dto.ReviewDto;
import com.chang.recmv.model.Movie;
import com.chang.recmv.model.Review;
import com.chang.recmv.model.User;
import com.chang.recmv.repository.ReviewRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	@Transactional(readOnly = true)
	public Map<String, String> validateReview(Errors errors) {
		Map<String, String> validationResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String key = String.format("valid_%s", error.getField());
			validationResult.put(key, error.getDefaultMessage());
		}
		
		return validationResult;
	}	
	
	@Transactional
	public void save(ReviewDto reviewDto, User user, Movie movie) {
		Review review = reviewDto.toEntity();
		review.setUser(user);
		review.setMovie(movie);
		
		reviewRepository.save(review);
	}
	
	@Transactional(readOnly = true)
	public Page<Review> findAll(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 5);
		
		return reviewRepository.findAll(pageable);
	}
}