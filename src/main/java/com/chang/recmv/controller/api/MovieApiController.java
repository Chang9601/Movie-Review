package com.chang.recmv.controller.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chang.recmv.model.Movie;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

@RestController
@RequestMapping("/api/movie/*")
public class MovieApiController {

	private static final Logger logger = LoggerFactory.getLogger(MovieApiController.class); 
	
	/*
	// 발급키
	private String kobisKey = "";
		
	// KOBIS 오픈 API Rest Client 호출
	private KobisOpenAPIRestService kobisService = new KobisOpenAPIRestService(kobisKey);
	
	@GetMapping("/movie/daily")
	public ResponseEntity<HashMap<String, Object>> getDaily() throws Exception {
		
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
		
		//ModelAndView mav = new ModelAndView("thymeleaf/movie/daily");
		
		// 일일 박스오피스 서비스 호출
		String dailyResp = kobisService.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd, wideAreaCd);		
		
		// JSON 라이브러리를 통해 처리
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> dailyRet = mapper.readValue(dailyResp, HashMap.class);
		logger.info("Movie: getDaily() 끝");
	
		return new ResponseEntity<HashMap<String,Object>>(dailyRet, HttpStatus.OK);
	}
	*/
	
	private String clientId = "";
	
	private String clientSecret = "";
	
	
	@GetMapping("/searchMovie")
	public ResponseEntity<JSONArray> searchMovie(@RequestParam("query") String query) throws Exception {
		logger.info("Movie: searchMovie(@RequestParam(\"query\") String query) 시작");
		
        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }	
      
        String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + query;    // json 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        String json = responseBody;
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject)parser.parse(json);
        JSONArray items = (JSONArray)obj.get("items");      
		List<Movie> list = new ArrayList<Movie>();
        
		for(int i = 0; i < items.size(); i++) {
			Movie movie = new Movie();
			JSONObject tmp = (JSONObject)items.get(i);
			movie.setTitle((String)tmp.get("title"));
			movie.setLink((String)tmp.get("link"));
			movie.setImage((String)tmp.get("image"));
			movie.setActor((String)tmp.get("actor"));
			
			System.out.println("제목: " + tmp.get(("title")));
			System.out.println("링크: " + tmp.get(("link")));
			System.out.println("이미지: " + tmp.get(("image")));
			System.out.println("배우: " + tmp.get(("actor")));
			
			if(movie != null)
				list.add(movie);
		}
				
        logger.info("Movie: searchMovie(@RequestParam(\"query\") String query) 끝");  
		//System.out.println("영화: " + items);
		
		
        
        return new ResponseEntity<JSONArray>(items, HttpStatus.OK);
	}
	
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }	
}