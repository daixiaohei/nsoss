package com.nspaces.oss.config;
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler; 
import org.springframework.stereotype.Service;


@Service
public class AuthenticationFailHandlerImpl extends SimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

	/**
	 * 登录时密码或账号不正确时的操作
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + "/userMange/login.do?error=true");
	}

}
