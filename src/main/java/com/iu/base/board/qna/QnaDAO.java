package com.iu.base.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.iu.base.board.BoardDAO;

@Mapper
public interface QnaDAO extends BoardDAO {
	// Mapper라고 선언해두면 자동으로 찾음
	
	// namespace는 package + 해당 클래스명
	// id는 매서드명
	// return은 선언된 데이터 형태를 사용
}
