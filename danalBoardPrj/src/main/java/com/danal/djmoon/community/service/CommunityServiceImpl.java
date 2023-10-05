package com.danal.djmoon.community.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danal.djmoon.community.dao.CommunityDao;
import com.danal.djmoon.community.page.PageVo;
import com.danal.djmoon.community.vo.BoardCmtVo;
import com.danal.djmoon.community.vo.BoardPageVo;
import com.danal.djmoon.community.vo.BoardVo;
import com.danal.djmoon.member.vo.MemberVo;
import com.danal.djmoon.member.vo.MemberVo2;

import static com.danal.djmoon.common.SQLiteTemplate.*;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	private CommunityDao dao;
	
	@Autowired
	private SqlSessionTemplate sst;

	//게시글 갯수 조회
	@Override
	public int selectCnt(BoardPageVo bpvo) {
		
		//커넥션 준비
		//SQL
		//트랜잭션 처리, 자원반납
		
		//게시글 갯수 조회
		Connection conn = createDB();

		int result = dao.selectCnt(sst, bpvo);
		
		close(conn);
		
		return result;
		
		//return dao.selectCnt(bpvo, sst);
	}
	
	//게시글  조회
	@Override
	public List<BoardVo> selectList(BoardPageVo bpvo, PageVo pv) {
		
		//커넥션 준비
		//SQL
		//트랜잭션, 자원반납
		
		Connection conn = createDB();
		
		List<BoardVo> list = dao.selectList(sst, bpvo, pv);
		
		close(conn);
		
		return list;
		
		//return dao.selectList(sst, bpvo, pv);
	}
	
	//게시글 작성 (이미지도 함께)
	@Override
	//@Transactional //두개이상의 sql문을 실행할 때 두 개 전부 성공적으로 실행되어야 커밋됨
	public int write(BoardVo vo, MemberVo mvo) {
		
		//커넥션 준비
		//SQL
		//트랜잭션 처리, 자원반납
		
		Connection conn = createDB();
		int result = dao.write(sst, vo, mvo);
		
		return result;
		
		//int result = dao.write(sst, vo);
		//return 1;
	}

	//게시글 상세 조회 (#1. 먼저 조회수 증가시키기)
	@Override
	@Transactional
	public BoardVo infoDetail(String bno) {
		
		Connection conn = createDB();

		//조회수 증가
		//int result = dao.increaseHit(bno, sst);
		
		return dao.selectInfoDetail(bno, sst);
	}
	
	//게시글 수정 시 작성된 게시글 내용 불러오기
	@Override
	public BoardVo selectInfoOne(String no) {
		
		Connection conn = createDB();
		
		//return dao.selectInfoOne(conn, no);
		
		return dao.selectInfoOne(sst, no);
	}

	//게시글 수정
	@Override
	public int infoEdit(BoardVo vo, String bno) {
		
		Connection conn = createDB();
		
		int result =dao.updateInfoOne(sst, vo, bno);
		
		close(conn);
		
		return result;
		
		//return dao.updateInfoOne(sst, vo, bno);
	}

	//게시글 삭제
	@Override
	public int deleteInfo(String bno) {
		
		Connection conn = createDB();
		
		int result = dao.deleteInfoOne(sst, bno);
		
		close(conn);
		
		return result;
		
		//return dao.deleteInfoOne(sst, no);
	}

	//댓글 작성
	@Override
	public int writeCmt(BoardCmtVo cmtvo) {

		Connection conn = createDB();
		
		int result = dao.insertCmt(cmtvo, sst);
		
		close(conn);
		
		return result;
		//return dao.insertCmt(cmtvo, sst);
	}

	//댓글 조회
	@Override
	public List<BoardCmtVo> selectCommCmt(String bno) {
		
		Connection conn = createDB();
		
		List<BoardCmtVo> list = dao.selectCommCmt(bno, sst);
		
		close(conn);
		
		return list;
		
		//return dao.selectCommCmt(bno, sst);
	}
	
	//댓글 삭제
	@Override
	public int cmtDelete(String cmtNo) {
		
		Connection conn = createDB();
		
		int result =dao.cmtDelete(cmtNo, sst);
		
		close(conn);
		
		return result;
		
		//return dao.cmtDelete(cmtNo, sst);
	}

	//조회수 제한
	@Override
	public BoardVo increaseViewCount(String bno) {
		Connection conn = createDB();

		//조회수 증가
		int result = dao.increaseHit(bno, sst);
		
		return dao.selectInfoDetail(bno, sst);
	}
}
