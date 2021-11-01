package com.chang.recmv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Rev;

@Mapper
@Repository
public interface RevMapper {
	public void writeRev(Rev rev) throws Exception;
	public List<Rev> getAllRev() throws Exception;
	public Rev readRev(Integer num) throws Exception;
	public void updateRev(Rev rev) throws Exception;
}