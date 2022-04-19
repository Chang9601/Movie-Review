package com.chang.recmv.controller.api;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chang.recmv.dto.ResponseDto;
import com.chang.recmv.dto.UserDto;
import com.chang.recmv.service.UserService;

@Controller
public class UserApiController {
	
	private final UserService userService;
	
	public UserApiController(UserService userService) {
		this.userService = userService;
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
}