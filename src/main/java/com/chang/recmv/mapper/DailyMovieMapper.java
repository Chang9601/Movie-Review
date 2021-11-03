package com.chang.recmv.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DailyMovieMapper {
	public String getDailyBoxOffice() throws Exception;
	
}
