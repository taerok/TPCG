package com.tpcg.TPCG.controller;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpcg.TPCG.dto.BoardDto;
import com.tpcg.TPCG.service.BoardService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/board")
public class BoardController {

//	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "모든 게시글을 가져옵니다.")
	@GetMapping(value = "/")
	public ResponseEntity<List<BoardDto>> getBoardList() {
		List<BoardDto> list = boardService.findAll();
		return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "선택된 게시글을 가져옵니다.")
	@GetMapping(value = "/{board_id}")
	public ResponseEntity<BoardDto> getBoardDetail(@PathVariable("board_id") long id) {
		BoardDto boardDto = boardService.findById(id);

		return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
	}

	@ApiOperation(value = "선택된 옵션으로 게시글을 검색합니다.")
	@GetMapping(value = "/search/{option}")
	public ResponseEntity<List<BoardDto>> searchBoard(@PathVariable("option") String option,
			@RequestParam("keyword") String keyword) {
		List<BoardDto> list = null;
		switch (option) {
		case "all":
			list = boardService.findByTitleContainingOrContentContaining(new BoardDto(0, keyword, keyword));
			break;
		case "title":
			list = boardService.findByTitle(keyword);
			break;
		case "content":
			list = boardService.findByContent(keyword);
			break;
		default:
			list = null;
		}

		return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "게시글을 등록합니다.")
	@PostMapping(value = "/")
	public ResponseEntity<String> registBoard(@RequestBody BoardDto boardDto) {
		int res = boardService.insert(boardDto);
		if (res == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ApiOperation(value = "게시글을 수정합니다.")
	@PatchMapping(value = "/")
	public ResponseEntity<String> updateBoard(@RequestBody BoardDto boardDto) {
		int res = boardService.update(boardDto);
		if (res == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ApiOperation(value = "게시글을 지웁니다.")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> removeBoard(@PathVariable("id") long id) {
		int res = boardService.delete(id);
		if (res == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
