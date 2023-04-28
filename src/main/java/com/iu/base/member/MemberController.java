package com.iu.base.member;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("info")
	public void info(HttpSession session) throws Exception {
		
		String pw="1234";
		
		MemberVO memberVO = (MemberVO)memberService.loadUserByUsername(pw);
		
		log.error("{} ::::: ", memberVO.getPassword());
		log.error("{} ::::: ", passwordEncoder.encode(pw));
		log.error("{} ::::: ", memberVO.getPassword().equals(passwordEncoder.encode(pw)));
		
		// 지난 프로젝트 때 못한 부분 (패스워드 평문화, 암호화 된거 비교해줌)
		boolean check = passwordEncoder.matches(pw, memberVO.getPassword());
		log.error("{} ::::: ", check);
		
		log.error("============ Login Info ============");
		// SPRING_SECURITY_CONTEXT
//		Enumeration<String> names = session.getAttributeNames();
//		while(names.hasMoreElements()) {
//			log.error("====={}=====", names.nextElement());
//		}
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.error("====={}=====", obj);
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		Authentication authentication = contextImpl.getAuthentication();
		
		log.error("=====USERNAME : {}=====", authentication.getName());
		log.error("=====Detail : {}=====", authentication.getDetails());
		log.error("=====MemberVO : {}=====", authentication.getPrincipal());
	}
	
	@GetMapping("mypage")
	public void getMyPage() throws Exception {
		
	}
	
	@GetMapping("admin")
	public void getAdmin() throws Exception {
		
	}
	
	@GetMapping("idDuplicateCheck")
	@ResponseBody
	public boolean idDuplicateCheck(MemberVO memberVO) throws Exception {
		log.debug("============ID 중복체크============");
		boolean check = false;
		
		memberVO = memberService.idDuplicateCheck(memberVO);
		
		if(memberVO == null) {
			check=true;
		}
		
		return check;
	}
	
	@GetMapping("join")
	public ModelAndView setMemberJoin(@ModelAttribute MemberVO memberVO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName("member/join");				
		return modelAndView;
	}
	
	@PostMapping("join")
	public ModelAndView setMemberJoin(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		boolean check = memberService.memberCheck(memberVO, bindingResult);
		
		if(check) {
			modelAndView.setViewName("member/join");
			return modelAndView;
		}
		
		int result = memberService.setMemberJoin(memberVO);
		
		modelAndView.setViewName("redirect:../");
		
		return modelAndView;
	}
	
	@GetMapping("login")
	public ModelAndView getMemberLogin(HttpSession session) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(obj==null) {
			modelAndView.setViewName("member/login");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		
		return modelAndView;
	}
	
	@PostMapping("login")
	public ModelAndView getMemberLogin(MemberVO memberVO, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		memberVO = memberService.getMemberLogin(memberVO);
		
		modelAndView.setViewName("redirect:./login");
		if(memberVO != null) {			
			session.setAttribute("member", memberVO);
		} 
		modelAndView.setViewName("redirect:../");
		
		return modelAndView;
	}
	
	@GetMapping("logout")
	public ModelAndView getMemberLogout(HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		int result = memberService.getMemberLogout(memberVO);
		
		session.invalidate();
		
				
		modelAndView.setViewName("redirect:../");
		return modelAndView;
	}
	
}
