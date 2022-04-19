package com.chang.recmv.model;

import lombok.Getter;

@Getter
public enum RoleType {
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");
	
	private final String value;
	
	private RoleType(String value) {
		this.value = value;
	}
}