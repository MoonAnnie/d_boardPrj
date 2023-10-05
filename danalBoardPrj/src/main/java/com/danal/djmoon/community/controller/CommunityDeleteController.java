package com.danal.djmoon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danal.djmoon.community.service.CommunityService;
import com.danal.djmoon.community.vo.BoardVo;

@RequestMapping("community")
@Controller
public class CommunityDeleteController {

	@Autowired
	private CommunityService cs;
	
	@GetMapping("delete")
	public String edit(Model model, String no, BoardVo vo) {

		int result = cs.deleteInfo(no);
		
		model.addAttribute("msg", "삭제완료!");
		model.addAttribute("msgDetail", "게시글이 삭제되었습니다.");
		model.addAttribute("path", "community/main");

		//return "redirect:info";
		//return "common/successMsg";
		return "redirect:main";

	}

}
