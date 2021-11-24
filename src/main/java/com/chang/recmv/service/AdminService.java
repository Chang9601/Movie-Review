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
	
	public List<User> getUsers(Criteria cri, String id) throws Exception {
		return mapper.getUsers(cri, id);
	}
	
	public Integer getNumUsers(String id) throws Exception {
		return mapper.getNumUsers(id);
	}
	
}