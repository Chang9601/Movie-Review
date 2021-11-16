package com.chang.recmv.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chang.recmv.model.Movie;
import com.chang.recmv.service.MovieService;

@Controller
@RequestMapping("/movie/*")
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 

	@Autowired
	private MovieService service;
	
	@GetMapping("/search")
	public String searchGet(Model model, String title) throws Exception {
		logger.info("Movie: search() 시작");		
		List<Movie> movies = null;
		if(title != null) {
			movies = service.readMovies(title);
			title = title.replace("<b>", "");
			title = title.replace("</b>", "");
		}
		if(movies != null) {
			for(Movie movie : movies) {
				String actor = movie.getActor();
				actor = actor.replace("|", " ");	
				
				movie.setTitle(title);
				movie.setActor(actor);
			}
		}
		model.addAttribute("movies", movies);
		logger.info("Movie: search() 끝");		
		
		return "movie/search";
	}
}