package com.iu.base.member;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("memberJoin")
	public ModelAndView setMemberJoin() throws Exception {
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName("member/join");				
		return modelAndView;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView setMemberJoin(MemberVO memberVO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		int result = memberService.setMemberJoin(memberVO);
		modelAndView.setViewName("redirect:../index");		
		return modelAndView;
	}
	
	@GetMapping("login")
	public ModelAndView getMemberLogin() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("member/login");
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
		session.invalidate();
		modelAndView.setViewName("redirect:../");
		return modelAndView;
	}
	
}
