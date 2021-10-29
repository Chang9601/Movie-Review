package com.chang.recmv.mapper;

//import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.User;

@Mapper
@Repository
public interface UserMapper {
	//public List<User> getAll() throws Exception;
	public void addUser(User user) throws Exception;
	public String ckDupId(String id) throws Exception;
}
