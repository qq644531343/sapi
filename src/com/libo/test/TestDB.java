package com.libo.test;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import orbit.base.user.User;
import orbit.base.util.ID;

import com.libo.dao.UserDAO;
import com.libo.db.DBFactory;
import com.libo.model.BaseHTTPBean;
import com.libo.model.UserBean;
import com.libo.tools.JSON;

public class TestDB {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		testConnection();
		
//		insertUser();
		
		//测试反射
		testReflect();
	}
	
	public static void insertUser() {
		
		for (int i = 0; i < 100; i++) {
			UserBean user = new UserBean();
			user.setUserId(ID.next());
			user.setUsername("liulibo" + i);
			user.setPassword("password" + i);
			user.setPlatform("qq"+i);
			user.setUserNick("nick"+i);
			user.setUserIcon("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3218985800,435701254&fm=58");
			user.setUserEmail("abc"+i+"@qq.com");
			user.setUserPhone("1316179715"+i);
			user.setUserSex("男");
			
			try {
				UserDAO.getInstance().insertUser(user, false);
			} catch (BaseHTTPBean e) {
				System.out.println(e.description());
			}
		}
	}
	
	public static void testConnection() throws Exception{
		Connection connection = DBFactory.getConnection();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery("select * from user");
		while (set.next()) {
			String userId = set.getString("userId");
			String username = set.getString("username");
			String password = set.getString("password");
			String platform = set.getString("platform");
			String userNick = set.getString("userNick");
			String userIcon = set.getString("userIcon");
			String userEmail = set.getString("userEmail");	
			String userPhone = set.getString("userPhone");
			String userSex = set.getString("userSex");
			
			UserBean user = new UserBean();
			user.setUserId(userId);
			user.setUsername(username);
			user.setPassword(password);
			user.setPlatform(platform);
			user.setUserNick(userNick);
			user.setUserIcon(userIcon);
			user.setUserEmail(userEmail);
			user.setUserPhone(userPhone);
			user.setUserSex(userSex);
			
			System.out.println(JSON.toHTTPJSON(user));
		}	
		
		statement.close();
		connection.close();
	}
	
	public  static void testReflect() {
		try {
			Class clazz =  Class.forName("com.libo.test.TestModel");
			Method method = clazz.getMethod("setName", String.class);
			Object object = clazz.newInstance();
			method.invoke(object, "1234");
			System.out.println(object);
			
//			Class userClazz = Class.forName("com.libo.model.UserBean");
//			Method[] methods = userClazz.getDeclaredMethods();
//			for (Method method2 : methods) {
//				System.out.println(method2.getName());
//			}
			
			UserBean user = UserDAO.getInstance().findUserById("IGROYIFA000C");
			user.setUserEmail(null);
			user.setUserIcon(null);
			UserDAO.getInstance().insertUser(user, true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
