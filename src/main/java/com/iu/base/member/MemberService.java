package com.iu.base.member;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.error("=========Spring Security Login=========");
		log.error("========={}=========" ,username);
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberDAO.getMemberLogin(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}

	
	// 패스워드가 일치하는지 검증하는 메서드
	public boolean memberCheck(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean result = false;
		// false : error가 없음, 검증 성공
		// true : error가 있음, 검증 실패
		
		// 1. annotation 검증 결과
		result = bindingResult.hasErrors();
		
		// 2. password 일치 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCk())) {
			result = true;
			bindingResult.rejectValue("passwordCk", "member.password.notEqual");
		} 
		
		// 3. ID 중복 검사
		MemberVO checkMember = memberDAO.idDuplicateCheck(memberVO);
		
		if(checkMember != null) {
			result=true;
			bindingResult.rejectValue("username", "member.username.equal");
		}
		
		return result;
	}
	
	
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception {
		return memberDAO.getMemberLogin(memberVO);
	}
	
	public int setMemberJoin(MemberVO memberVO) throws Exception {
//		memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setMemberJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("num", 3);
		result = memberDAO.setMemberRoleAdd(map);
		
		return result;
	}	
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception {
		return memberDAO.idDuplicateCheck(memberVO);
	}
	
	public int getMemberLogout(MemberVO memberVO) throws Exception {
		return memberDAO.getMemberLogout(memberVO);
	}
	
	
}
