package com.iu.base.member;

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
		return memberDAO.setMemberJoin(memberVO);
	}
		
	public int setMemberRoleAdd(MemberVO memberVO) throws Exception {
		return memberDAO.setMemberRoleAdd(memberVO);
	}
	
}
