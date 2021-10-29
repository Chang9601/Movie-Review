package com.chang.recmv.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
	private String id; // 아이디
	private String pw; // 비밀번호
	private String email; // 이메일
	private String grade; // 관리자/사용자
	//private String friends; // 친구
	// private String movies; // 리뷰
	private Timestamp createDate; // 등록 날짜/시간
	private Timestamp updateDate; // 수정 날짜/시간
}
