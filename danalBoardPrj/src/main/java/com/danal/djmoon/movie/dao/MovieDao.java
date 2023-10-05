package com.danal.djmoon.movie.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.danal.djmoon.movie.vo.MoviePageVo;

public interface MovieDao {

	//크롤링 결과 갯수 조회하기
	public int selectCnt(SqlSessionTemplate sst, MoviePageVo mvo);

}
