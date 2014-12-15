package com.nspaces.oss.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

/**
 * Authentication Handler for redirect the original inputed URL after login system.
 * 
 * @author Kelvin
 *
 */
@Service
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	/**
	 * 登录时通过密码验证后进行的操作
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication) 
			throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + "/pages/baike_list.jsp");
	}

}
