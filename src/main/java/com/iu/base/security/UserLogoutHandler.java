package com.iu.base.security;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserLogoutHandler implements LogoutHandler {
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restKey;
	
	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String redirectUri;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
//		request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		MemberVO memberVO = (MemberVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, Object> atts = memberVO.getAttributes();
		
		Iterator<String> keys = atts.keySet().iterator();
		
		log.error("RestApiKey ::: {}",restKey);
		log.error("RedirectUri ::: {}",redirectUri);
		
		while(keys.hasNext()) {
			String key = keys.next();
			Object value = atts.get(key);
			log.error("Key ::: {}", key);
			log.error("Value ::: {}", value);
		}
		this.logoutAll();
	}
	
	private void logoutAll() {
		// 카카오계정과 함께 로그아웃
		// 1. 요청 준비
		RestTemplate restTemplate = new RestTemplate();
		
		// 2. header
		
		// 3. parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("client_id", restKey);
		params.add("logout_redirect_uri", redirectUri);
		
		// 4. 요청 객체 생성
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, null);
		
		// 5. 요청 발생
//		String response = restTemplate.getForObject("https://kauth.kakao.com/oauth/logout", String.class, request);
		ResponseEntity<String> response = restTemplate.getForEntity("https://kauth.kakao.com/oauth/logout?client_id="+restKey+"&logout_redirect_uri=http://localhost/member/socialLogout", String.class, request);
		String result = response.getBody();
		log.error("Logout Result ::: {}", result);
	}
	
}
