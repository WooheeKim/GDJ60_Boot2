package com.iu.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.iu.base.member.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		log.error("=============== Logout Success Handler ===============");
		log.error("=============== {} ===============", memberDAO);
		
		response.sendRedirect("/");
	
	}
	
}
