package com.chang.recmv.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chang.recmv.model.User;
import com.chang.recmv.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	public PrincipalDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// form에서 name이 username, password인 2개의 값을 가로챈다.
	// password를 자동으로 처리
	// SecurityContext(Authentication(UserDetails(PrincipalDetails)))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		return user != null ? new PrincipalDetails(user) : null;
	}
}