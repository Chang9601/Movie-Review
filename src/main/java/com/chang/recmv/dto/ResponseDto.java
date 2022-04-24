package com.chang.recmv.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDto<T> {
	int status;
	T data;
	
	public ResponseDto (int status, T data) {
		this.status = status;
		this.data = data;
	}
}
