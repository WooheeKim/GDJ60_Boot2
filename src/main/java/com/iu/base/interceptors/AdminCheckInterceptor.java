package com.iu.base.interceptors;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.iu.base.member.MemberVO;
import com.iu.base.member.RoleVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
	
	
	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
		log.info("==============Admin Interceptor 실행==============");
		
		boolean check = false;
		
		HttpSession session = request.getSession();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		memberVO.getRoleVOs();
		
		// 1.
		for(RoleVO roleVO:memberVO.getRoleVOs()) {
			if(roleVO.getRoleName().equals("ROLE_ADMIN")) {
				return true;
			}
		}
		
		request.setAttribute("message", "권한이 없습니다.");
		request.setAttribute("url", "../");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
		view.forward(request, response);
		
		return false;
		
		// 2.
//		for(RoleVO roleVO : memberVO.getRoleVOs()) {
//			if(roleVO.getNum() == 1) {
//				check = true;
//				break;
//			} else {
//				request.setAttribute("message", "관리자만 권한이 있습니다.");
//				request.setAttribute("url", "/");
//				
//				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
//				view.forward(request, response);
//				
//				check = false;
//			}
//			
//		}
//		return check;
		
		
		
		}
	
}
