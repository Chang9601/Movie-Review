package com.chang.recmv.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private int id; // 키

	// 연관관계 매핑
	// 리뷰와 사용자: 다대일
	@ManyToOne(fetch = FetchType.LAZY) // 기본적으로 지연 로딩 권장
	@JoinColumn(name = "user_id")
	private User user;

	// 연관관계 매핑
	// 리뷰와 영화: 다대일
	@ManyToOne(fetch = FetchType.LAZY) // 기본적으로 지연 로딩 권장
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@Column(nullable = false, length = 100)
	private String title; // 리뷰제목

	@Lob
	private String content; // 리뷰내용
	
	private double rating; // 리뷰평가, not null 자동 추가
	
	//private int like; // 리뷰 좋아요 개수,  not null 자동 추가
	
	@CreationTimestamp
	@Column(name = "creation_date")
	private Timestamp creationDate; // 등록 날짜와시간
	
	@UpdateTimestamp
	@Column(name = "update_date")
	private Timestamp updateDate; // 수정 날짜와시간
	
	/*
	// 연관관계 편의 메서드(양방향)
	public void setUser(User user) {
		
		// 기존 관계 제거
		if(this.user != null)
			this.user.getReviews().remove(this);
		
		this.user = user;
		user.getReviews().add(this);
	}*/
	
	public Review() {
		
	}
	
	@Builder
	public Review(String title, String content, double rating) {
		this.title = title;
		this.content = content;
		this.rating = rating;
	}
}