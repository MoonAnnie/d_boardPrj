package com.danal.djmoon.community.service;

import java.util.List;

import com.danal.djmoon.community.page.PageVo;
import com.danal.djmoon.community.vo.BoardCmtVo;
import com.danal.djmoon.community.vo.BoardPageVo;
import com.danal.djmoon.community.vo.BoardVo;
import com.danal.djmoon.member.vo.MemberVo;

public interface CommunityService {
	
	//게시글 갯수 조회하기
	public int selectCnt(BoardPageVo bpvo);

	//게시글 목록
	public List<BoardVo> selectList(BoardPageVo bpvo, PageVo pv);

	//게시글 작성
	public int write(BoardVo vo, MemberVo mvo);
	
	//게시글 상세 조회
	public BoardVo infoDetail(String bno);

	//게시글 수정 시 작성된 게시글 내용 불러오기
	public BoardVo selectInfoOne(String no);

	//게시글 수정
	public int infoEdit(BoardVo vo, String bno);

	//게시글 삭제
	public int deleteInfo(String no);

	//댓글 작성
	public int writeCmt(BoardCmtVo cmtvo);
	
	//댓글 조회
	public List<BoardCmtVo> selectCommCmt(String bno);
	
	//댓글 삭제
	public int cmtDelete(String cmtNo);

	//조회수 제한
	public BoardVo increaseViewCount(String bno);
}
