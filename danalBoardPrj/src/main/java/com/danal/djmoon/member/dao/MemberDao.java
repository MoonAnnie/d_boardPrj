package com.danal.djmoon.member.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.danal.djmoon.member.vo.MemberVo;

public interface MemberDao {

	//회원가입
	//public int insertMember(Connection conn, MemberVo vo);
	public int insertMember(SqlSessionTemplate sst, MemberVo vo);
	
	//로그인
	//public MemberVo selectOneMember(Connection conn, MemberVo vo);
	public MemberVo selectOneMember(SqlSessionTemplate sst, MemberVo vo);

	//아이디 중복확인
	//public int doubleCheckbyId(Connection conn, String id);
	public int doubleCheckbyId(SqlSessionTemplate sst, String id);

}
