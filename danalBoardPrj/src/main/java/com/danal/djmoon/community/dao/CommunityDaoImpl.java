package com.danal.djmoon.community.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.danal.djmoon.community.page.PageVo;
import com.danal.djmoon.community.vo.BoardCmtVo;
import com.danal.djmoon.community.vo.BoardPageVo;
import com.danal.djmoon.community.vo.BoardVo;
import com.danal.djmoon.member.vo.MemberVo;

@Repository
public class CommunityDaoImpl implements CommunityDao {

	//게시판 갯수 조회
	@Override
	public int selectCnt(SqlSessionTemplate sst, BoardPageVo bpvo) {
		
		if(bpvo.getSearch() != null && !bpvo.getSearch().isEmpty()) {
			if("0".equals(bpvo.getSearchType())) { //제목으로 검색
				return sst.selectOne("boardMapper.selectCntByTitle" , bpvo);
			}else if("1".equals(bpvo.getSearchType())) { //작성자로 검색
				return sst.selectOne("boardMapper.selectCntByName" , bpvo);
			}
		}
  		return sst.selectOne("boardMapper.selectCnt" , bpvo);
	}
	
	//게시판 조회
	@Override
	public List<BoardVo> selectList(SqlSessionTemplate sst, BoardPageVo bpvo, PageVo pv){
		
	    int currentPage = pv.getCurrentPage();
	    int boardLimit = pv.getBoardLimit();
	    int offset = (currentPage - 1) * boardLimit;
	    
	    Map<String, Object> params = new HashMap<>();
	    params.put("search", bpvo.getSearch());
	    params.put("searchType", bpvo.getSearchType());
	    params.put("boardLimit", boardLimit);
	    params.put("offset", offset);
	    
	    if (bpvo.getSearch() != null && !bpvo.getSearch().isEmpty()) {
        if ("0".equals(bpvo.getSearchType())) { //제목으로 검색
        	return sst.selectList("boardMapper.selectListByTitle" , params);
        	}else if("1".equals(bpvo.getSearchType())) { //작성자로 검색
        	return sst.selectList("boardMapper.selectListByName" , params);
        	}
	    }
	    return sst.selectList("boardMapper.selectList", params);
	}

	//게시글 작성
	@Override
	public int write(SqlSessionTemplate sst, BoardVo vo, MemberVo mvo) {
		return sst.insert("boardMapper.write", vo);
	}

	//게시글 상세 조회 시 조회수 증가
	@Override
	public int increaseHit(String bno, SqlSessionTemplate sst) {
		return sst.update("boardMapper.increaseHit", bno);
	}
	
	//게시글 상세 조회
	@Override
	public BoardVo selectInfoDetail(String bno, SqlSessionTemplate sst) {
		return sst.selectOne("boardMapper.selectInfoOne", bno);
	}

	@Override
	public BoardVo selectInfoOne(SqlSessionTemplate sst, String bno) {
		int result = sst.update("boardMapper.increaseHit", bno);
		return sst.selectOne("boardMapper.selectInfoOne", bno);
	}

	//게시글 수정
	@Override
	public int updateInfoOne(SqlSessionTemplate sst, BoardVo vo, String bno) {
		return sst.insert("boardMapper.updateInfoOne", vo);
	}

	//게시글 삭제
	@Override
	public int deleteInfoOne(SqlSessionTemplate sst, String bno) {
		return sst.update("boardMapper.deleteInfoOne", bno);
	}

	//댓글 작성
	@Override
	public int insertCmt(BoardCmtVo cmtvo, SqlSessionTemplate sst) {
		return sst.insert("boardMapper.insertCmt", cmtvo);
	}
	
	//댓글 조회
	@Override
	public List<BoardCmtVo> selectCommCmt(String bno, SqlSessionTemplate sst){
		return sst.selectList("boardMapper.selectCommCmt", bno);
	}

	//댓글 삭제
	@Override
	public int cmtDelete(String cmtNo, SqlSessionTemplate sst) {
		return sst.update("boardMapper.cmtDelete", cmtNo);
	}
	
}
