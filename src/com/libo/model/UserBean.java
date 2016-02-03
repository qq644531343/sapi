package com.libo.model;

import com.libo.tools.LoginTool;

public class UserBean {
	
	private String userId;
	private String username;
	private String password;
	private String platform; //登录平台，支持sina qq weixin self
	private String userNick;
	private String userIcon;
	private String userEmail;
	private String userPhone;
	private String userSex;
	private String loginToken;
	
	public String getLoginToken() {
		if (loginToken == null) {
			loginToken = LoginTool.generatorLoginToken(userId);
		}
		return loginToken;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}	
	
	
	
}
