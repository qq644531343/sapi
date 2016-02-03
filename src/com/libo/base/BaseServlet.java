package com.libo.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libo.constraint.MsgString;
import com.libo.model.BaseHTTPBean;

/**
 * Servlet基类
 */
public abstract class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public BaseServlet() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseServletModel http = new BaseServletModel(request, response);
		try {
			executeGet(http);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			http.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BaseServletModel http = new BaseServletModel(request, response);
		try {
			execute(http);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			http.close();
		}
	}
	
	public abstract void execute(BaseServletModel http);
	
	public void executeGet(BaseServletModel http) {
		http.println(new BaseHTTPBean(MsgString.CODE_NO_GET, http.getPath() + MsgString.MSG_NO_GET).description());
	}
	
}
