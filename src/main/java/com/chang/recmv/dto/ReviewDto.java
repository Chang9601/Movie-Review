package com.chang.recmv.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.chang.recmv.model.Review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReviewDto {
	@NotBlank(message = "제목을 입력하세요.")
	private String title; // 아이디
	
	@NotBlank(message = "내용을 입력하세요.")
	private String content; // 비밀번호
	
	@PositiveOrZero(message = "별점을 입력하세요.(0 ~ 5)")
	private double rating; // 평점
	
	// ReviewDto(DTO) -> Review(Entity)
	public Review toEntity() {
		Review review = Review.builder()
				.title(title)
				.content(content)
				.rating(rating)
				.build();
		
		return review;
	}
}