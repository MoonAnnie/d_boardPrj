package com.danal.djmoon.member.vo;

import lombok.Data;

@Data
public class AttachmentVo {
	
	private int no;
	private String originName;
	private String changeName;
	private String filePath;
	private String enrollDate;
	private char status;

}
