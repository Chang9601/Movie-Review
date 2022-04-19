package com.chang.recmv.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class LoginFailureHandler implements AuthenticationFailureHandler {

	private final String url = "/login?error=true";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String msg = null;
		
		if(exception instanceof AuthenticationException) {
			msg = "존재하지 않는 아이디";
		} else if(exception instanceof BadCredentialsException) {
			msg = "아이디 또는 비밀번호 잘못";
		}
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(url).forward(request, response);
	}
}
