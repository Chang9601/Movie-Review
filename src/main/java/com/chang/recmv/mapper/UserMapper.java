package com.chang.recmv.mapper;

import java.util.List;

//import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.User;

@Mapper
@Repository
public interface UserMapper {
	public void addUser(User user) throws Exception;
	public String ckDupId(String id) throws Exception;
	public String ckLogin(User user) throws Exception;
	public User readUser(String id) throws Exception;
	public void updateUser(User user) throws Exception;
	public void deleteRev(String id) throws Exception;		
	public void deleteUser(User user) throws Exception;
	public List<User> getAllUser(String id) throws Exception;
}