package com.danal.djmoon.member.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVo2 {

	private int no;
	private String name;
	private String id;
	private String pwd;
	private Timestamp enrollDate;

}
