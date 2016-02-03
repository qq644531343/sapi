package com.libo.action;

import javax.servlet.annotation.WebServlet;

import orbit.base.util.ID;

import com.libo.base.BaseServlet;
import com.libo.base.BaseServletModel;
import com.libo.constraint.MsgString;
import com.libo.dao.UserDAO;
import com.libo.model.BaseHTTPBean;
import com.libo.model.UserBean;

@WebServlet(urlPatterns="/insertuser")
public class RegisterUserActoin extends BaseServlet {

	private static final long serialVersionUID = -4384214470403455847L;

	@Override
	public void execute(BaseServletModel http)  {

		UserBean user = new UserBean();

		user.setUserNick(http.getParamOption("userNick"));
		user.setUserIcon(http.getParamOption("userIcon"));
		user.setUserEmail(http.getParamOption("userEmail"));
		user.setUserPhone(http.getParamOption("userPhone"));
		user.setUserSex(http.getParamOption("userSex"));

		try {
			user.setUserId(ID.next());
			user.setUsername(http.getParam("username"));
			user.setPassword(http.getParam("password"));
			user.setPlatform(http.getParam("platform"));
			
			boolean result = UserDAO.getInstance().insertUser(user, false);
			
			if (result == true) {
				http.println(new BaseHTTPBean().description());
			}else {
				http.println(new BaseHTTPBean(MsgString.CODE_UNKNOW, MsgString.MSG_UNKNOW).description());
			}
		
		} catch (BaseHTTPBean e) {
			http.println(e.description());
		}

	}

}
