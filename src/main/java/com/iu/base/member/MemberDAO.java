package com.iu.base.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception;
	
	public int setMemberJoin(MemberVO memberVO) throws Exception;
	
	public int setMemberRoleAdd(MemberVO memberVO) throws Exception;
	
	public int userNameCheck(String userName) throws Exception;
	
	public MemberVO getMemberPage(MemberVO memberVO) throws Exception;
	
	public int setMemberUpdate(MemberVO memberVO) throws Exception;
	
	
}
