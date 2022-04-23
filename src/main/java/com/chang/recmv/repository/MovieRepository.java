package com.chang.recmv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	boolean existsByLink(String link);
	Page<Movie> findByTitleContaining(@Param("query") String query, Pageable pageable);
}