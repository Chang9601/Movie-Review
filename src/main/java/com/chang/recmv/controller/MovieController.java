package com.chang.recmv.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

@RestController
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 
	
	// 발급키
	private String key = "957ea9693381e2fd1ffb2a2691ca38be";
		
	// KOBIS 오픈 API Rest Client 호출
	private KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
	
	@GetMapping("/movie/daily")
	public ModelAndView getDaily() throws Exception {
		
		logger.info("Movie: getDaily() 시작");
		
		// 조회날짜: yyyymmdd 형식
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String targetDt = LocalDate.now().minusDays(1).format(formatter);
		
		// 결과 ROW(default: "10", 최대: "10")
		String itemPerPage = "10";
		
		// 다양성/상업영화("Y": 다양성, "N": 상업, default: 전체")
		String multiMovieYn = "";
		
		// 한국/외국영화("K": 한국, "F": 외국, default: 전체)
		String repNationCd = "";
		
		// 상영지역(default: 전체)
		String wideAreaCd = "";
		
		ModelAndView mav = new ModelAndView("thymeleaf/movie/daily");
		
		// 일일 박스오피스 서비스 호출
		String dailyResp = service.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd, wideAreaCd);		
		
		// Json 라이브러리를 통해 처리
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> dailyRet = mapper.readValue(dailyResp, HashMap.class);
		mav.addObject("daily", dailyRet);
		logger.info("Movie: getDaily() 끝");
	
		return mav;
	}
}
