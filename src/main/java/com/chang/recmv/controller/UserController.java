package com.chang.recmv.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.chang.recmv.config.auth.PrincipalDetails;
import com.chang.recmv.model.User;
import com.chang.recmv.service.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
	
	// 다른 사용자 정보 접근 시 이동	
	private boolean isUserPrincipalDetailsSame(String user, String principalDetail) {
		return user.equals(principalDetail);
	}
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}	
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}	
	
	@GetMapping("/users")
	public String read(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam String username, Model model) {
		if(!isUserPrincipalDetailsSame(username, principalDetails.getUsername())) {
			return "redirect:/";
		}
		
		model.addAttribute("user", userService.findByUsername(username));
		
		return "user/read";
	}
	
	@GetMapping("/users/{id}/update")
	public String update(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int id, Model model) {
		User user = userService.findById(id);
		
		if(!isUserPrincipalDetailsSame(user.getUsername(), principalDetails.getUsername())) {
			return "redirect:/";
		}
		
		model.addAttribute("user", user);	
		
		return "user/update";
	}
	
	@GetMapping("/users/{id}/delete")
	public String delete(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int id, Model model) {
		User user = userService.findById(id);
		
		if(!isUserPrincipalDetailsSame(user.getUsername(), principalDetails.getUsername())) {
			return "redirect:/";
		}
		
		model.addAttribute("user", user);	
		
		return "user/delete";
	}
}