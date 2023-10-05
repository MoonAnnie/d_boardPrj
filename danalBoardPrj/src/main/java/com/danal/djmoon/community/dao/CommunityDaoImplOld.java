//package com.danal.djmoon.community.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.apache.ibatis.session.RowBounds;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.danal.djmoon.community.page.PageVo;
//import com.danal.djmoon.community.vo.BoardCmtVo;
//import com.danal.djmoon.community.vo.BoardPageVo;
//import com.danal.djmoon.community.vo.BoardVo;
//import com.danal.djmoon.member.vo.MemberVo;
//
//import static com.danal.djmoon.common.SQLiteTemplate.*;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//@Repository
//@Slf4j
//public class CommunityDaoImplOld implements CommunityDao {
//
//	//게시판 갯수 조회
//	@Override
//	public int selectCnt(SqlSessionTemplate sst, BoardPageVo bpvo) {
////	public int selectCnt(Connection conn, BoardPageVo bpvo) {
////		
////		// SQL
////        String sql = "SELECT COUNT(*) AS CNT FROM COMMUNITY INNER JOIN MEMBER ON COMMUNITY.MEMBER_NO = MEMBER.NO WHERE COMMUNITY.DELETE_YN = 'N'";
////        
////        if (bpvo.getSearch() != null && !bpvo.getSearch().isEmpty()) {
////            if ("0".equals(bpvo.getSearchType())) { // 제목으로 검색
////                sql += " AND TITLE LIKE '%' || ? || '%'";
////            } else if ("1".equals(bpvo.getSearchType())) { // 작성자로 검색
////                sql += " AND NAME LIKE '%' || ? || '%'";
////            }
////        }
////
////        PreparedStatement pstmt = null;
////        ResultSet rs = null;
////        int result = 0;
////
////        try {
////            pstmt = conn.prepareStatement(sql);
////
////            if (bpvo.getSearch() != null && !bpvo.getSearch().isEmpty()) {
////                pstmt.setString(1, bpvo.getSearch());
////            }
////
////            rs = pstmt.executeQuery();
////
////            if (rs.next()) {
////                result = rs.getInt("CNT");
////            }
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        } finally {
////            close(rs);
////            close(pstmt);
////        }
////
////        return result;
//        
//		int cnt = sst.selectOne("boardMapper.selectCnt" , bpvo);
//		return cnt;
//		
//	}
//	
//	//게시판 조회
//	@Override
//	public List<BoardVo> selectList(SqlSessionTemplate sst, BoardPageVo bpvo, PageVo pv){
////	public List<BoardVo> selectList(Connection conn, BoardPageVo bpvo, PageVo pv) {
////
////	    // 게시글 목록 조회
////	    int currentPage = pv.getCurrentPage();
////	    int boardLimit = pv.getBoardLimit();
////	    int offset = (currentPage - 1) * boardLimit;
////
////	    String sql = "SELECT *, (SELECT rowid FROM COMMUNITY WHERE COMMUNITY.NO = C.NO) AS CUSTOM_ROWID " +
////	                 "FROM (SELECT COMMUNITY.NO, MEMBER.NAME, COMMUNITY.MEMBER_NO, MEMBER.NO, COMMUNITY.TITLE, " +
////	                 "COMMUNITY.CONTENT, COMMUNITY.ENROLL_DATE, COMMUNITY.DELETE_YN, COMMUNITY.HIT " +
////	                 "FROM COMMUNITY INNER JOIN MEMBER ON COMMUNITY.MEMBER_NO = MEMBER.NO) AS C " +
////	                 "WHERE DELETE_YN = 'N'";
////	                
////	    // 검색어와 검색 타입이 존재할 때 검색 조건 추가
////	    if (bpvo.getSearch() != null && !bpvo.getSearch().isEmpty()) {
////	        if ("0".equals(bpvo.getSearchType())) { // 제목으로 검색
////	            sql += " AND TITLE LIKE '%' || ? || '%'";
////	        } else if ("1".equals(bpvo.getSearchType())) { // 작성자로 검색
////	            sql += " AND NAME LIKE '%' || ? || '%'";
////	        }
////	    }
////
////	    sql += "ORDER BY ENROLL_DATE DESC LIMIT ? OFFSET ?";
////
////	    PreparedStatement pstmt = null;
////	    ResultSet rs = null;
////	    ArrayList<BoardVo> voList = new ArrayList<BoardVo>();
////
////	    try {
////	        pstmt = conn.prepareStatement(sql);
////
////	        //검색 조건이 추가된 경우에만 바인딩
////	        int parameterIndex = 1;
////	        if (bpvo.getSearch() != null && !bpvo.getSearch().isEmpty()) {
////	            pstmt.setString(parameterIndex++, "%" + bpvo.getSearch() + "%");
////	        }
////
////	        pstmt.setInt(parameterIndex++, boardLimit); // LIMIT 값 설정 //boardLimit == 한 페이지에 보여줄 게시글 수
////	        pstmt.setInt(parameterIndex, offset); // 제외 값 OFFSET 값 설정
////
////	        rs = pstmt.executeQuery();
////
////	        while (rs.next()) {
////	            // rs -> vo
////	            String no = rs.getString("NO");
////	            String name = rs.getString("NAME");
////	            String title = rs.getString("TITLE");
////	            String content = rs.getString("CONTENT");
////	            String enrollDate = rs.getString("ENROLL_DATE").substring(0, 10);
////	            String deleteYn = rs.getString("DELETE_YN");
////	            String hit = rs.getString("HIT");
////	            String memberNo = rs.getString("MEMBER_NO");
////
////	            // vo -> list
////	            BoardVo vo = new BoardVo();
////	            vo.setNo(no);
////	            vo.setName(name);
////	            vo.setTitle(title);
////	            vo.setContent(content);
////	            vo.setEnrollDate(enrollDate);
////	            vo.setDeleteYn(deleteYn);
////	            vo.setHit(hit);
////	            vo.setMemberNo(memberNo);
////
////	            voList.add(vo);
////	        }
////
////	    } catch (SQLException e) {
////	        e.printStackTrace();
////	    } finally {
////	        close(rs);
////	        close(pstmt);
////	    }
////
////	    return voList;
//		
//	    int currentPage = pv.getCurrentPage();
//	    int boardLimit = pv.getBoardLimit();
//	    int offset = (currentPage - 1) * boardLimit;
//	    
//	    Map<String, Object> params = new HashMap<>();
//	    params.put("search", bpvo.getSearch());
//	    params.put("searchType", bpvo.getSearchType());
//	    params.put("boardLimit", boardLimit);
//	    params.put("offset", offset);
//	    
//	    if (bpvo.getSearch() != null && !bpvo.getSearch().isEmpty()) {
//        if ("0".equals(bpvo.getSearchType())) { //제목으로 검색
//        	return sst.selectList("boardMapper.selectListByTitle" , params);
//        	} else if ("1".equals(bpvo.getSearchType())) { //작성자로 검색
//        	return sst.selectList("boardMapper.selectListByName" , params);
//        	}
//	    }
//	    
//	    return sst.selectList("boardMapper.selectList", params);
//		//return sst.selectList("boardMapper.selectList" , bpvo);
//		
//	}
//
//	//게시글 작성
//	@Override
//	public int write(SqlSessionTemplate sst, BoardVo vo, MemberVo mvo) {
////	public int write(Connection conn, BoardVo vo, MemberVo mvo) {
////		
////		//INSERT 구문 작성하기
////		String sql = "INSERT INTO COMMUNITY (MEMBER_NO, TITLE, CONTENT, ENROLL_DATE)VALUES(?, ?, ?, datetime('now', 'localtime'))";
////		
////		PreparedStatement pstmt = null;
////		int result = 0;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			
////			pstmt.setString(1, vo.getMemberNo());
////			pstmt.setString(2, vo.getTitle());
////			pstmt.setString(3, vo.getContent());
////			
////			result = pstmt.executeUpdate();
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			close(pstmt);
////		}
////		
////		return result;
//		
//		System.out.println("vo :::" + vo);
//		return sst.insert("boardMapper.write", vo);
//	}
//
//	//게시글 상세 조회 시 조회수 증가
//	@Override
//	public int increaseHit(String bno, SqlSessionTemplate sst) {
////	public int increaseHit(String bno, Connection conn) {
////		
////		//SQL
////		String sql = "UPDATE COMMUNITY SET HIT = HIT + 1 WHERE NO = ? AND DELETE_YN = 'N'";
////		
////		PreparedStatement pstmt = null;
////		int result = 0;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			
////			pstmt.setString(1, bno);
////			
////			result = pstmt.executeUpdate();
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			close(pstmt);
////		}
////		
////		return result;
//		
//		return sst.update("boardMapper.increaseHit", bno);
//		
//	}
//	
//	//게시글 상세 조회
//	@Override
//	public BoardVo selectInfoDetail(String bno, SqlSessionTemplate sst) {
////	public BoardVo selectInfoDetail(String bno, Connection conn) {
////		
////		//SQL
////		//String sql = "SELECT B.NO , B.CATEGORY , B.TITLE , B.CONTENT , B.ENROLL_DATE , B.DELETE_YN , B.C_LIKE , B.MODIFY_DATE , B.VIEW_COUNT , B.REPORT_YN , M.NICK AS WRITER , C.MENU_TYPE AS CATEGORY FROM BOBSTORY B JOIN MEMBER M ON B.WRITER = M.NO JOIN MENU_CATE C ON B.CATEGORY = C.MENU_CATE_NO WHERE B.NO = ? AND B.DELETE_YN ='N'";
////		String sql = "SELECT C.NO, C.TITLE, C.CONTENT, "
////					+ "C.ENROLL_DATE, C.HIT, C.DELETE_YN, C.MEMBER_NO, M.NAME "
////					+ "FROM COMMUNITY C "
////					+ "JOIN MEMBER M ON M.NO = C.MEMBER_NO "
////					+ "WHERE C.NO = ? AND C.DELETE_YN = 'N'";
////		
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////		BoardVo vo = null;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			
////			pstmt.setString(1, bno);
////			
////			rs = pstmt.executeQuery();
////			
////			if(rs.next()) {
////				String no = rs.getString("NO");
////				String name = rs.getString("NAME");
////				String title = rs.getString("TITLE");
////				String content = rs.getString("CONTENT");
////				String enrollDate = rs.getString("ENROLL_DATE").substring(0, 10);
////				String deleteYn = rs.getString("DELETE_YN");
////				String hit = rs.getString("HIT");
////				String memberNo = rs.getString("MEMBER_NO");
////				
////				vo = new BoardVo();
////				vo.setNo(no);
////				vo.setName(name);
////				vo.setTitle(title);
////				vo.setContent(content);
////				vo.setEnrollDate(enrollDate);
////				vo.setDeleteYn(deleteYn);
////				vo.setHit(hit);
////				vo.setMemberNo(memberNo);
////			}
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			close(rs);
////			close(pstmt);
////		}
////		return vo;
//		
//		return sst.selectOne("boardMapper.selectInfoOne", bno);
//	}
//
//	@Override
//	public BoardVo selectInfoOne(SqlSessionTemplate sst, String bno) {
////	public BoardVo selectInfoOne(Connection conn, String bno) {
////		
////		String sql = "SELECT C.NO , C.MEMBER_NO , C.TITLE , C.CONTENT , C.ENROLL_DATE , C.DELETE_YN , C.HIT FROM COMMUNITY C JOIN MEMBER M ON C.MEMBER_NO = M.NO WHERE C.NO = ? AND C.DELETE_YN = 'N'";
////
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////		BoardVo vo = null;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			
////			pstmt.setString(1, bno);
////			
////			rs = pstmt.executeQuery();
////			
////			if(rs.next()) {
////				String no = rs.getString("NO");
////				String title = rs.getString("TITLE");
////				String content = rs.getString("CONTENT");
////				String enrollDate = rs.getString("ENROLL_DATE").substring(0, 10);
////				String deleteYn = rs.getString("DELETE_YN");
////				String hit = rs.getString("HIT");
////				String memberNo = rs.getString("MEMBER_NO");
////				
////				vo = new BoardVo();
////				vo.setNo(no);
////				vo.setTitle(title);
////				vo.setContent(content);
////				vo.setEnrollDate(enrollDate);
////				vo.setDeleteYn(deleteYn);
////				vo.setHit(hit);
////				vo.setMemberNo(memberNo);
////			}
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			close(rs);
////			close(pstmt);
////		}
////		return vo;
//		
//		int result = sst.update("boardMapper.increaseHit", bno);
//		return sst.selectOne("boardMapper.selectInfoOne", bno);
//	}
//
//	//게시글 수정
//	@Override
//	public int updateInfoOne(SqlSessionTemplate sst, BoardVo vo, String bno) {
////	public int updateInfoOne(Connection conn, BoardVo vo, String bno) {
////		
////		String sql = "UPDATE COMMUNITY SET TITLE = ?, CONTENT = ?, MODIFY_DATE = datetime('now', 'localtime') WHERE NO = ?";
////
////		PreparedStatement pstmt = null;
////		int result = 0;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			
////			pstmt.setString(1, vo.getTitle());
////			pstmt.setString(2, vo.getContent());
////			pstmt.setString(3, vo.getNo());
////			
////			result = pstmt.executeUpdate();
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			close(pstmt);
////		}
////		
////		return result;
//		
//		return sst.insert("boardMapper.updateInfoOne", vo);
//	}
//
//	//게시글 삭제
//	@Override
//	public int deleteInfoOne(SqlSessionTemplate sst, String bno) {
////	public int deleteInfoOne(Connection conn, String bno) {
////		
////		String sql = "UPDATE COMMUNITY SET DELETE_YN = 'Y' WHERE NO = ?";
////		
////		PreparedStatement pstmt = null;
////		int result = 0;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			
////			pstmt.setString(1, bno);
////			
////			result = pstmt.executeUpdate();
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			close(pstmt);
////		}
////		
////		return result;
//		return sst.update("boardMapper.deleteInfoOne", bno);
//	}
//
//	//댓글 작성
//	@Override
//	public int insertCmt(BoardCmtVo cmtvo, SqlSessionTemplate sst) {
////	public int insertCmt(BoardCmtVo cmtvo, Connection conn) {
////		
////		String sql ="INSERT INTO COMMENT(POST_NO, WRITER, CMT, ENROLL_DATE, MODIFY_DATE) VALUES (?, ?, ?, datetime('now', 'localtime'), datetime('now', 'localtime'))";
////		
////		PreparedStatement pstmt = null;
////		int result = 0;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			
////			pstmt.setString(1, cmtvo.getPostNo());
////			pstmt.setString(2, cmtvo.getWriter());
////			pstmt.setString(3, cmtvo.getCmt());
////			
////			result = pstmt.executeUpdate();
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			close(pstmt);
////		}
////		
////		return result;
//		
//		return sst.insert("boardMapper.insertCmt", cmtvo);
//	}
//	
//	//댓글 조회
//	@Override
//	public List<BoardCmtVo> selectCommCmt(String bno, SqlSessionTemplate sst){
////	public List<BoardCmtVo> selectCommCmt(String bno, Connection conn) {
////		
////		String sql = "SELECT C.CMT_NO, C.POST_NO, C.WRITER, C.ENROLL_DATE, C.MODIFY_DATE, C.CMT, M.NAME "
////				+ "FROM COMMENT C "
////				+ "JOIN MEMBER M ON C.WRITER = M.NO "
////				+ "WHERE POST_NO = ? AND DELETE_YN = 'N'";
////		
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////		ArrayList<BoardCmtVo> voList = new ArrayList<BoardCmtVo>();
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////
////			pstmt.setString(1, bno);
////			
////			rs = pstmt.executeQuery();
////			
////			while(rs.next()) {
////				//rs -> vo
////				String cmtNo = rs.getString("CMT_NO");
////				String postNo = rs.getString("POST_NO");
////				String writer = rs.getString("WRITER");
////				String enrollDate = rs.getString("ENROLL_DATE");
////				String modifyDate = rs.getString("MODIFY_DATE"); 
////				String cmt = rs.getString("CMT");
////				String name = rs.getString("NAME");
////				
////				//vo -> list
////				BoardCmtVo cmtvo = new BoardCmtVo();
////				cmtvo.setCmtNo(cmtNo);
////				cmtvo.setPostNo(postNo);
////				cmtvo.setWriter(writer);
////				cmtvo.setEnrollDate(enrollDate);
////				cmtvo.setModifyDate(modifyDate);
////				cmtvo.setCmt(cmt);
////				cmtvo.setName(name);
////				
////				voList.add(cmtvo);
////			}
////				
////			} catch (SQLException e) {
////				e.printStackTrace();
////			} finally {
////				close(rs);
////				close(pstmt);
////			}
////			
////			return voList;
//		
//		return sst.selectList("boardMapper.selectCommCmt", bno);
//	}
//
//	//댓글 삭제
//	@Override
//	public int cmtDelete(String cmtNo, SqlSessionTemplate sst) {
////	public int cmtDelete(String cmtNo, Connection conn) {
////		
////		String sql = "UPDATE COMMENT SET DELETE_YN = 'Y' WHERE CMT_NO = ?";
////		
////		PreparedStatement pstmt = null;
////		int result = 0;
////		
////		try {
////			pstmt = conn.prepareStatement(sql);
////			
////			pstmt.setString(1, cmtNo);
////			
////			result = pstmt.executeUpdate();
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			close(pstmt);
////		}
////		
////		return result;
//		
//		return sst.update("boardMapper.cmtDelete", cmtNo);
//	}
//	
//}
