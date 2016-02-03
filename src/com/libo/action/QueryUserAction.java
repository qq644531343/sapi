package com.libo.action;

import javax.servlet.annotation.WebServlet;

import com.libo.base.BaseServlet;
import com.libo.base.BaseServletModel;
import com.libo.dao.UserDAO;
import com.libo.model.BaseHTTPBean;
import com.libo.model.UserBean;
import com.libo.tools.JSON;

@WebServlet(urlPatterns = "/user/queryuser")
public class QueryUserAction extends BaseServlet {

	private static final long serialVersionUID = -53767802964589779L;

	@Override
	public void execute(BaseServletModel http) {

		try {
			String userId = http.getParam("userId");
			UserBean user = UserDAO.getInstance().findUserById(userId);
			http.print(JSON.toHTTPJSON(user));
		} catch (BaseHTTPBean e) {
			http.print(JSON.toJSONString(e));
		}
	}
}
