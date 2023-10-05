package com.danal.djmoon.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danal.djmoon.movie.Detail;
import com.danal.djmoon.movie.Main;

@RequestMapping("movie")
@Controller
public class MovieDetailController {
    
    // 메인 화면
    @GetMapping("detail")
    public String main(Model model) throws InterruptedException {
        Detail crawler = new Detail();
        List<String> dataList = crawler.getDataList();
        
        // 크롤링한 데이터를 모델에 추가
        model.addAttribute("dataList", dataList);
        return "movie/detail";
    }
}
