package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.mapper.CommentMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	//게시글 조회
	public List<BoardDto> findAllWithPaging(int pageNum, int pageSize) {
		int offset = (pageNum - 1) * pageSize;
		return boardMapper.findAllWithPaging(offset, pageSize);
	}
	
	//페이징 정보 조회
	public PageDto getPageInfo(int pageNum, int pageSize) {
		int totalCount = boardMapper.getTotalCount();
		return new PageDto(pageNum, pageSize, totalCount);
	}
	
	//게시글 상세 조회
	public BoardDto findById(Long id) {
		return boardMapper.findById(id);
	}
	
	//게시글 저장
	public void insertBoard(BoardDto boardDto) {
		boardMapper.insertBoard(boardDto);
	}
	
	//게시글 수정
	public void updateBoard(BoardDto boardDto) {
		BoardDto existingBoard = boardMapper.findById(boardDto.getId());
		if (existingBoard == null) {
			throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
		}
		boardMapper.updateBoard(boardDto);
	}
	
	//게시글 삭제
	public void deleteBoard(Long id) {
		BoardDto board = boardMapper.findById(id);
		if (board == null) {
			throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
		}
		// 게시글의 댓글들도 함께 삭제
		List<CommentDto> comments = commentMapper.selectCommentsByBoardId(id);
		for (CommentDto comment : comments) {
			commentMapper.deleteComment(comment.getId());
		}
		boardMapper.deleteBoard(id);
	}

}
