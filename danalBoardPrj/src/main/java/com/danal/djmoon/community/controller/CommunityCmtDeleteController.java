package com.danal.djmoon.community.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danal.djmoon.community.service.CommunityService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("community")
@Controller
@Slf4j
public class CommunityCmtDeleteController {
	
	@Autowired
	private CommunityService cs;
	
	//댓글 삭제
	@GetMapping("CmtDelete")
	public String deleteCmt(int bno , HttpServletRequest req, String cmtNo) {
		
		int result = cs.cmtDelete(cmtNo);
		
		if(result == 1) {
			return "redirect:/community/detail?bno=" + bno;
		}else {
			return "common/error";
		}
	}

}
