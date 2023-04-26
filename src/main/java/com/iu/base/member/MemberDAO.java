package com.iu.base.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public List<MemberVO> getBirth() throws Exception;
	
	public int setEnabled() throws Exception;
	
	public List<MemberVO> allUserName() throws Exception;
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception;
	
	public int getMemberLogout(MemberVO memberVO) throws Exception;
	
	public int setMemberJoin(MemberVO memberVO) throws Exception;
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception;
	
	public int setMemberRoleAdd(Map<String, Object> map) throws Exception;
	
	public int userNameCheck(String userName) throws Exception;
	
	public MemberVO getMemberPage(MemberVO memberVO) throws Exception;
	
	public int setMemberUpdate(MemberVO memberVO) throws Exception;
	
	
}
