package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CommentDto;
import com.example.demo.service.CommentService;

@Controller 
@RequestMapping("/comment")

public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	//게시글의 댓글 저장
    @PostMapping("/comment")
    @ResponseBody
    public void insertComment(@RequestBody CommentDto commentDto) {
    	commentService.insertComment(commentDto);
    }
    
    //게시글의 댓글 수정
    @PutMapping("/comment")
    @ResponseBody
    public void updateComment(@RequestBody CommentDto commentDto) {
    	commentService.updateComment(commentDto);
    }
    
    //게시글의 댓글 삭제
    @DeleteMapping("/comment/{id}")
    @ResponseBody
    public void deleteComment(@PathVariable Long id) {
    	commentService.deleteComment(id);
    }

}
