package com.danal.djmoon.member.service;

import com.danal.djmoon.member.vo.MemberVo;

public interface MemberService {

	//회원가입
	public int join(MemberVo vo);
	
	//로그인
	public MemberVo login(MemberVo vo);
	
	//아이디 중복확인
	public int doubleCheckbyId(String id);

}
