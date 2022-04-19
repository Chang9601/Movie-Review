package com.chang.recmv.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.chang.recmv.model.RoleType;
import com.chang.recmv.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 태그의 name 속성과 일치
@Setter
@Getter
@ToString
public class UserDto {
	@NotBlank(message = "아이디를 입력하세요.")
	@Pattern(regexp = "^(?=(.*[a-z])+)(?=(.*[0-9])+).{5,10}$", message = "5~10자의 영문 소문자와 숫자만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.")
	private String username; // 아이디
	
	@NotBlank(message = "비밀번호를 입력하세요.")
	@Pattern(regexp = "^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!@#$%^&*]+)).{7,14}$", message = "7~14자의 영문 대소문자, 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다. 각각 적어도 1개 이상 포함하세요.")
	private String password; // 비밀번호

	@NotBlank(message = "이메일을 입력하세요.")
	@Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,4})$", message = "이메일이 형식에 맞지 않습니다.")
	private String email; // 이메일
	
	// UserDto(DTO) -> User(Entity)
	public User toEntity() {
		User user = User.builder()
				.username(username)
				.password(password)
				.email(email)
				.role(RoleType.USER)
				.build();
		
		return user;
	}
}