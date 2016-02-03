package com.libo.exception;

import javax.servlet.annotation.WebServlet;

import com.libo.base.BaseServlet;
import com.libo.base.BaseServletModel;
import com.libo.constraint.MsgString;
import com.libo.model.BaseHTTPBean;
import com.libo.tools.JSON;

@WebServlet(urlPatterns="/error")
public class BaseException extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void executeGet(BaseServletModel http) {
		this.execute(http);
	}
	
	@Override
	public void execute(BaseServletModel http) {
		
		BaseHTTPBean bean = null;
		int code = http.response.getStatus();
		if (code >= 400 && code < 500) {
			bean = new BaseHTTPBean(MsgString.CODE_NOTFOUND, MsgString.MSG_NOTFOUND);
		}else if(code >= 500 && code < Integer.parseInt(MsgString.CODE_NO_SIGN)) {
			bean = new BaseHTTPBean(MsgString.CODE_SERVER_ERROR, MsgString.MSG_SERVER_ERROR);
		}else {
			String codeString = http.response.getHeader("code");
			if (codeString != null) {
				code = Integer.parseInt(codeString);
				if(code == Integer.parseInt(MsgString.CODE_NO_SIGN)) {
					bean = new BaseHTTPBean(MsgString.CODE_NO_SIGN, MsgString.MSG_NO_SIGN);
				}else if(code == Integer.parseInt(MsgString.CODE_NO_LOGIN)) {
					bean = new BaseHTTPBean(MsgString.CODE_NO_LOGIN, MsgString.MSG_NO_LOGIN);
				}
			}
		}
			
		if (bean == null) {
			System.out.println("出现未知错误:"+code);
			bean = new BaseHTTPBean(MsgString.CODE_UNKNOW, MsgString.MSG_UNKNOW);
		}
		
		String jsonString = JSON.toJSONString(bean);
		http.println(jsonString);
	}


}
