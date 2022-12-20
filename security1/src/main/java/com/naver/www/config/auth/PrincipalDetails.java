package com.naver.www.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.naver.www.model.User;

import lombok.Data;
import lombok.Getter;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

	private User user;
	private Map<String, Object> attributes;
	
	// 일반 로그인
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// OAuth 로그인
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override  // User 권한을 리턴하는곳
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(()->{
			return user.getRole();
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override  // 해당 계정이 만료되었는게 아닌지
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override  // 해당 계정이 잠금 또는 휴면 등이 되어있지는 않는지
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override  // 비밀번호 변경한지 오래되지 않았는지 체크
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override  // 계정이 활성화 되어 있는지 휴면 등이 되어있지는 않는지
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return null;
	}

}
