package com.iu.base.board.notice;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardVO;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;



@Controller
@RequestMapping("/notice/*")
@Slf4j //log 기록을 남겨주는 Annotation
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Value("${app.upload.notice}")
	private String path;
	
	// 각 메서드가 실행되기 전에 @ModelAttribute부터 실행함
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(NoticeVO noticeVO) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		BoardVO boardVO = noticeService.getDetail(noticeVO);
		
		modelAndView.addObject("boardVO", boardVO);
		modelAndView.setViewName("board/detail");
		
		return modelAndView;
	}
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFileVO boardFileVO) throws Exception{
		boardFileVO = noticeService.getFileDetail(boardFileVO);
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("boardFileVO", boardFileVO);
		modelAndView.setViewName("fileManager");
		
		return modelAndView;
	}
	
	@GetMapping("list")
//	@RequestMapping(value="list", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getList(@ModelAttribute Pager pager) throws Exception {
		// 매개변수는 자동으로 @ModelAttribute annotation이 붙어져서 나가기 때문에, mv.addObject("pager", pager) 한 것과 같음
		// object의 이름은 매개변수의 첫글자를 소문자로 바꿀 것이 됨
		
		//파라미터 확인 등을 하고싶을때는 system.out이라는 프린트문 대신에 로그기록을 사용하도록 한다.
		log.info("Path =====> {}", path);
		log.info("kind : {}", pager.getKind());
		log.info("search: {}", pager.getSearch());
		
		ModelAndView modelAndView = new ModelAndView();
		
		//파라미터 확인용 - 이제 이거 대신에 로그를 사용하면 된다.
//		System.out.println(pager.getKind());
//		System.out.println(pager.getSearch());
		
		List<BoardVO> ar = noticeService.getList(pager);
		
		modelAndView.addObject("list", ar);
		modelAndView.setViewName("board/list");
		return modelAndView;
	}
	
	@GetMapping("add")
	public ModelAndView setInsert() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("board/add");
		return modelAndView;
	}
	
	@PostMapping("add")
	public ModelAndView setInsert(NoticeVO noticeVO, MultipartFile[] boardFiles) throws Exception {
		
		for(MultipartFile multipartFile:boardFiles) {
			log.info("OriginalName : {} Size : {}", multipartFile.getOriginalFilename(), multipartFile.getSize());
		}
		
		ModelAndView modelAndView = new ModelAndView();
		
		int result = noticeService.setInsert(noticeVO, boardFiles);
		
		modelAndView.setViewName("redirect:./list");
		return modelAndView;
	}
	
}
