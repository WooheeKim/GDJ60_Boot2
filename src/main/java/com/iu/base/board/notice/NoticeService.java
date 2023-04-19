package com.iu.base.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.base.board.BoardService;
import com.iu.base.board.BoardVO;
import com.iu.base.util.Pager;

@Service
public class NoticeService implements BoardService {
	//source - Override/implement Method 눌러서 작성
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeStartRow();
		
		pager.makeBlock(noticeDAO.getTotalCount(pager));
		
		return noticeDAO.getList(pager);
	}
	
	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		return null;
	}
	
	@Override
	public int setInsert(BoardVO boardVO) throws Exception {
		return noticeDAO.setInsert(boardVO);
	}
	
	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		return 0;
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		return 0;
	}
}
