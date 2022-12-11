package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;

@Configuration // 빈등록(ioc관리)
@EnableWebSecurity // 시큐리티 필터 추가
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증은 미리 체크
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  // lucy(자바스크립트 공격) 신규프로젝트시 적용하여 작성
		http.csrf().disable() // 테스트로 인하여 잠궈둠
				.authorizeHttpRequests((authz) -> {
					try {
						authz.antMatchers("/", "/auth/**", "/css/**", "/js/**", "/image/**").permitAll().anyRequest()
								.authenticated().and().formLogin().loginPage("/auth/loginForm")
								.loginProcessingUrl("/auth/loginProc").defaultSuccessUrl("/");
					} catch (Exception e) { // 로그아웃은 디폴트로 /logout으로 되어있음
						throw new RuntimeException(e);
					}
				}).httpBasic(Customizer.withDefaults());
		return http.build();
		
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(principalDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
