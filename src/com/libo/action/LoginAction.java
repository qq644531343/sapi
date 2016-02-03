package com.libo.action;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import com.libo.base.BaseServlet;
import com.libo.base.BaseServletModel;
import com.libo.constraint.MsgString;
import com.libo.constraint.ServerConfig;
import com.libo.dao.UserDAO;
import com.libo.model.BaseHTTPBean;
import com.libo.model.UserBean;
import com.libo.tools.JSON;

@WebServlet(urlPatterns = "/login")
public class LoginAction extends BaseServlet {

	private static final long serialVersionUID = 1141551127804532589L;

	@Override
	public void execute(BaseServletModel http) {
		
		HttpSession session = null;
		if (ServerConfig.supportJSP) {
			 session = http.request.getSession();
			 session.setAttribute("loginUser", null);
		}
		
		try {
			String username = http.getParam("username");
			String password = http.getParam("password");
			
			UserBean user = UserDAO.getInstance().findUserByUsername(username);
			if (user != null) {
				if (!user.getPassword().equals(password)) {
					http.println(new BaseHTTPBean(MsgString.CODE_ERROR_USERFAIL, MsgString.MSG_ERROR_USERFAIL).description());
				}else{
					http.println(JSON.toHTTPJSON(user));
					if (ServerConfig.supportJSP) {
						session.setAttribute("loginUser", user);
						session.setAttribute("userId", user.getUserId());
						session.setAttribute("loginToken", user.getLoginToken());
					}
				}
			}else {
				http.println(new BaseHTTPBean(MsgString.CODE_ERROR_USERFAIL, MsgString.MSG_ERROR_USERFAIL).description());
			}
		} catch (BaseHTTPBean e) {
			http.println(e.description());
		}
		
	}

}
