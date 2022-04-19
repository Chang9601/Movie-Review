package com.chang.recmv.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id; // 키
	
	// 연관관계의 주인
	// 반대쪽 필드의 이름을 값으로 설정
	// 사용자와 리뷰: 일대다
	////@OneToMany(mappedBy = "user")
	//private List<Review> reviews;
	
	@Column(nullable = false, length = 100, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password; // 비밀번호
	
	@Column(nullable = false, length = 50, unique = true)
	private String email; // 이메일
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)	
	private RoleType role; // 관리자/사용자

	@CreationTimestamp
	@Column(name = "creation_date")
	private Timestamp creationDate; // 등록 날짜와 시간
	
	@UpdateTimestamp
	@Column(name = "update_date")	
	private Timestamp updateDate; // 수정 날짜와 시간
	
	public User() {
		
	}
	
	// 클래스 수준이 아니고 직접 만든 생성자에 @Builder 추가
	@Builder
	public User(String username, String password, String email, RoleType role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}
}