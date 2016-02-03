package com.libo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import orbit.base.util.ID;

import org.apache.commons.lang.StringUtils;

import com.libo.constraint.MsgString;
import com.libo.constraint.ServerConfig;
import com.libo.tools.LoginTool;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (!ServerConfig.login) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		
		boolean logined = false;
		logined = filter(hRequest, hResponse);
		if (!logined) {
			
		}else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

	public boolean filter(HttpServletRequest request , HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String loginToken = request.getParameter("loginToken");
		
		if (ServerConfig.supportJSP) {
			HttpSession session = request.getSession();
			userId = (String)session.getAttribute("userId");
			loginToken = (String)session.getAttribute("loginToken");
		}
		
		if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(loginToken)) {
			boolean res = LoginTool.checkLoginToken(loginToken, userId);
			if (res == true) {
				return true;
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
		response.addHeader("code", MsgString.CODE_NO_LOGIN);
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return false;
	}
	
}
