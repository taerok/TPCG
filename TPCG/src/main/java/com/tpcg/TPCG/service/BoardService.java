package com.tpcg.TPCG.service;

import java.util.List;

import com.tpcg.TPCG.dto.BoardDto;

public interface BoardService {

	public List<BoardDto> findAll();
	public long count();
	public BoardDto findById(long id);
	public List<BoardDto> findByTitleContainingOrContentContaining(BoardDto boardDto);
	public List<BoardDto> findByTitle(String title);
	public List<BoardDto> findByContent(String content);
	public int insert(BoardDto boardDto);
	public int update(BoardDto boardDto);
	public int delete(long id);
	
}
