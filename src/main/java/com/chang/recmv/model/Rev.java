package com.chang.recmv.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Rev {
	private Integer num; // 번호
	private String title; // 제목
	private String movie; // 영화이름
	private Integer rating; // 평가
	private String review; // 내용
	private Integer like; // 좋아요
	private String userId; // recmv_user의 id를 참조하는 FK
	private Timestamp createDate; // 등록 날짜/시간
	private Timestamp updateDate; // 수정 날짜/시간
}
