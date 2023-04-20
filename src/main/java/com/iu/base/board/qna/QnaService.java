package com.iu.base.board.qna;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional(rollbackFor = Exception.class)
public class QnaService implements BoardService {
	//source - Override/implement Method 눌러서 작성
	
	@Autowired
	private QnaDAO qnaDAO;
	
	
	@Value("${app.upload.qna}")
	private String path;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeStartRow();
		
		pager.makeBlock(qnaDAO.getTotalCount(pager));
		
		return qnaDAO.getList(pager);
	}
	
	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		return qnaDAO.getDetail(boardVO);
	}
	
	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		return qnaDAO.getFileDetail(boardFileVO);
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] multipartFiles) throws Exception {
		
		int result = qnaDAO.setInsert(boardVO);
		log.error("Num =======> {}", boardVO.getNum());
		
//		Random random = new Random();
//		int num = random.nextInt(1);
//		
//		if(multipartFiles == null) {
//			throw new Exception();
//		}
		
		
		if(multipartFiles != null) {
			for(MultipartFile multipartFile: multipartFiles) {
				//multipartFile.isEmpty()
				if(!multipartFile.isEmpty()) {
					String fileName = fileManager.saveFile(path, multipartFile);
					BoardFileVO boardFileVO = new BoardFileVO();
					boardFileVO.setFileName(fileName);
					boardFileVO.setOriName(multipartFile.getOriginalFilename());
					boardFileVO.setNum(boardVO.getNum());
					result = qnaDAO.setFileInsert(boardFileVO);
					
				}
			}
		}
		
		return result; //qnaDAO.setInsert(boardVO);
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
