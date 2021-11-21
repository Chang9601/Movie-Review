package com.chang.recmv.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Rev {
	private Integer num; // 키
	private Integer userNum; // recmv_user의 num을 참조하는 FK
	private Integer movieNum; // recmv_movie의 num을 참조하는 FK
	private String id; // 리뷰목록 아이디
	private String image; // 리뷰목록 이미지
	private String movie; // 영화이름
	private String title; // 리뷰제목
	private Double rating; // 평가
	private String content; // 내용
	private Integer like; // 좋아요
	private Timestamp createDate; // 등록 날짜/시간
	private Timestamp updateDate; // 수정 날짜/시간
}
