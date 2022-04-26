package com.chang.recmv.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	@Transactional(readOnly = true)
	public Page<Comment> findAll(int reviewId, Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 5);
		
		return commentRepository.findByReviewId(reviewId, pageable);
	}
	
	@Transactional
	public void update(CommentDto commentDto, int id) {
		Comment entity = commentRepository.findById(id).orElseThrow(() -> {
			return new IllegalStateException("댓글 수정 실패: Comment 객체 없음");			
		});
		
		Comment comment = commentDto.toEntity();
		entity.setContent(comment.getContent());
		entity.setUpdateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));
	}
	
	@Transactional
	public void delete(int id) {
		commentRepository.deleteById(id);
	}
}