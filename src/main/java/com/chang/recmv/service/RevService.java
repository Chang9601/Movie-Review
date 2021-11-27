package com.chang.recmv.service;


import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chang.recmv.mapper.RevMapper;
import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.Movie;
import com.chang.recmv.model.Rev;

@Service
public class RevService {
	
	@Autowired
	private RevMapper mapper;
	
	public void writeRev(Rev rev) throws Exception {
		mapper.writeRev(rev);
	}
	
	public List<Rev> getRevs(Criteria cri) throws Exception {
		return mapper.getRevs(cri);
	}
	
	public Integer getNumRevs() throws Exception {
		return mapper.getNumRevs();
	}
	
	public Rev readRev(Integer num) throws Exception {
		return mapper.readRev(num);
	}

	public void updateRev(Rev rev, Integer num) throws Exception {
		mapper.updateRev(rev, num);
	}
	
	public void deleteRev(Integer num) throws Exception {
		mapper.deleteRev(num);
	}
	
	public Integer readNum(String id) throws Exception {
		return mapper.readNum(id);
	}
	
	public String readId(Integer num) throws Exception {
		return mapper.readId(num);
	}
	
	public Movie readMovie(Integer num) throws Exception {
		return mapper.readMovie(num);
	}	
	
	public Double readRating(Integer num) throws Exception {
		return mapper.readRating(num);
	}
	
	public Double getAvgRating(String title) throws Exception {
		return mapper.getAvgRating(title);
	}
	
	public Integer getNumRevsByTitle(String movie) throws Exception {
		return mapper.getNumRevsByTitle(movie);
	}
	
	public void updateAvgRating(String title, Double rating) throws Exception {
		mapper.updateAvgRating(title, rating);
	}
	
	public List<Rev> getUserRevs(Integer userNum, Criteria cri) throws Exception {
		return mapper.getUserRevs(userNum, cri);
	}
	
	public Integer getNumUserRevs(Integer userNum) throws Exception {
		return mapper.getNumUserRevs(userNum);
	}

	public Integer ckDupRev(Integer userNum, Integer movieNum) throws Exception {
		return mapper.ckDupRev(userNum, movieNum);	
	}
	
	public void addRecom(Integer userNum, Integer revNum) throws Exception {
		mapper.addRecom(userNum, revNum);
	}
	
	public Integer ckDupRecom(Integer userNum, Integer revNum) throws Exception {
		return mapper.ckDupRecom(userNum, revNum);
	}
	
	public void updateRecom(Integer num, Integer recom) throws Exception {
		mapper.updateRecom(num, recom);
	}
	
	public Integer getRecom(Integer num) throws Exception {
		return mapper.getRecom(num);
	}
}