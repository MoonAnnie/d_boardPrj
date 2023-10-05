package com.danal.djmoon.community.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.danal.djmoon.community.page.PageVo;
import com.danal.djmoon.community.page.Pagination;
import com.danal.djmoon.community.service.CommunityService;
import com.danal.djmoon.community.vo.BoardPageVo;
import com.danal.djmoon.community.vo.BoardVo;
import com.danal.djmoon.member.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("community")
@Controller
@Slf4j
public class CommunityMainController {
	
	@Autowired
	private CommunityService cs;
	
	//화면
	@GetMapping("main")
    public String main(Model model,
                       @RequestParam(defaultValue = "1") int p,
                       @RequestParam(required = false) String search,
                       @RequestParam(required = false, defaultValue = "0") String searchType, // 변경된 부분
                       HttpSession session, MemberVo mvo, HttpServletRequest req) {

	    String deleteYn = "N";
		    
	    BoardPageVo bpvo = new BoardPageVo();
        bpvo.setP(p);
        bpvo.setDeleteYn(deleteYn);
        bpvo.setSearch(search);
        bpvo.setSearchType(searchType); // 변경된 부분

		//PageVo 객체 만들기
		int listCount = cs.selectCnt(bpvo);
		if (listCount == 0) {
			model.addAttribute("msg", "검색 결과가 없습니다.");
			return "community/main";
		}		

		int currentPage = p; // 현재 페이지
	    int pageLimit = 5;   // 목록에 보여 줄 페이지 수
	    int boardLimit = 10; // 한 페이지에 보여줄 게시글 수
	    PageVo pv = Pagination.getPageVo(listCount, currentPage, pageLimit, boardLimit);
	    
	    List<BoardVo> list = cs.selectList(bpvo, pv);

		model.addAttribute("list", list);
		model.addAttribute("pv", pv);
		model.addAttribute("bpvo", bpvo);
		
		//검색바가 작동이 잘 되는지 확인하는 로그
		log.info("Received request - 검색어: {}, 검색조건: {} (0: 제목, 1: 작성자)", search, searchType);

		return "community/main";
	}
	
	@PostMapping("main")
	public String main(BoardVo vo) {
		return "community/main";
	}
	

}