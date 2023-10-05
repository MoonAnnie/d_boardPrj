package com.danal.djmoon.community.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BoardVo {
	
	public String no;
	public String memberNo;
	
	@NotBlank
	@Size(min = 2, max = 100, message = "제목은 2자 이상 100자 이하로 입력해주세요.")
	@Pattern(regexp = "^[a-zA-Z0-9가-힣\\s]*$", message = "제목은 영문, 숫자, 한글만 입력 가능합니다.")
	public String title;
	public String content;
	public String enrollDate;
	public String modifyDate;
	public String hit;
	public String deleteYn;
	
	public String name;
	public String id;
	
}




