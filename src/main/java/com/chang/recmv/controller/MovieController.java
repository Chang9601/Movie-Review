package com.chang.recmv.controller;

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
		Movie movie = null;
		
		if(title != null)
			movie = service.readMovie(title);
		
		if(movie != null) {
			title = title.replace("<b>", "");
			title = title.replace("</b>", "");
			
			String actor = movie.getActor();
			actor = actor.replace("|", " ");	
			
			movie.setTitle(title);
			movie.setActor(actor);
	
			model.addAttribute("movie", movie);
		}
		
		logger.info("Movie: search() 끝");		
		
		return "movie/search";
	}
}
