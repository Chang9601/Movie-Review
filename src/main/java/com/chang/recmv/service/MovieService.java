package com.chang.recmv.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chang.recmv.dto.MovieDto.Item;
import com.chang.recmv.model.Movie;
import com.chang.recmv.repository.MovieRepository;

@Service
public class MovieService {
	private final MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	@Transactional(readOnly = true)
	public boolean existsByLink(String link) {
		return movieRepository.existsByLink(link);
	}
	
	@Transactional
	public void save(Item movieDto) {
		Movie movie = movieDto.toEntity();
		
		movieRepository.save(movie);
	}

	@Transactional(readOnly = true)
	public Page<Movie> findAll(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 5);
		
		return movieRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Movie findById(int id) {
		return movieRepository.findById(id).orElseThrow(() ->{
			return new IllegalStateException("영화 찾기 실패: 아이디 없음");
		});
	}
	
	@Transactional(readOnly = true)
	public Page<Movie> findByTitleContaining(String query, Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 5);		
		
		return movieRepository.findByTitleContaining(query, pageable);
	}
}