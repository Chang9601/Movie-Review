package com.chang.recmv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.User;

@Mapper
@Repository
public interface AdminMapper {
	public List<User> getUsers(Criteria cri, String id) throws Exception;
	public Integer getNumUsers(String id) throws Exception;
}