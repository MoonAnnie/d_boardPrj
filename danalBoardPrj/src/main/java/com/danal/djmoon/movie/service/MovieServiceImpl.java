package com.danal.djmoon.movie.service;

import java.sql.Connection;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danal.djmoon.movie.dao.MovieDao;
import com.danal.djmoon.movie.vo.MoviePageVo;

import static com.danal.djmoon.common.SQLiteTemplate.*;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao dao;
	
	@Autowired
	private SqlSessionTemplate sst;
	
	//크롤링 결과 갯수 조회하기
	public int selectCnt(MoviePageVo mvo) {
		
		Connection conn = createDB();
		
		int result = dao.selectCnt(sst, mvo);
		
		close(conn);
		
		return result;
	}

}
