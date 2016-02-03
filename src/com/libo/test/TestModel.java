package com.libo.test;

import java.util.HashMap;
import java.util.Map;

import com.libo.tools.JSON;

public class TestModel {
	private String name;
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public TestModel(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "TestModel [name=" + name + ", age=" + age + "]";
	}
	public TestModel() {
		super();
	}
	
	public static void main(String[] args) {
		
		String mallId = "123";
		
		Map<String, Map<String, String>> map = new HashMap<String, Map<String,String>>();
		
		Map<String, String> timeline_feedMap = new HashMap<String, String>();
		timeline_feedMap.put("mallId", mallId);
		Map<String, String> timeline_platMap = new HashMap<String, String>();
		timeline_platMap.put("mallId", mallId);
		
		map.put("timeline_feed", timeline_feedMap);
		map.put("timeline_plat", timeline_platMap);
		
		System.out.println(JSON.toJSONString(map));
		System.out.println(JSON.toHTTPJSON(map));
	}
	
}
