package com.chang.recmv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	@Query(value = "UPDATE review SET view = view + 1 WHERE review_id = :id", nativeQuery = true)
	@Modifying // 삽입, 수정, 삭제 쿼리 작성 시 필요
	int updateView(@Param("id") int id);
	
	// Containing: LIKE 검색 가능(즉, %{query}%)
	Page<Review> findByTitleContaining(@Param("query") String query, Pageable pageable);
}