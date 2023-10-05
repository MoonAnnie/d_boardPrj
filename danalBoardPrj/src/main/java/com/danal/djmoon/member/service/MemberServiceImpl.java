package com.danal.djmoon.member.service;

import java.sql.Connection;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danal.djmoon.member.dao.MemberDao;
import com.danal.djmoon.member.vo.MemberVo;

import static com.danal.djmoon.common.SQLiteTemplate.*;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{

	@Autowired
	private BCryptPasswordEncoder enc;
	
	@Autowired
	private SqlSessionTemplate sst;
	
	@Autowired
	private MemberDao memberDao;
	
	//회원가입
	@Override
	@Transactional
	public int join(MemberVo vo) {
	
		//커넥션 준비
		//SQL
		//트랜잭션 처리, 자원반납
		Connection conn = createDB();
		
		//디비 커넥션 확인하는 로그
		log.debug("DB Connection info - 연결 정보: {}", conn);

		
		//비밀번호 암호화
		String pwd = vo.getPwd();
		String newPwd = enc.encode(pwd);
		vo.setPwd(newPwd);
		
		int result = memberDao.insertMember(sst, vo);
		
		//회원가입 결과 확인하는 로그
		log.debug("Insert Member result - 결과: {} (0:성공, 1:실패)", result);
		
		close(conn);
		return result;
		
		//int result = memberDao.insertMember(sst, vo);
		//return result;
	}

	//로그인
	@Override
	public MemberVo login(MemberVo vo) {
		
		//커넥션 준비
		//SQL
		//트랜잭션 처리, 자원반납
		Connection conn = createDB();
		
		//디비 커넥션 확인하는 로그
		log.debug("DB Connection info - 연결 정보: {}", conn);
				
		MemberVo loginMember = memberDao.selectOneMember(sst, vo);
		
		//회원가입 결과 확인하는 로그
		log.debug("Login result - 결과: {} (0: 실패, 1: 성공)", loginMember);
		
		if(loginMember == null) {
			return null;
		}
		
		String pwd = vo.getPwd();
		String dbPwd = loginMember.getPwd();
		
		close(conn);
		
		//암호화 추가
		if(enc.matches(pwd, dbPwd)) {
			return loginMember;
		}else {
			return null;
		}
		
		//return loginMember;
	}

	//아이디 중복확인
	@Override
	public int doubleCheckbyId(String id) {
		
		Connection conn = createDB();
		
		//디비 커넥션 확인하는 로그
		log.debug("DB Connection info - 연결 정보: {}", conn);
				
		int result = memberDao.doubleCheckbyId(sst, id);
		
		//아이디 중복확인 결과 확인하는 로그
		log.debug("ID validation result - 결과: {} (0:성공, 1:실패)", result);
		
		close(conn);
		
		return result;
		//return memberDao.doubleCheckbyId(sst , id);
	}

}//class
