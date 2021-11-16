package com.chang.recmv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.Rev;

@Mapper
@Repository
public interface RevMapper {
	public void writeRev(Rev rev) throws Exception;
	public List<Rev> getAllRev(Criteria cri) throws Exception;
	public Integer getNumAllRev() throws Exception;
	public Integer readNum(String id) throws Exception;
	public String readId(Integer num) throws Exception;
	public Rev readRev(Integer num) throws Exception;
	public void updateRev(Rev rev, Integer num) throws Exception;
	public void deleteRev(Integer num) throws Exception;
}