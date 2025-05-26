package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CommentDto;
import com.example.demo.mapper.CommentMapper;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	// 게시글의 댓글 목록 조회
	public List<CommentDto> selectCommentsByBoardId(Long boardId) {
		return commentMapper.selectCommentsByBoardId(boardId);
	}
	
	// 댓글 저장
	public void insertComment(CommentDto commentDto) {
		int depth = commentDto.getParentId() != null ? 1 : 0;
		commentDto.setDepth(depth);
		commentMapper.insertComment(commentDto);
	}

	// 댓글 수정
	public void updateComment(CommentDto commentDto) {
		CommentDto existingComment = commentMapper.selectCommentById(commentDto.getId());
		if (existingComment == null) {
			throw new IllegalArgumentException("댓글이 존재하지 않습니다.");
		}
		commentMapper.updateComment(commentDto);
	}

	// 댓글 삭제
	public void deleteComment(Long id) {
		CommentDto comment = commentMapper.selectCommentById(id);
		if (comment == null) {
			throw new IllegalArgumentException("댓글이 존재하지 않습니다.");
		}
		
		// 대댓글이 있는 경우 먼저 대댓글 삭제
		if (comment.getDepth() == 0) {
			List<CommentDto> childComments = commentMapper.selectChildComments(id);
			for (CommentDto child : childComments) {
				commentMapper.deleteComment(child.getId());
			}
		}
		
		commentMapper.deleteComment(id);
	}
	
}
