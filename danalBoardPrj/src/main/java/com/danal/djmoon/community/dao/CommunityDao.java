package com.danal.djmoon.community.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.danal.djmoon.community.page.PageVo;
import com.danal.djmoon.community.vo.BoardCmtVo;
import com.danal.djmoon.community.vo.BoardPageVo;
import com.danal.djmoon.community.vo.BoardVo;
import com.danal.djmoon.member.vo.MemberVo;

public interface CommunityDao {
	
	//게시글 갯수 조회
	//public int selectCnt(Connection conn, BoardPageVo bpvo);
	public int selectCnt(SqlSessionTemplate sst, BoardPageVo bpvo);
	
	//게시글 조회
	//public List<BoardVo> selectList(Connection conn, BoardPageVo bpvo, PageVo pv);
	public List<BoardVo> selectList(SqlSessionTemplate sst, BoardPageVo bpvo, PageVo pv);

	//게시글 작성
	//public int write(Connection conn, BoardVo vo, MemberVo mvo);
	public int write(SqlSessionTemplate sst, BoardVo vo, MemberVo mvo);
	
	//게시글 상세 조회 시 조회수 증가
	//public int increaseHit(String bno, Connection conn);
	public int increaseHit(String bno, SqlSessionTemplate sst);
	
	//게시글 상세 조회
	//public BoardVo selectInfoDetail(String bno, Connection conn);
	public BoardVo selectInfoDetail(String bno, SqlSessionTemplate sst);
	
	//게시글 수정 시 작성된 게시글 내용 불러오기
	//public BoardVo selectInfoOne(Connection conn, String no);
	public BoardVo selectInfoOne(SqlSessionTemplate sst, String no);
	
	//게시글 수정
	//public int updateInfoOne(Connection conn, BoardVo vo, String bno);
	public int updateInfoOne(SqlSessionTemplate sst, BoardVo vo, String bno);
	
	//게시글 삭제
	//public int deleteInfoOne(Connection conn, String bno);
	public int deleteInfoOne(SqlSessionTemplate sst, String bno);
	
	//댓글 작성
	//public int insertCmt(BoardCmtVo cmtvo, Connection conn);
	public int insertCmt(BoardCmtVo cmtvo, SqlSessionTemplate sst);
	
	//댓글 조회
	//public List<BoardCmtVo> selectCommCmt(String bno, Connection conn);
	public List<BoardCmtVo> selectCommCmt(String bno, SqlSessionTemplate sst);
	
	//댓글 삭제
	//public int cmtDelete(String cmtNo, Connection conn);
	public int cmtDelete(String cmtNo, SqlSessionTemplate sst);
}

