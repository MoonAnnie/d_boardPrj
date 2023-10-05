package com.danal.djmoon.community.vo;

import lombok.Data;

@Data
public class BoardPageVo {
	
	private String search;
	private int p;
	private String deleteYn;
	private String sort;
	private int cateNo;
	private String no;
	private String searchType;
	private int limit;
	private int offset;
	private int boardLimit;
}

