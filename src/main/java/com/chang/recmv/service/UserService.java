package com.chang.recmv.service;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chang.recmv.mapper.UserMapper;
import com.chang.recmv.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	public void addUser(User user) throws Exception {
		mapper.addUser(user);
	}
	
	public String ckDupId(String id) throws Exception {
		return mapper.ckDupId(id);
	}
	
	public String ckLogin(User user) throws Exception {
		return mapper.ckLogin(user);
	}
	
	public User readUser(String id) throws Exception {
		return mapper.readUser(id);
	}
	
	public void updateUser(User user, String pw) throws Exception {
		mapper.updateUser(user, pw);
	}
	
	public void deleteRev(String id) throws Exception {
		mapper.deleteRev(id);
	}
	
	public void deleteUser(User user) throws Exception {
		mapper.deleteUser(user);
	}
	
	public List<User> getAllUser(String id) throws Exception {
		return mapper.getAllUser(id);
	}
}
