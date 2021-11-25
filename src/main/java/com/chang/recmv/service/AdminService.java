package com.chang.recmv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chang.recmv.mapper.AdminMapper;
import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.User;

@Service
public class AdminService {
	
	@Autowired
	private AdminMapper mapper;
	
	public List<User> adminGetUsers(Criteria cri, String id) throws Exception {
		return mapper.adminGetUsers(cri, id);
	}
	
	public Integer adminGetNumUsers(String id) throws Exception {
		return mapper.adminGetNumUsers(id);
	}
	
	public User adminReadUser(Integer num) throws Exception {
		return mapper.adminReadUser(num);
	}

	public void adminUpdateUser(User user, Integer num) throws Exception {
		mapper.adminUpdateUser(user, num);
	}
	
	public void adminDeleteUser(Integer num) throws Exception {
		mapper.adminDeleteUser(num);
	}
	
	public void adminDeleteRevs(Integer userNum) throws Exception {
		mapper.adminDeleteRevs(userNum);
	}
}