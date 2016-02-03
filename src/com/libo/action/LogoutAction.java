package com.libo.action;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import com.libo.base.BaseServlet;
import com.libo.base.BaseServletModel;
import com.libo.constraint.ServerConfig;
import com.libo.model.BaseHTTPBean;

@WebServlet(urlPatterns = "/user/logout")
public class LogoutAction extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(BaseServletModel http) {
		HttpSession session = http.request.getSession();
		
		http.print(new BaseHTTPBean().description());
		if (ServerConfig.supportJSP) {
			session.removeAttribute("loginUser");
			session.removeAttribute("loginToken");
			session.removeAttribute("userId");
			session.invalidate();
		}
	}

}
