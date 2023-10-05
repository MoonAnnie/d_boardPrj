package com.danal.djmoon.community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danal.djmoon.community.service.CommunityService;
import com.danal.djmoon.community.vo.BoardVo;
import com.danal.djmoon.member.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("community")
@Controller
@Slf4j
public class CommunityWriteSummernoteController {
	
	@Autowired
	private CommunityService cs;
	
	//게시글 작성 (화면)
	@GetMapping("write_summernote")
	public String write(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 if(req.getSession().getAttribute("loginMember") == null) {
		 req.setAttribute("msg", "로그인 후 이용해주세요");
		 return "community/write_summernote"; 
		 }
		 return "community/write_summernote";
	}
	
	//게시글 작성하기
	@PostMapping(value="write_summernote")
	public String write(@Valid BoardVo vo, BindingResult bindingResult, MemberVo mvo, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
	//public String write(BoardVo vo, MemberVo mvo, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
	
		
		//세션 가져오기
		HttpSession s = req.getSession();
		
		//로그인 멤버 가져오기
		MemberVo loginMember = (MemberVo)s.getAttribute("loginMember");
		
		if(loginMember.getId() == null) { 
			return "common/error"; 
			}
		
		session.setAttribute("loginMember", loginMember);
		
		//글 작성하기
		int result = cs.write(vo, mvo);
		
		// 유효성 검사 결과 확인
		if (bindingResult.hasErrors()) {
			// 에러 처리 로직
			return "redirect:main";
		}
		
		if(result == 1) {
			req.setAttribute("msg", "게시글 작성 성공 !");
			
			//게시글 작성 내역 알려주는 로그
			log.debug("Board Info - Writer: {}, Title: {}, Content: {}", loginMember.getName(), vo.getTitle(), vo.getContent());
			
			return "redirect:main";
		}else {
			return "common/error";
		}
		
	}
	
	
}//class



