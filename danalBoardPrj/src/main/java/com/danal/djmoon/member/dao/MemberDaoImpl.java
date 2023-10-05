package com.danal.djmoon.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.danal.djmoon.member.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao{

	//회원가입
	@Override
	public int insertMember(SqlSessionTemplate sst, MemberVo vo) {
		return sst.insert("memberMapper.insertMember", vo);
	}

	//로그인
	@Override
	public MemberVo selectOneMember(SqlSessionTemplate sst, MemberVo vo) {
		return sst.selectOne("memberMapper.selectOneById" , vo);
	}

	//아이디 중복확인
	@Override
	public int doubleCheckbyId(SqlSessionTemplate sst, String id) {
		return sst.selectOne("memberMapper.selectOneCheckId" , id);
	}


}
