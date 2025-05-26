package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CommentDto;

@Mapper
public interface CommentMapper {

	List<CommentDto> selectCommentsByBoardId(@Param("boardId") Long boardId);
	CommentDto selectCommentById(@Param("id") Long id);
	List<CommentDto> selectChildComments(@Param("parentId") Long parentId);
	void insertComment(CommentDto commentDto);
	void updateComment(CommentDto commentDto);
	void deleteComment(@Param("id") Long id);
}
