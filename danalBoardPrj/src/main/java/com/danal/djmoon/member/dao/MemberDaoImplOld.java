//package com.danal.djmoon.member.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.danal.djmoon.member.vo.MemberVo;
//
//import static com.danal.djmoon.common.SQLiteTemplate.*;
//
//import lombok.extern.slf4j.Slf4j;
//
//import com.danal.djmoon.member.vo.MemberVo;
//
//@Repository
//@Slf4j
//public class MemberDaoImplOld implements MemberDao{
//
//	//회원가입
//	@Override
//	//public int insertMember(SqlSessionTemplate sst, MemberVo vo) {
//	public int insertMember(Connection conn, MemberVo vo) {
//		
//		String sql = "INSERT INTO MEMBER(NAME, ID, PWD, ENROLL_DATE) VALUES( ?, ?, ?, datetime('now', 'localtime'))";
//		
//		PreparedStatement pstmt = null;
//		int result = 0;
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getId());
//			pstmt.setString(3, vo.getPwd());
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//				
//		//return sst.selectOne("memberMapper.insertMember", vo);
//	}
//
//	//로그인
//	@Override
//	public MemberVo selectOneMember(SqlSessionTemplate sst, MemberVo vo) {
////	public MemberVo selectOneMember(Connection conn, MemberVo vo) {
////		
////		//String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
////		//비밀번호 암호화 후 아래로 수정
////		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
////		
////		PreparedStatement pstmt = null;
////		MemberVo loginMember = null;
////		ResultSet rs = null;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			
////			pstmt.setString(1, vo.getId());
////			//pstmt.setString(2, vo.getPwd());
////			
////			rs = pstmt.executeQuery();
////			
////			if(rs.next()) {
////				String no = rs.getString("NO");
////				String name = rs.getString("NAME");
////				String id = rs.getString("ID");
////				String pwd = rs.getString("PWD");
////				String enrollDate = rs.getString("ENROLL_DATE");
////				
////				loginMember = new MemberVo();
////				
////				loginMember.setNo(no);
////				loginMember.setName(name);
////				loginMember.setId(id);
////				loginMember.setPwd(pwd);
////				loginMember.setEnrollDate(enrollDate);
////				
////			}
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			close(pstmt);
////			close(rs);
////		}
////		
////		return loginMember;
//		return sst.selectOne("memberMapper.selectOneById" , vo);
//	}
//
//	//아이디 중복확인
//	@Override
//	public int doubleCheckbyId(SqlSessionTemplate sst, String id) {
////	public int doubleCheckbyId(Connection conn, String id) {
////		
////		String sql = "SELECT ID FROM MEMBER WHERE ID = ?";
////		ResultSet rs = null;
////		PreparedStatement pstmt = null;
////		int result = 0;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			pstmt.setString(1, id);
////			rs = pstmt.executeQuery();
////			
////			if(rs.next()) {
////				result = 1; 
////			} 
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		
////		} finally {
////			close(rs);
////			close(pstmt);
////		}
////		return result;
//		
//		return sst.selectOne("memberMapper.selectOneCheckId" , id);
//	}
//
//
//}
