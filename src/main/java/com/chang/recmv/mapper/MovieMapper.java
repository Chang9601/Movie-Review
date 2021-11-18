package com.chang.recmv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.Movie;

@Mapper
@Repository
public interface MovieMapper {
	public void addMovie(Movie movie) throws Exception;
	public Integer ckDupMovie(String title) throws Exception;
	public Movie readMovie(String title) throws Exception;
	public List<Movie> getMovies(Criteria cri) throws Exception;
	public Integer getNumMovies() throws Exception;
}