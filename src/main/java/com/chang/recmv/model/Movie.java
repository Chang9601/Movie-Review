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
	private Integer revNum; // recmv_rev의 num 참조
	private String title; // 영화제목
	private String link; // 영화링크
	private String image; // 영화 이미지
	private String actor; // 배우목록
	private String plot; // 줄거리
	private Timestamp createDate; // 등록 날짜/시간	
}
