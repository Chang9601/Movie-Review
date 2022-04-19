package com.chang.recmv.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chang.recmv.dto.MovieDto;
import com.chang.recmv.dto.MovieDto.Item;
import com.chang.recmv.service.MovieService;
import com.google.gson.Gson;

@RestController
public class MovieApiController {
	
	private final MovieService movieService;
	
	public MovieApiController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping("/movie")
	public String getMovie(/*@RequestParam String query*/) throws Exception {
		String clientId = "sTFs6vyQCtWYXkzcSbOY"; // 애플리케이션 클라이언트 아이디값"
		String clientSecret = "2e0N2GUS5k"; // 애플리케이션 클라이언트 시크릿값"

		String query = "전우치";
		
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + query; // json 결과
		
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);
		
		Gson gson = new Gson();
		
		MovieDto movieDto = gson.fromJson(responseBody, MovieDto.class);
		for(Item item : movieDto.getItems()) {
			String link = item.getLink();
			if(movieService.existsByLink(link)) // 이미 DB에 존재하면 스킵
				continue;
			
			String title = item.getTitle().replaceAll("(<b>|</b>)", "");			
			String plot = getPlot(link);
			String actor = item.getActor().replaceAll("\\|", " ");
			
			item.setTitle(title);
			item.setActor(actor);
			item.setPlot(plot);
			
			movieService.save(item);
		}
		
		return responseBody;
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
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

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
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
	
	// 링크로 줄거리 추출
	private static String getPlot(String link) throws Exception {
		Document document = Jsoup.connect(link).get();
		Element text = document.select("p.con_tx").first();
		String plot = null;
		
		if (text != null) 
			plot = text.text();

		return plot;
	}	
}