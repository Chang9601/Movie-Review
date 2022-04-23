package com.chang.recmv.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.chang.recmv.dto.UserDto;
import com.chang.recmv.model.User;
import com.chang.recmv.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Transactional(readOnly = true)
	public Map<String, String> validateUser(Errors errors) {
		Map<String, String> validationResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String key = String.format("valid_%s", error.getField());
			validationResult.put(key, error.getDefaultMessage());
		}
		
		return validationResult;
	}
	
	@Transactional(readOnly = true)
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	@Transactional
	public void save(UserDto userDto) {
		// 비밀번호 암호화
		String rawPassword = userDto.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		userDto.setPassword(encPassword);
		
		User user = userDto.toEntity();
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Transactional(readOnly = true)
	public User findById(int id) {
		return userRepository.findById(id).orElseThrow(() -> {
			return new IllegalStateException("사용자 찾기 실패: 아이디 없음");				
		});
	}
}