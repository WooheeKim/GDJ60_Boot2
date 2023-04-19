package com.iu.base.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.base.board.BoardDAO;
import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardService;
import com.iu.base.board.BoardVO;
import com.iu.base.util.FileManager;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeService implements BoardService {
	//source - Override/implement Method 눌러서 작성
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	
	@Value("${app.upload.notice}")
	private String path;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeStartRow();
		
		pager.makeBlock(noticeDAO.getTotalCount(pager));
		
		return noticeDAO.getList(pager);
	}
	
	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		return noticeDAO.getDetail(boardVO);
	}
	
	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		return noticeDAO.getFileDetail(boardFileVO);
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] multipartFiles) throws Exception {
		
		int result = noticeDAO.setInsert(boardVO);
		log.error("Num =======> {}", boardVO.getNum());
		
		if(multipartFiles != null) {
			for(MultipartFile multipartFile: multipartFiles) {
				//multipartFile.isEmpty()
				if(!multipartFile.isEmpty()) {
					String fileName = fileManager.saveFile(path, multipartFile);
					BoardFileVO boardFileVO = new BoardFileVO();
					boardFileVO.setFileName(fileName);
					boardFileVO.setOriName(multipartFile.getOriginalFilename());
					boardFileVO.setNum(boardVO.getNum());
					noticeDAO.setFileInsert(boardFileVO);
					
				}
			}
		}
		
		return 0; //noticeDAO.setInsert(boardVO);
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
