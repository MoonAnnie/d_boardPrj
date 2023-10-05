package com.danal.djmoon.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.danal.djmoon.member.service.MemberService;
import com.danal.djmoon.member.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("member")
@Controller
@Slf4j
public class MemberController {

	@Autowired
	private BCryptPasswordEncoder enc;

	@Autowired
	private MemberService memberService;
	
	//회원가입화면
	@GetMapping("join")
	public String join(HttpServletRequest request) {
		return "member/join";
	}
	
	//회원가입하기
	@PostMapping("join")
	public String join(MemberVo vo , Model model) {
		
		int result = memberService.join(vo);
		
		if(result == 1) {
			model.addAttribute("msg", "회원가입 성공");
			model.addAttribute("path", "main");
			
			//회원가입 결과 알려주는 로그
			log.debug("Member Join Result - 아이디: {}, 비밀번호: {}, 가입일자: {}", vo.getId(), vo.getPwd(), vo.getEnrollDate());
			
			return "member/login";
		}else {
			return "common/error";
		}
	}
	
	//로그인화면
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	//로그인하기
	@PostMapping("login")
	public String login(MemberVo vo , HttpSession session , Model model) {
		
		//member select
		MemberVo loginMember = memberService.login(vo);
		
		session.setAttribute("loginMember", loginMember);
		
		//로그인 정보 알려주는 로그
		log.debug("Login Info - 아이디: {}, 비밀번호: {}", loginMember.getId(), loginMember.getPwd());
		
		model.addAttribute("msg", "로그인 성공^_^");

		//return "redirect:/main";
		return "main/main";
	}
	
	//로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	
	//아이디 중복 확인
	@ResponseBody
	@PostMapping("idDoubleCheck")
	public int idDoubleCheck(String id) {
		
		int result =  memberService.doubleCheckbyId(id);		
		
		return result;
	}
	
}//class
