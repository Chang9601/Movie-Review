package com.chang.recmv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}