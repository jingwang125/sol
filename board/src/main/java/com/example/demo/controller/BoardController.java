package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageDto;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;

@Controller 
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
	//게시글 목록 조회
	@GetMapping("/list")
	public String list(@RequestParam(defaultValue="1") int pageNum, Model model) {
		int pageSize = 20;
		//게시글 목록 조회
		model.addAttribute("boards",boardService.findAllWithPaging(pageNum, pageSize));
		//페이징 정보 조회
		PageDto pageInfo = boardService.getPageInfo(pageNum, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		
		return "board/list";
	}
	
	//게시글 상세 조회, 댓글 조회
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        model.addAttribute("comments", commentService.selectCommentsByBoardId(id));
        return "board/view";
    }
    
    //게시글 작성화면 이동
    @GetMapping("/write")
    public String writeForm() {
        return "board/write";
    }
    
    //게시글 저장
    @PostMapping("/write")
    public String write(BoardDto boardDto) {
        boardService.insertBoard(boardDto);
        return "redirect:/board/list";
    }
    
    //게시글 수정화면 이동, 수정정보 호출
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        BoardDto board = boardService.findById(id);
        model.addAttribute("board", board);
        return "board/edit";
    }
    
    //게시글 수정
    @PostMapping("/edit/{id}")
    public String updateBoard(@PathVariable Long id, BoardDto boardDto) {
        boardDto.setId(id);
        boardService.updateBoard(boardDto);
        return "redirect:/board/view/" + id;
    }
    
    //게시글 삭제
    @PostMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }

}
