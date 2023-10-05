package com.danal.djmoon.movie.service;

import com.danal.djmoon.movie.vo.MoviePageVo;

public interface MovieService {

	//크롤링 결과 갯수 조회하기
	public int selectCnt(MoviePageVo mvo);

}
