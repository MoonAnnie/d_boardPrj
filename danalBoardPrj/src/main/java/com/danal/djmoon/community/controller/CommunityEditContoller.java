package com.danal.djmoon.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danal.djmoon.community.service.CommunityService;
import com.danal.djmoon.community.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("community")
@Controller
public class CommunityEditContoller {

	@Autowired
	private CommunityService cs;
	
	@GetMapping("edit")
	public String edit(Model model, String no) {

		BoardVo vo = cs.selectInfoOne(no);	
		model.addAttribute("vo", vo);
		
		return "community/edit";
	}
	
	@PostMapping("edit")
	public String edit(BoardVo vo, Model model, String bno) {
		
		int result = cs.infoEdit(vo, bno);
		
		if(result != 1) return "error";
		
		//model.addAttribute("msg", "수정완료!");
		//model.addAttribute("msgDetail", "게시글이 수정되었습니다.");
		//model.addAttribute("path", "community/detail?bno="+vo.getNo());

		return "redirect:detail?bno="+vo.getNo();
		
		//return "common/successMsg";

	}
	

}

