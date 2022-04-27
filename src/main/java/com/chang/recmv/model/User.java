package com.chang.recmv.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	
	// 연관관계의 주인 X
	// 반대쪽 필드의 이름을 값으로 설정
	// 사용자와 리뷰: 일대다
	// 연관관계의 주인이 아니기 때문에 mappedBy 키워드 사용
	// User 삭제 시 외래 키로 걸린 모든 Review 삭제 위해서 CascadeType.REMOVE 사용
	// @OneToMany는 기본적으로 FetchType.LAZY, @AuthenticationPrincipal은 SQL 쿼리 실행 시 User의 연관된 reviews에 접근하기 때문에 즉시 로딩, 따라서 FetchType.EAGER
	// ★컬렉션의 경우 user.getReviews().get(0)처럼 실제 데이터 조회 시 데이터베이스를 조회해서 초기화, 따라서 user.getReviews() 호출 시 컬렉션 초기화 X
	// Resolved [org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.chang.recmv.model.User.reviews, could not initialize proxy - no Session]
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Review> reviews;
	
	// User 삭제 시 외래 키로 걸린 모든 Comment 삭제 위해서 CascadeType.REMOVE 사용
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Comment> comments;
	
	@Column(nullable = false, length = 100, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password; // 비밀번호
	
	@Column(nullable = false, length = 50, unique = true)
	private String email; // 이메일

	@Column(length = 100)
	private String provider; // OAuth 2.0 제공자
	
	@Column(name = "provider_id", length = 100)
	private String providerId; // 
	
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
	
	@Builder
	public User(String username, String password, String email, RoleType role, String provider, String providerId) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
	}
}