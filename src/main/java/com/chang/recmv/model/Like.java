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
public class Like {
	private Integer num; // 키
	private Integer userNum; // 회원번호
	private Integer revNum; // 리뷰번호
	private Integer like; // 좋아요 개수
	private Timestamp createDate; // 등록 날짜/시간
	private Timestamp updateDate; // 수정 날짜/시간	
}