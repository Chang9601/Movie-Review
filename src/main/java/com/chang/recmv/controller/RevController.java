package com.chang.recmv.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.Movie;
import com.chang.recmv.model.Paging;
import com.chang.recmv.service.RevService;

@Controller
@RequestMapping("/rev/*")
public class RevController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RevService service;

	@Autowired
	private HttpSession session;

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰목록 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/main")
	public String main(Model model, Criteria cri) throws Exception {
		logger.info("Rev: main(Model model, Criteria cri) 시작");
		Paging page = new Paging(cri, service.getNumRevs());
		model.addAttribute("page", page);
		model.addAttribute("revs", service.getRevs(cri));
		logger.info("페이징: " + page);
		logger.info("Rev: main(Model model, Criteria cri) 끝");

		return "rev/main";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰목록 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰작성 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/write/{num}")
	public String writeGET(@PathVariable Integer num, Model model) throws Exception {
		logger.info("Rev: writeGET(@PathVariable Integer num, Model model) 시작");
		String id = (String) session.getAttribute("id");
		if (id == null) return "redirect:/user/login";
		// 키에 해당하는 영화
		Movie movie = service.readMovie(num);
		// 로그인된 회원의 아이디
		model.addAttribute("id", id);
		// 로그인된 회원의 키
		model.addAttribute("userNum", service.readNum(id));
		// 영화제목
		model.addAttribute("movie", movie.getTitle());
		// 영화 이미지
		model.addAttribute("image", movie.getImage());
		// 영화의 키
		model.addAttribute("movieNum", num);
		logger.info("Rev: writeGET(@PathVariable Integer num, Model model) 끝");

		return "rev/write";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰작성 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰조회 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/read/{num}")
	public String readRev(@PathVariable Integer num, Model model, Integer currentPageNum) throws Exception {
		logger.info("Rev : readRev(@PathVariable Integer num, Model model, Integer currentPageNum) 시작");
		String id = (String) session.getAttribute("id");
		// 리뷰를 작성한 회원의 번호
		Integer userNum = service.readRev(num).getUserNum();
		// 현재 로그인한 회원의 번호
		model.addAttribute("num", service.readNum(id));
		// 리뷰를 작성한 회원의 아이디
		model.addAttribute("id", service.readId(userNum));
		model.addAttribute("currentPageNum", currentPageNum);
		// 키에 해당하는 리뷰
		model.addAttribute("rev", service.readRev(num));
		logger.info("리뷰조회: " + service.readRev(num));
		logger.info("Rev : readRev(@PathVariable Integer num, Model model, Integer currentPageNum) 끝");

		return "rev/read";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰조회 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/update/{num}")
	public String updateRevGET(@PathVariable Integer num, Model model) throws Exception {
		logger.info("Rev: updateRevGET(@PathVariable Integer num, Model model) 시작");
		String id = (String) session.getAttribute("id");
		if (id == null) return "redirect:/user/login";
		// 리뷰를 작성한 회원의 번호
		Integer userNum = service.readRev(num).getUserNum();
		// 리뷰를 작성한 회원의 아이디
		model.addAttribute("id", service.readId(userNum));
		model.addAttribute("rev", service.readRev(num));
		logger.info("리뷰수정 전: " + service.readRev(num));
		logger.info("Rev: updateRevGET(@PathVariable Integer num, Model model) 끝");

		return "rev/update";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 리뷰수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원 1명 리뷰목록
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@GetMapping("/list/{userNum}")
	public String getUserRevs(@PathVariable Integer userNum, Model model, Criteria cri) throws Exception {
		logger.info("Rev: getUserRevs(@PathVariable Integer userNum, Model model, Criteria cri) 시작");
		Paging page = new Paging(cri, service.getNumUserRevs(userNum));
		model.addAttribute("page", page);
		model.addAttribute("revs", service.getUserRevs(userNum, cri));
		model.addAttribute("userNum", userNum);
		logger.info("Rev: getUserRevs(@PathVariable Integer userNum, Model model, Criteria cri) 끝");

		return "rev/list";
	}
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 회원 1명 리뷰수정
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}