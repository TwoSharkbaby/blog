package com.naver.www.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.naver.www.config.auth.PrincipalDetailsService;
import com.naver.www.config.oauth.PrincipalOauth2UserService;
import com.naver.www.repository.UserRepository;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

	// 1. 코드받기(인증) 2. 엑세스토큰(권한) 3. loaduser에서 사용자프로필 정보 가져오기
	// 4. 추가정보가 필요없다면 자동회원가입(쇼핑몰등에서 추가 정보가 필요할때 추가정보 입력하는 창으로 유도)
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CorsConfig corsConfig;
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PrincipalDetailsService principalDetailService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				// .and()
				// .formLogin().disable()
				// .httpBasic().disable()
				// .apply(new MyCustomDsl()) // 커스텀 필터 등록
				// .and()
				.authorizeRequests(authroize -> {
					try {
						authroize.antMatchers("/user/**")
								.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
								.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
								.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')").anyRequest().permitAll().and()
								.formLogin().loginPage("/loginForm").loginProcessingUrl("/login")
								.defaultSuccessUrl("/")  // 권한이 필요한곳에서 로그인페이지로 이동한뒤 성공하면
								.and().oauth2Login().loginPage("/loginForm") // 원래 있던곳으로 보내고 그게 아니면 "/" 로 보낸다
								.userInfoEndpoint().userService(principalOauth2UserService); 
															
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).httpBasic(Customizer.withDefaults()).build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(passwordEncoder());
	}
	
//	public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//			http
//					.addFilter(corsConfig.corsFilter())
//					.addFilter(new JwtAuthenticationFilter(authenticationManager))
//					.addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
//		}
//	}

}
