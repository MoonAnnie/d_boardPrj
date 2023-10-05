package com.danal.djmoon.movie.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.danal.djmoon.movie.vo.MoviePageVo;

@Repository
public class MovieDaoImpl implements MovieDao {

	//크롤링 결과 갯수 조회하기
	public int selectCnt(SqlSessionTemplate sst, MoviePageVo mvo) {
		
		if(mvo.getSearch() != null && !mvo.getSearch().isEmpty()) {
			if("0".equals(mvo.getSearchType())) { //제목으로 검색
				return sst.selectOne("boardMapper.selectCntByTitle" , mvo);
			}else if("1".equals(mvo.getSearchType())) { //작성자로 검색
				return sst.selectOne("boardMapper.selectCntByName" , mvo);
			}
		}
  		return sst.selectOne("boardMapper.selectCnt" , mvo);
	}

}
