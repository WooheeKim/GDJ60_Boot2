package com.iu.base.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberVO implements UserDetails {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String passwordCk;
	@NotBlank
	private String name;
	@Email
	private String email;
	@Past
	private Date birth;
	
	private Date lastTime;
	
	private List<RoleVO> roleVOs;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO:roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));			
		}
		
		return authorities;
	}

//	@Override
//	public String getUsername() {
//		username(id) 반환
//		return null;
//	}
	
//	@Override
//	public String getPassword() {
//		password 반환
//		return null;
//	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정의 만료 여부
		// true : 계정이 만료가 안됨
		// false : 계정이 만료 됨, 로그인 안됨
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠김 여부
		// true : 계정이 잠기지 않음
		// false : 계정이 잠김, 로그인 안됨
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 패스워드 만료 여부
		// true : 패스워드가 만료 안됨
		// false : 패스워드가 만료 됨, 로그인 안됨
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		// 계정의 사용 가능 여부
		// true : 계정 활성화
		// false : 계정 비활성화, 로그인 안됨
		return true;
	}

}
