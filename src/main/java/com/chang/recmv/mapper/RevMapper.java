package com.chang.recmv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.Movie;
import com.chang.recmv.model.Rev;

@Mapper
@Repository
public interface RevMapper {
	public void writeRev(Rev rev) throws Exception;
	public List<Rev> getRevs(Criteria cri) throws Exception;
	public Integer getNumRevs() throws Exception;
	public Rev readRev(Integer num) throws Exception;
	public void updateRev(Rev rev, Integer num) throws Exception;
	public void deleteRev(Integer num) throws Exception;
	public Integer readNum(String id) throws Exception;
	public String readId(Integer num) throws Exception;
	public Movie readMovie(Integer num) throws Exception;	
	public Double readRating(Integer num) throws Exception;
	public Double getAvgRating(String  title) throws Exception;
	public Integer getNumRevsByTitle(String movie) throws Exception;
	public void updateAvgRating(String title, Double rating) throws Exception;
	public List<Rev> getUserRevs(Integer userNum, Criteria cri) throws Exception;
	public Integer getNumUserRevs(Integer userNum) throws Exception;
	public Integer ckDupRev(Integer userNum, Integer movieNum) throws Exception;
	public void addRecom(Integer userNum, Integer revNum) throws Exception;
	public Integer ckDupRecom(Integer userNum, Integer revNum) throws Exception;	
	public void updateRecom(Integer num, Integer recom) throws Exception;
	public Integer getRecom(Integer num) throws Exception;
}