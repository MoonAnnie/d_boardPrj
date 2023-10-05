package com.danal.djmoon.movie.vo;

import lombok.Data;

@Data
public class MoviePageVo {
	
	private String search;
	private int p;
	private String deleteYn;
	private String no;
	private String searchType;
	private int limit;
	private int offset;
	private int boardLimit;

}
