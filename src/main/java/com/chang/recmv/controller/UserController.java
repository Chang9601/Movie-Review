package com.chang.recmv.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chang.recmv.model.User;
import com.chang.recmv.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	/*
	 * GET 방식: thymeleaf에서 / 없음 POST 방식: ModelAndView에서 redirect 사용 String을 json으로
	 * 전달: 쌍따옴표 제거, replaceAll("^\"|\"$", "")
	 */

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@Autowired
	private HttpSession session;

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원가입 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// http://localhost:8088/recmv/user/signup
	@GetMapping("/signup")
	public String signupGET() throws Exception {
		logger.info("User: signupGET() 시작");
		logger.info("User: signupGET() 끝");

		return "user/signup";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원가입 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// http://localhost:8088/recmv/user/login
	@GetMapping("/login")
	public String loginGet() throws Exception {
		logger.info("User: loginGET() 시작");
		logger.info("User: loginGET() 끝");

		return "user/login";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그아웃 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/logout")
	public String logout() throws Exception {
		logger.info("User: logout() 시작");
		session.invalidate();
		logger.info("User: logout() 끝");

		return "redirect:./login";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그아웃 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원조회 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/read")
	public String readUser(Model model) throws Exception {
		logger.info("User: readUser(Model model) 시작");
		String id = (String) session.getAttribute("id");
		if (id == null) return "redirect:./login";
		User user = service.readUser(id);
		logger.info("회원조회: " + user);
		model.addAttribute("user", user);
		logger.info("User: readUser(Model model) 끝");

		return "user/read";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원조회 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/update")
	public String updateUserGET(Model model) throws Exception {
		logger.info("User: updateUserGET(Model model) 시작");
		String id = (String) session.getAttribute("id");
		if (id == null) return "redirect:./login";
		User user = service.readUser(id);
		logger.info("회원수정 전: " + user);
		model.addAttribute("user", user);
		logger.info("User: updateUserGET(Model model) 끝");

		return "user/update";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/delete")
	public String deleteUserGET(Model model) throws Exception {
		logger.info("User: deleteUserGET(Model model) 시작");
		String id = (String) session.getAttribute("id");
		if (id == null) return "redirect:./login";
		User user = service.readUser(id);
		logger.info("회원탈퇴 전: " + user);
		model.addAttribute("user", user);
		logger.info("User: deleteUserGET(Model model) 끝");

		return "user/delete";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}