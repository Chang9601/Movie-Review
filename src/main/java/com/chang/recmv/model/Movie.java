package com.chang.recmv.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private int id; // 키
	
	@Column(nullable = false, length = 200)
	private String title; // 영화의 제목

	private String link; // 영화의 하이퍼텍스트 link

	private String image; // 영화의 썸네일 이미지의 URL

	@Column(nullable = false, length = 300)
	private String actor; // 영화의 출연 배우
	
	@Lob
	private String plot; // 영화 줄거리
	
	@CreationTimestamp
	@Column(name = "creation_date")
	private Timestamp creationDate; // 등록 날짜와 시간
	
	@UpdateTimestamp
	@Column(name = "update_date")	
	private Timestamp updateDate; // 수정 날짜와 시간
	
	public Movie() {
		
	}
	
	// 클래스 수준이 아니고 직접 만든 생성자에 @Builder 추가	
	@Builder
	public Movie(String title, String link, String image, String actor, String plot) {
		this.title = title;
		this.link = link;
		this.image = image;
		this.actor = actor;
		this.plot = plot;
	}
}