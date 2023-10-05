package com.danal.djmoon.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.danal.djmoon.community.controller.CommunityMainController;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	
	@GetMapping("main")
	public String main(HttpSession session) {
		
		//세션 정보 확인하는 로그
		log.debug("session info - 세션 정보: {}", session);
		
		return"main/main";
		
	}

}
