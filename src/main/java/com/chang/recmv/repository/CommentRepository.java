package com.chang.recmv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
