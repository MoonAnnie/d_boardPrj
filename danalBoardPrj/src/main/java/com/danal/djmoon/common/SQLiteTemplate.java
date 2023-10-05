package com.danal.djmoon.common;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.danal.djmoon.member.controller.MemberController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SQLiteTemplate {
	
	//SQLite 커넥션 연결
	public static Connection createDB(){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			//conn = DriverManager.getConnection("jdbc:sqlite:/Users/anniemoon/dev/danalHW/danalBoardPrj/djmoondb.db");
			conn = DriverManager.getConnection("jdbc:sqlite:D:\\djmoon\\dev\\sourcetree\\boardPrj\\danalBoardPrj\\djmoondb.db");
			
		}catch(Exception e) {
			//System.out.println("ERROR ::: SQLiteTemplate");
			//e.printStackTrace();
			log.warn ("[ERROR] - DB connection 중 예외 발생 : {} ", e);
		}
		
		return conn;
	}
	
	//commit
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();				
			}
		} catch (SQLException e) {
			log.warn ("[ERROR] - DB commit 중 예외 발생 : {} ", e);
		}
	}
	
	//rollback
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();				
			}
		} catch (SQLException e) {
			log.warn ("[ERROR] - DB rollback 중 예외 발생 : {} ", e);
		}
	}
	
	//커넥션 close
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();				
			}
		} catch (SQLException e) {
			log.warn ("[ERROR] - DB connection close 중 예외 발생 : {} ", e);
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()) {
				stmt.close();				
			}
		} catch (SQLException e) {
			log.warn ("[ERROR] - DB stmt close 중 예외 발생 : {} ", e);
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) {
				rs.close();				
			}
		} catch (SQLException e) {
			log.warn ("[ERROR] - DB rs close 중 예외 발생 : {} ", e);
		}
	}

}
