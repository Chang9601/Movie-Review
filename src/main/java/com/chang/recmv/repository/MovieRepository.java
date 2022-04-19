package com.chang.recmv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	boolean existsByLink(String link);
}