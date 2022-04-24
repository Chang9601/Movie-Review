package com.chang.recmv.dto;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;

import com.chang.recmv.model.Comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CommentDto {
	@NotBlank(message = "내용을 입력하세요.")
	private String content; // 비밀번호
	
	private String creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	
	private String updateDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	
	public Comment toEntity() {
		Comment comment = Comment.builder()
			.content(content)
			.creationDate(creationDate)
			.updateDate(updateDate)
			.build();
		
		return comment;
	}
}