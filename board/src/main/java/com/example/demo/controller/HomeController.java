package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
	//홈 화면이동
    @GetMapping("/")
    public String home() {
        return "redirect:/board/list";  // 루트 경로로 접속시 게시판 목록으로 리다이렉트
    }
} 