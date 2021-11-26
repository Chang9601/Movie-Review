package com.chang.recmv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chang.recmv.model.Movie;
import com.chang.recmv.service.MovieService;

@Controller
@RequestMapping("/movie/*")
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

	@Autowired
	private MovieService service;
	
	@GetMapping("/searchMovieDB")
	public String searchMovieDB(@RequestParam("query") String query, Model model) throws Exception {	
		logger.info("Movie: searchMovieDB(@RequestParam(\"query\") String query, Model model) 시작");
		List<Movie> movies = service.searchDB(query);
		model.addAttribute("movies", movies);
		logger.info("Movie: searchMovieDB(@RequestParam(\"query\") String query, Model model) 끝");

		return "movie/searchMovie";
	}		
}