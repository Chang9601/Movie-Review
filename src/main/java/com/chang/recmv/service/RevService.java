package com.chang.recmv.service;


//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chang.recmv.mapper.RevMapper;
import com.chang.recmv.model.Rev;

@Service
public class RevService {
	
	@Autowired
	private RevMapper mapper;
	
	public void writeRev(Rev rev) throws Exception {
		mapper.writeRev(rev);
	}
}