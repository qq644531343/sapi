package com.libo.base;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.libo.constraint.MsgString;
import com.libo.model.BaseHTTPBean;

public class BaseServletModel {

	public HttpServletRequest request;
	public HttpServletResponse response;
	public PrintWriter out;

	BaseServletModel(HttpServletRequest request, HttpServletResponse response) {
		super();

		try {
			response.setContentType("text/html;charset=UTF-8");

			this.request = request;
			this.response = response;
			this.out = response.getWriter();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void print(Object obj) {
		this.out.print(obj);
	}

	public void println(Object obj) {
		this.out.println(obj);
	}

	public void close() {
		synchronized (this) {
			if (this.out != null) {
				this.out.flush();
				this.out.close();
				this.out = null;
			}
		}
	}

	public String getParam(String key) throws BaseHTTPBean {

		if (!StringUtils.isNotEmpty(key))
			throw new BaseHTTPBean(MsgString.CODE_WARM_NOVALUE, "key为空");

		String value = request.getParameter(key);
		if (!StringUtils.isNotEmpty(value)) {
			throw new BaseHTTPBean(MsgString.CODE_WARM_NOVALUE, key + "没有赋值");
		}

		return value;
	}

	public String getParamOption(String key) {
		String value = "";
		value = request.getParameter(key);
		if (!StringUtils.isNotEmpty(value)) {
			value = "";
		}
		return value;
	}
	
	public String getPath() {
		return request.getServletPath();
	}

}
