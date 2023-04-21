package com.iu.base.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception {
		return memberDAO.getMemberLogin(memberVO);
	}
	
	public int setMemberJoin(MemberVO memberVO) throws Exception {
		memberVO.setEnabled(true);
		int result = memberDAO.setMemberJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("userName", memberVO.getUserName());
		map.put("num", 3);
		result = memberDAO.setMemberRoleAdd(map);
		
		return result;
	}
	
	public boolean idDuplicateCheck(MemberVO memberVO) throws Exception {
		return memberDAO.idDuplicateCheck(memberVO);
	}
	
}
