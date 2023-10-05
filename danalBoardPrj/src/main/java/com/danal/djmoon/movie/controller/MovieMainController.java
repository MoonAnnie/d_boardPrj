package com.danal.djmoon.movie.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.danal.djmoon.common.PageVo;
import com.danal.djmoon.common.Pagination;
import com.danal.djmoon.movie.Main;
import com.danal.djmoon.movie.service.MovieService;
import com.danal.djmoon.movie.vo.MoviePageVo;

@RequestMapping("movie")
@Controller
public class MovieMainController {
	
	@Autowired
	private MovieService ms;
    
    // 메인 화면
    @GetMapping("main")
    public String main(Model model,
            @RequestParam(defaultValue = "1") int p,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "0") String searchType, // 변경된 부분
            HttpSession session, HttpServletRequest req) throws InterruptedException {
        
    	String deleteYn = "N";
    	
    	MoviePageVo bpvo = new MoviePageVo();
    	bpvo.setP(p);
    	bpvo.setDeleteYn(deleteYn);
    	bpvo.setSearch(search);
    	bpvo.setSearchType(searchType);
    	
    	int listCount = ms.selectCnt(bpvo);
    	if(listCount == 0) {
    		model.addAttribute("msg", "검색 결과가 없습니다.");
    		return "movie/main";
    	}
    	
        int currentPage = p;
        int boardLimit = 100; // 페이지당 결과 개수를 100으로 변경
        int startRow = (currentPage - 1) * boardLimit + 1;
        int endRow = startRow + boardLimit - 1;

        // 크롤링 데이터를 가져올 때 페이징 처리
        Main crawler = new Main();
        List<String> dataList = crawler.getDataList(bpvo, startRow, endRow);

        PageVo pv = Pagination.getPageVo(listCount, currentPage);
        
        // 크롤링한 데이터를 모델에 추가
        model.addAttribute("dataList", dataList);
		model.addAttribute("pv", pv);
		model.addAttribute("bpvo", bpvo);
        
        return "movie/main";
    }
}
