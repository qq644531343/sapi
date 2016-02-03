package com.libo.action;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.libo.base.BaseServlet;
import com.libo.base.BaseServletModel;
import com.libo.constraint.MsgString;
import com.libo.dao.UserDAO;
import com.libo.model.BaseHTTPBean;
import com.libo.model.UserBean;
import com.libo.tools.JSON;
import com.libo.tools.StringTool;

@WebServlet(urlPatterns = "/user/updateUser")
public class UpdateUserAction extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(BaseServletModel http) {
		
		Map<String, String[]> params = http.request.getParameterMap();
		Iterator<String> keys = params.keySet().iterator();
		String userId = null;
		try {
			userId = http.getParam("userId");
		} catch (BaseHTTPBean e) {
			http.println(new BaseHTTPBean(MsgString.CODE_ERROR_USERID, MsgString.MSG_ERROR_USERID));
		}
		
		try {
			@SuppressWarnings("rawtypes")
			Class userClazz =  Class.forName("com.libo.model.UserBean");
			UserBean user = new UserBean();
			user.setUserId(userId);
			
			while (keys.hasNext()) {
				String key = keys.next();
				if (key == null || key.equals("userId") || key.equals("username") || key.equals("loginToken")) {
					continue;
				}
				@SuppressWarnings("unchecked")
				Method method = userClazz.getMethod("set"+StringTool.toUpperCaseFirstOne(key), String.class);
				String value = http.getParamOption(key);
				if(value != null) {
					method.invoke(user, value);
				}
				
			}
			
			boolean result = UserDAO.getInstance().insertUser(user, true);
			if (result == true) {
				http.println(new BaseHTTPBean().description());
			}else {
				http.println(new BaseHTTPBean(MsgString.CODE_UNKNOW, MsgString.MSG_UNKNOW).description());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
