package com.chang.recmv.service;


import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chang.recmv.mapper.RevMapper;
import com.chang.recmv.model.Criteria;
import com.chang.recmv.model.Rev;

@Service
public class RevService {
	
	@Autowired
	private RevMapper mapper;
	
	public void writeRev(Rev rev) throws Exception {
		mapper.writeRev(rev);
	}
	
	public List<Rev> getAllRev(Criteria cri) throws Exception {
		return mapper.getAllRev(cri);
	}
	
	public Integer getNumAllRev() throws Exception {
		return mapper.getNumAllRev();
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
}
