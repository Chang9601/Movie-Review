package com.chang.recmv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.User;

@Mapper
@Repository
public interface AdminMapper {
	public List<User> adminGetUsers(Criteria cri, String id) throws Exception;
	public Integer adminGetNumUsers(String id) throws Exception;
	public User adminReadUser(Integer num) throws Exception;
	public void adminUpdateUser(User user, Integer num) throws Exception;
	public void adminDeleteUser(Integer num) throws Exception;
	public void adminDeleteRevs(Integer userNum) throws Exception;
}