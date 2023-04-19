package com.iu.base.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iu.base.util.Pager;

public interface BoardService {
	
	// 글 전체 조회
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	// 글 하나 조회
	public BoardVO getDetail(BoardVO boardVO) throws Exception;
	
	// 파일 조회
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception;
	
	// 글 쓰기
	public int setInsert(BoardVO boardVO, MultipartFile[] multipartFiles) throws Exception;
	
	// 글 수정
	public int setUpdate(BoardVO boardVO) throws Exception;
	
	// 글 삭제
	public int setDelete(BoardVO boardVO) throws Exception;
	
}
