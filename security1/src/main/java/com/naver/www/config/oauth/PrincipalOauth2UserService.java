package com.naver.www.config.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.naver.www.config.auth.PrincipalDetails;
import com.naver.www.config.oauth.provider.FacebookUserInfo;
import com.naver.www.config.oauth.provider.GoogleUserInfo;
import com.naver.www.config.oauth.provider.NaverUserInfo;
import com.naver.www.config.oauth.provider.OAuth2UserInfo;
import com.naver.www.model.User;
import com.naver.www.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	

	// 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
	// 함수 종료시 @authenticationPrincipal 어노테이션이 만들어짐
	@Override  
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("userRequest : " + userRequest.getClientRegistration());
		System.out.println("userRequest : " + userRequest.getAccessToken().getTokenValue());
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		System.out.println("oauth2User : " + oauth2User.getAttributes());
		
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북 로그인 요청");
			oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인 요청");
			oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
		} else {
			System.out.println("구글과 페이스북, 네이버 로그인만 지원합니다");
		}
		
		String provider = oAuth2UserInfo.getProvider();  // google facebook
		String providerId = oAuth2UserInfo.getProviderId();
		String username = provider + "_" + providerId;  // google_112434184590715126038
		String password = passwordEncoder.encode("겟인데어");
		String role = "ROLE_USER";
		String email = oAuth2UserInfo.getEmail();
		
		User userEntiry = userRepository.findByUsername(username);
		
		if(userEntiry == null) {
			userEntiry = User.builder().username(username).password(password).email(email)
					.role(role).provider(provider).providerId(providerId).build();
			userRepository.save(userEntiry);
		}
		
		return new PrincipalDetails(userEntiry, oauth2User.getAttributes());
	}
	
}
