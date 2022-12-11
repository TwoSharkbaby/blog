package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;
@Getter
public class PrincipalDetail implements UserDetails{

	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(() -> {
			return "ROLE_"+user.getRole(); // 계정이 갖고 있는 권한 목록을 리턴
		});                                // 권한이 여러개일때 루프를 돌려서 만듬
		return collectors;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	public String getEmail() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정이 만료되지 않았는지 true : 만료안됨
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정이 잠겨있지 않았는지 true : 잠기지 않음
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {  // 비밀번호가 만료 되지 않았는지 true : 만료되지 않음
		return true;
	}

	@Override
	public boolean isEnabled() {  // 계정 확성화가 되어 있는지 true : 활성화되어 있음
		return true;
	}
}
