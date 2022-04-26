package com.chang.recmv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.chang.recmv.config.auth.PrincipalDetailsService;

@Configuration
@EnableWebSecurity // Spring Security 설정 클래스 정의
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // @Secured 어노테이션 활성화, @PreAuthorize와 @PostAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PrincipalDetailsService principalDetailsService;
	
	public SecurityConfig(PrincipalDetailsService principalDetailsService) {
		this.principalDetailsService = principalDetailsService;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// 비밀번호 암호화 객체 Bean으로 등록(IoC 컨테이너)
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
 
	// 모든 인증을 처리하는 AuthenticationManager를 생성하기 위해 AuthenticationManagerBuilder 사용
	// 로그인 인증을 위해서 UserDetailsService를 구현하여 loadUserByUsername() 메서드 오버라이딩
	// DB의 암호환된 비밀번호와 비교하기 위해서 로그인 과정에서 전달받은 password를 BCryptPasswordEncoder로 암호화
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailsService).passwordEncoder(passwordEncoder());
	}
	
	// 인증을 무시할 경로 설정
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers( "/css/**", "/js/**", "/img/**");
	}

	// HTTP 요청에 대한 보안(인증과 권한) 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable(); // 나중에 토큰으로 수정 필요
		
		http.authorizeRequests() // HttpServletRequest에 따라 접근 제한
				.antMatchers("/users/**").authenticated() // user는 인증 필요
				.antMatchers("/api/**").authenticated() // user는 인증 필요
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // admin은 권한 필요
				.anyRequest().permitAll() // 나머지 권한없이 모두 허용
			.and()
				.formLogin() // form 기반 인증, /login으로 접근하면 Spring Security가 제공하는 기본 로그인 form 사용, HttpSession 사용
				.loginPage("/login") // 맞춤 로그인 페이지 사용
				.loginProcessingUrl("/login") // Spring Security 로그인 대신 처리
				.defaultSuccessUrl("/") // 로그인 성공 시 기본 페이지
			.and()
				.logout() // 로그아웃 대신 처리, WebSecurityConfigureAdpater가 자동으로 적용, /logout 접근 시 HTTP 세션 제거
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // CSRF 활성화 시 로그아웃 요청 기본적으로 POST(강제 로그아웃 방지 목적), logoutRequestMatcher를 통해서 GET으로 로그아웃
				.logoutSuccessUrl("/") // 로그아웃 성공 시 이동하는 페이지
				.invalidateHttpSession(false); // HTTP 세션 초기화
	}
}