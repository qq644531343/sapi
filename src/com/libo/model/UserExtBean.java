package com.libo.model;

public class UserExtBean extends UserBean {
	
	private String cityName;
	private String address;
	private int level;
	private String registTime;
	private String lastLoginTime;
	private float lonX;
	private float latY;
	
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public float getLonX() {
		return lonX;
	}
	public void setLonX(float lonX) {
		this.lonX = lonX;
	}
	public float getLatY() {
		return latY;
	}
	public void setLatY(float latY) {
		this.latY = latY;
	}

	
}
