package com.libo.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import sun.util.logging.resources.logging;

import com.libo.constraint.MsgString;
import com.libo.constraint.ServerConfig;
import com.libo.tools.XLog;
import com.mysql.jdbc.log.Log;

/**
 * Servlet Filter implementation class BaseFilter
 */
public class BaseFilter implements Filter {
	
	private String excludedPages;  
	private String[] excludedPageArray;

    /**
     * Default constructor. 
     */
    public BaseFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//有过滤器时，需第一行设置编码，否则会出现各种乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(!ServerConfig.sign) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		
		boolean isExcludePage = false;
		for (String page : excludedPageArray) {
			if (hRequest.getServletPath().equals(page)) {
				isExcludePage = true;
				break;
			}
		}
		
		if (isExcludePage) {
			chain.doFilter(request, response);
		}else {
			boolean res = filter(hRequest, hResponse);
			if (res) {
				chain.doFilter(request, response);
			}
		}	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		excludedPages = fConfig.getInitParameter("excludedPages");
		if (StringUtils.isNotEmpty(excludedPages)) {
			excludedPageArray = excludedPages.split(",");
		}
	}
	
	private boolean filter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		XLog.logger.info(uri);
		
		String sign = request.getParameter("sign");
		if (sign == null) {
			sign = (String) request.getSession().getAttribute("sign");
		}
		if (StringUtils.isNotEmpty(sign) && "123456".equals(sign)) {
			return true;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
		response.addHeader("code", MsgString.CODE_NO_SIGN);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return false;
	}

}
