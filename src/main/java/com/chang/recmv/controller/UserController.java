package com.chang.recmv.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chang.recmv.model.User;
import com.chang.recmv.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	/* GET 방식: thymeleaf에서 / 없음
	 * POST 방식: ModelAndView에서 redirect 사용
	 * String을 json으로 전달: 쌍따옴표 제거, replaceAll("^\"|\"$", "")
	 * 
	 * 
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
	private UserService service;
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원가입 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// http://localhost:8088/recmv/user/signup
	@GetMapping("/signup")
	public String signupGet() throws Exception {
		logger.info("User: signupGet() 시작");
		logger.info("User: signupGet() 끝");
			
		return "user/signup";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원가입 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// http://localhost:8088/recmv/user/login
	@GetMapping("/login")
	public String loginGet() throws Exception {
		logger.info("User: loginGet() 시작");		
		logger.info("User: loginGet() 끝");
		
		return "user/login";
	}
	
	/*@PostMapping("/user/login")
	public ModelAndView loginPOST(User user, HttpSession session) throws Exception {
		logger.info("User: loginPOST(User user, HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("redirect:./main");
		logger.info("로그인: " + user);
		String id = service.ckLogin(user);
		if(id == null) {
			mav.setViewName("redirect:./login");
			return mav;
		}
		session.setAttribute("id", id);
		logger.info("User: loginPOST(User user, HttpSession session) 끝");
		
		return mav;
	}
	
	@PostMapping("/user/ckUser")
	public String ckUser(@RequestBody User user) throws Exception {
		logger.info("User: ckUser(User user) 시작");
		System.out.println("사용자: " + user);
		String id = service.ckLogin(user); 
		logger.info("User: ckUser(User user) 끝");
		
		return id;
	}*/
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 사용자 메인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// http:localhost:8088/recmv/main
	@GetMapping("/main") // -> main의 main으로 변환
	public String main(HttpSession session, Model model) throws Exception {		
		logger.info("User: main(HttpSession session, Model model) 시작");
		String id = (String)session.getAttribute("id");
		if(id == null) 
			return "redirect:./login";
		model.addAttribute("id", id);
		logger.info("User: main(HttpSession session, Model model) 끝");		
		
		return "user/main";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 사용자 메인 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그아웃 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/user/logout")
	public String logout(HttpSession session) throws Exception {
		logger.info("User: logout(HttpSession session) 시작");
		session.invalidate();
		logger.info("User: logout(HttpSession session) 끝");
		
		return "redirect:./login";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 로그아웃 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원조회 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/user/read")
	public ModelAndView readUser(HttpSession session) throws Exception {		
		logger.info("User: readUser(HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/user/read");
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:./login");
			return mav;			
		}
		User user = service.readUser(id);
		logger.info("조회: " + user);
		mav.addObject("user", user);
		logger.info("User: readUser(HttpSession session) 끝");
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원조회 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/user/update")
	public ModelAndView updateUserGET(HttpSession session) throws Exception {	
		logger.info("User: updateUserGET(HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/user/update");
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:./login");
			return mav;			
		}
		User user = service.readUser(id);
		logger.info("수정 전: " + user);
		mav.addObject("user", user);
		logger.info("User: updateUserGET(HttpSession session) 끝");
		
		return mav;
	}

	@PostMapping("/user/update")
	public ModelAndView updateUserPOST(User user) throws Exception {		
		logger.info("User: updateUserPOST(HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("redirect:./main");
		service.updateUser(user);
		logger.info("수정 후: " + user);
		logger.info("User: updateUserPOST(HttpSession session) 끝");
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/user/delete")
	public ModelAndView deleteUserGET(HttpSession session) throws Exception {
		logger.info("User: deleteUserGET(HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/user/delete");
		String id = (String)session.getAttribute("id");
		if(id == null) {
			mav.setViewName("redirect:./login");
			return mav;			
		}
		User user = service.readUser(id);
		mav.addObject("user", user);
		logger.info("User: deleteUserGET(HttpSession session) 시작");
		
		return mav;
	}
	
	@PostMapping("/user/delete")
	public ModelAndView deleteUserPOST(User user, HttpSession session) throws Exception {
		logger.info("User: deleteUserPOST(String id) 시작");
		ModelAndView mav = new ModelAndView("redirect:./login");
		String id = (String)session.getAttribute("id");
		user.setId(id);
		logger.info("삭제: " + user);
		service.deleteRev(id);
		service.deleteUser(user);
		session.invalidate();
		logger.info("User: deleteUserPOST(String id) 시작");
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원탈퇴 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원목록 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/user/all")
	public ModelAndView getAllUser(HttpSession session) throws Exception {
		logger.info("User: getAllUser(HttpSession session) 시작");
		ModelAndView mav = new ModelAndView("thymeleaf/user/all");
		String id = (String)session.getAttribute("id");		
		if(id == null || !id.equals("admin")) {
			mav.setViewName("redirect:./login");
			return mav;			
		}		
		mav.addObject("users", service.getAllUser(id));
		logger.info("User: getAllUser(HttpSession session) 끝");
		
		return mav;
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원목록 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
