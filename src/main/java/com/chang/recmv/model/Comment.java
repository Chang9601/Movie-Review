package com.chang.recmv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private int id; // 키
	
	@Column(nullable = false, length = 150)
	private String content; // 내용
	
	// 연관관계 주인
	// 댓글과 리뷰: 다대일
	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

	// 연관관계 주인
	// 댓글과 사용자: 다대일
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "creation_date") 
	@CreatedDate
	private String creationDate; 
	
	@Column(name = "update_date") 
	@LastModifiedDate
	private String updateDate;

	public Comment() {
		
	}
	
	@Builder
	public Comment(String content, String creationDate, String updateDate) {
		this.content = content;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}
}