package com.libo.tools;

import org.apache.commons.lang.StringUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class LoginTool {
	
	/**
	 * 根据userId生成loginToken
	 */
	public static String generatorLoginToken(String userId) {
		return "token"+userId;
	}
	
	/**
	 * 校验loginToken
	 */
	public static boolean checkLoginToken(String loginToken, String userId) {
		boolean res = false;
		
		if (StringUtils.isNotEmpty(loginToken) && StringUtils.isNotEmpty(userId) && 
				loginToken.equals("token"+userId)) {
			res = true;
		}
		
		return res;
	}
}
