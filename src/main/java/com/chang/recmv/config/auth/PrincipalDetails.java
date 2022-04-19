package com.chang.recmv.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chang.recmv.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Spring Security가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 객체를
// Spring Security의 고유한 세션저장소에 저장
@Getter
@Setter
@ToString
public class PrincipalDetails implements UserDetails {

	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// 사용자의 권한 목록
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return "ROLE_" + user.getRole();
			}
		});
		
		return collection;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}