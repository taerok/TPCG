package com.tpcg.TPCG.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpcg.TPCG.dao.BoardDao;
import com.tpcg.TPCG.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao; 
	
	@Override
	public List<BoardDto> findAll() {
		return boardDao.findAll();
	}

	@Override
	public long count() {
		return boardDao.count();
	}

	@Override
	public BoardDto findById(long id) { 
		return boardDao.findById(id);
	}

	@Override
	public List<BoardDto> findByTitleContainingOrContentContaining(BoardDto boardDto) {
		return boardDao.findByTitleContainingOrContentContaining(boardDto);
	}

	@Override
	public List<BoardDto> findByTitle(String title) {
		return boardDao.findByTitle(title);
	}

	@Override
	public List<BoardDto> findByContent(String content) {
		return boardDao.findByContent(content);
	}

	@Override
	public int insert(BoardDto boardDto) {
		return boardDao.insert(boardDto);
	}

	@Override
	public int update(BoardDto boardDto) {
		return boardDao.update(boardDto);
	}

	@Override
	public int deleteById(long id) {
		return boardDao.deleteById(id);
	}

}
