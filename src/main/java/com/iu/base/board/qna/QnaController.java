package com.iu.base.board.qna;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/qna/*")
@Slf4j //log 기록을 남겨주는 Annotation
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${app.upload.qna}")
	private String path;
	
	// 각 메서드가 실행되기 전에 @ModelAttribute부터 실행함
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		BoardVO boardVO = qnaService.getDetail(qnaVO);
		
		modelAndView.addObject("boardVO", boardVO);
		modelAndView.setViewName("board/detail");
		
		return modelAndView;
	}
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFileVO boardFileVO) throws Exception{
		boardFileVO = qnaService.getFileDetail(boardFileVO);
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
		
		List<BoardVO> ar = qnaService.getList(pager);
		
		modelAndView.addObject("list", ar);
		modelAndView.setViewName("board/list");
		return modelAndView;
	}
	
	@GetMapping("add")
	public ModelAndView setInsert(@ModelAttribute BoardVO boardVO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("board/add");
		return modelAndView;
	}
	
	@PostMapping("add")
	public ModelAndView setInsert(@Valid BoardVO boardVO, BindingResult bindingResult, MultipartFile[] boardFiles) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			log.warn("========검증 실패========");
			modelAndView.setViewName("board/add");
			return modelAndView;
		}
		
		for(MultipartFile multipartFile:boardFiles) {
			log.info("OriginalName : {} Size : {}", multipartFile.getOriginalFilename(), multipartFile.getSize());
		}
		
		
		int result = qnaService.setInsert(boardVO, boardFiles);
		
		modelAndView.setViewName("redirect:./list");
		return modelAndView;
	}
	
}
