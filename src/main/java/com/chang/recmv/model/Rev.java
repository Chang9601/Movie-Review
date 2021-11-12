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
	private Integer num; // 번호
	private String movie; // 영화이름
	private String title; // 리뷰제목
	private String userId; // recmv_user의 id를 참조하는 FK	
	private Integer rating; // 평가
	private String content; // 내용
	private Integer like; // 좋아요
	private Timestamp createDate; // 등록 날짜/시간
	private Timestamp updateDate; // 수정 날짜/시간
}
