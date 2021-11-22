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
public class Movie {
	private Integer num; // 키
	private String title; // 영화제목
	private String link; // 영화링크
	private String image; // 영화 이미지
	private String cast; // 배우목록
	private String plot; // 줄거리
	private Double rating; // 평균 평가	
	private Timestamp createDate; // 등록 날짜/시간	
}
