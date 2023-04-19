package com.iu.base.board;

import java.util.List;

import com.iu.base.util.Pager;

public interface BoardService {
	
	// 글 전체 조회
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	// 글 하나 조회
	public BoardVO getDetail(BoardVO boardVO) throws Exception;
	
	// 글 쓰기
	public int setInsert(BoardVO boardVO) throws Exception;
	
	// 글 수정
	public int setUpdate(BoardVO boardVO) throws Exception;
	
	// 글 삭제
	public int setDelete(BoardVO boardVO) throws Exception;
	
}
