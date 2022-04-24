package com.chang.recmv.controller.api;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chang.recmv.dto.ResponseDto;
import com.chang.recmv.dto.UserDto;
import com.chang.recmv.service.UserService;

@Controller
public class UserApiController {
	
	private final UserService userService;
	
	private final AuthenticationManager authenticationManager;
	
	public UserApiController(UserService userService, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/join")
	public String join(@Valid UserDto userDto, Errors errors, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("userDto", userDto);
			
			Map<String, String> validationResult = userService.validateUser(errors);
			for(String key : validationResult.keySet())
				model.addAttribute(key, validationResult.get(key));
			return "user/join";
		}
		
		userService.save(userDto);
		return "redirect:/login";
	}
	
	@GetMapping("/duplicate")
	public @ResponseBody ResponseDto<String> duplicate(@RequestParam String username) {
		boolean result = userService.existsByUsername(username);
		String dbUsername = "";
		
		if(result)
			dbUsername = username;
		System.out.println("DB 아이디: " + dbUsername);
		return new ResponseDto<String>(HttpStatus.OK.value(), dbUsername);
		
	}
	
	@PutMapping("/api/users/{id}/update")
	public @ResponseBody ResponseDto<String> update(@RequestBody UserDto userDto, @PathVariable int id) {
		userService.update(userDto, id);
		
		// 트랜잭션이 종료되어서 DB의 값은 변경
		// 하지만 세션값은 변경되지 않은 상태, 따라서 직접 세션값 변경		
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<String>(HttpStatus.OK.value(), userDto.getUsername());
	}
	
	@DeleteMapping("/api/users/{id}/delete")
	public @ResponseBody ResponseDto<Integer> delete(@RequestBody UserDto userDto, @PathVariable int id) {
		int result = userService.delete(userDto, id);
		
		// 트랜잭션이 종료되어서 DB의 값은 변경
		// 하지만 세션값은 변경되지 않은 상태, 따라서 직접 세션값 변경
		// 회원탈퇴, 따라서 null로 설정
		if(result == 0)
			SecurityContextHolder.getContext().setAuthentication(null);		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
}