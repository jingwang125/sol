package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	List<BoardDto> findAllWithPaging(int offset, int pageSize);
	int getTotalCount();
	BoardDto findById(@Param("id") Long id);
	void insertBoard(BoardDto boardDto);
	void updateBoard(BoardDto boardDto);
	void deleteBoard(@Param("id") Long id);
}
