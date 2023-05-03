package com.iu.base.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iu.base.board.BoardVO;
import com.iu.base.board.notice.NoticeService;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

//@Controller
//Controller 내의 모든 메서드가 @ResponseBody가 필요하다면 선언
@RestController
@Slf4j
public class RestFullController {
	
	@Autowired
	private NoticeService noticeService;
	
//	@GetMapping("/rest/{num}/detail")
	@GetMapping("/rest/detail")
	@ResponseBody
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		boardVO = noticeService.getDetail(boardVO);
		return boardVO;
	}
	
	@GetMapping("/rest/list/{page}")
	@ResponseBody
	public List<BoardVO> getList(@PathVariable(name = "page") Long p, Pager pager) throws Exception {	
		log.error("{} ::: page", p);
		List<BoardVO> ar = noticeService.getList(pager);
		
		
		return ar;
	}
	
}
