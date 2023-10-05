package com.danal.djmoon.community.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danal.djmoon.community.service.CommunityService;
import com.danal.djmoon.community.vo.BoardCmtVo;
import com.danal.djmoon.community.vo.BoardVo;
import com.danal.djmoon.member.vo.MemberVo;

@RequestMapping("community")
@Controller
public class CommunityDetailController {

	@Autowired
	private CommunityService cs;

	@GetMapping("detail")
	public String detail(Model model, String bno, HttpServletRequest req, HttpServletResponse resp,
			HttpSession session) {
	
		if (bno == null) {
			bno = "1";
		}


		//세션 가져오기
		HttpSession s = req.getSession();

		//로그인 멤버 가져오기
		MemberVo loginMember = (MemberVo) s.getAttribute("loginMember");
		
		//게시글 조회수 중복 방지
        //게시글을 이미 조회했는지 확인
        Set<String> viewedPosts = (Set<String>) s.getAttribute("viewedPosts");
        System.out.println("viewedPosts :" +viewedPosts);
        if (viewedPosts == null) {
            viewedPosts = new HashSet<>();
            s.setAttribute("viewedPosts", viewedPosts);
        }

        if (!viewedPosts.contains(bno) && loginMember != null) {
        	System.out.println("viewedPosts'bno:" + viewedPosts.contains(bno));
            //조회수 증가 로직 실행
            cs.increaseViewCount(bno);

            //게시글을 이미 조회했음을 표시
            viewedPosts.add(bno);
        }

        BoardVo vo = cs.infoDetail(bno);
        
        List<BoardCmtVo> cvo = cs.selectCommCmt(bno);
        
        model.addAttribute("vo", vo);
        model.addAttribute("cvo", cvo);
		// 로그인 관련 속성 설정 (관리자로 로그인 또는 내가 쓴 게시글, 댓글 == 삭제 가능)
		boolean isAdmin = false;
		boolean isMyPosts = false;
		List<Boolean> isMyCmts = new ArrayList<>();

		if (loginMember != null) {
			isAdmin = "admin".equals(loginMember.getId());
			isMyPosts = loginMember.getNo().equals(vo.getMemberNo());

			for (BoardCmtVo cmt : cvo) {
				boolean isMyCmt = loginMember.getNo().equals(cmt.getWriter());
				isMyCmts.add(isMyCmt);
			}
		}

		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("isMyPosts", isMyPosts);
		model.addAttribute("isMyCmts", isMyCmts);

		return "community/detail";

	}
}
