package com.chang.recmv.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Movie;

@Mapper
@Repository
public interface MovieMapper {
	public void addMovie(Movie movie) throws Exception;
	public Movie readMovie(String title) throws Exception;
}