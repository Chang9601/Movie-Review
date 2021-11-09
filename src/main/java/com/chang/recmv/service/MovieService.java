package com.chang.recmv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chang.recmv.mapper.MovieMapper;
import com.chang.recmv.model.Movie;

@Service
public class MovieService {
	
	@Autowired
	private MovieMapper mapper;
	
	public void addMovie(Movie movie) throws Exception {
		mapper.addMovie(movie);
	}
	
	public Movie readMovie(String title) throws Exception {
		return mapper.readMovie(title);
	}
}
