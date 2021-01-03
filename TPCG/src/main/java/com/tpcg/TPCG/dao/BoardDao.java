package com.tpcg.TPCG.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tpcg.TPCG.dto.BoardDto;


@Mapper
public interface BoardDao {
	
	public List<BoardDto> findAll();
	public long count();
	public BoardDto findById(long id);
	public List<BoardDto> findByTitleContainingOrContentContaining(BoardDto boardDto);
	public List<BoardDto> findByTitle(String title);
	public List<BoardDto> findByContent(String content);
	public int insert(BoardDto boardDto);
	public int update(BoardDto boardDto);
	public int deleteById(long id);
	
}
