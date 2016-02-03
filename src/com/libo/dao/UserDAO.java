package com.libo.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.libo.constraint.MsgString;
import com.libo.db.DBFactory;
import com.libo.model.BaseHTTPBean;
import com.libo.model.UserBean;
import com.libo.tools.StringTool;

public class UserDAO {

	private UserDAO() {
	}

	private static final UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	public UserBean findUserById(String userId) throws BaseHTTPBean {
		return this.findUser("userId", userId);
	}

	public UserBean findUserByUsername(String username) throws BaseHTTPBean {
		return this.findUser("username", username);
	}

	public UserBean findUser(String key, String value) throws BaseHTTPBean {
		UserBean user = null;

		if (!StringUtils.isNotEmpty(value)) {
			return user;
		}

		String sql = "select * from user where " + key + "='" + value + "';";
		Connection connection = DBFactory.getConnection();

		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			if (set.next()) {
				String userId = set.getString("userId");
				String username = set.getString("username");
				String password = set.getString("password");
				String platform = set.getString("platform");
				String userNick = set.getString("userNick");
				String userIcon = set.getString("userIcon");
				String userEmail = set.getString("userEmail");
				String userPhone = set.getString("userPhone");
				String userSex = set.getString("userSex");

				user = new UserBean();
				user.setUserId(userId);
				user.setUsername(username);
				user.setPassword(password);
				user.setPlatform(platform);
				user.setUserNick(userNick);
				user.setUserIcon(userIcon);
				user.setUserEmail(userEmail);
				user.setUserPhone(userPhone);
				user.setUserSex(userSex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BaseHTTPBean(MsgString.CODE_ERROR_DB_OPERATION, MsgString.MSG_ERROR_DB_OPERATION);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return user;
	}

	public boolean insertUser(UserBean user, boolean isUpdate)
			throws BaseHTTPBean {

		boolean res = false;
		if (user == null) {
			return res;
		}

		Connection connection = DBFactory.getConnection();

		PreparedStatement statement = null;
		try {
			if (!isUpdate) {
				String sql = "insert into user values(?,?,?,?,?,?,?,?,?);";
				statement = connection.prepareStatement(sql);
				statement.setString(1, user.getUserId());
				statement.setString(2, user.getUsername());
				statement.setString(3, user.getPassword());
				statement.setString(4, user.getPlatform());
				statement.setString(5, user.getUserNick());
				statement.setString(6, user.getUserIcon());
				statement.setString(7, user.getUserEmail());
				statement.setString(8, user.getUserPhone());
				statement.setString(9, user.getUserSex());

				if (statement.executeUpdate() != 0) {
					res = true;
				}
			}else {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("update user set ");
				
				try {
					Class userClazz = Class.forName("com.libo.model.UserBean");
					Method[] methods = userClazz.getDeclaredMethods();
					for (Method method : methods) {
						String methodName = method.getName();
						if (methodName.startsWith("get")) {
							
							String value =  (String)method.invoke(user, null);
							
							if (StringUtils.isNotEmpty(value)  
											  && !"getUserId".equals(methodName) 
											  && !"getUsername".equals(methodName) 
											  && !"getLoginToken".equals(methodName)) {
								String varName = StringTool.toLowerCaseFirstOne(methodName.substring(3));
								stringBuilder.append(varName + "='" + value + "' , ");
							}
						}
					}
					if (stringBuilder.toString().endsWith(", ")) {
						stringBuilder.deleteCharAt(stringBuilder.length() - 2);
					}
					stringBuilder.append("where userId='"+user.getUserId() + "';");
					System.out.println(stringBuilder.toString());
					statement = connection.prepareStatement(stringBuilder.toString());
					if (statement.executeUpdate() != 0) {
						res = true;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (SQLException e) {

			if (e.getMessage().startsWith("Duplicate entry")) {
				throw new BaseHTTPBean(MsgString.CODE_ERROR_USERREPEAT,
						MsgString.MSG_ERROR_USERREPEAT);
			} else {
				e.printStackTrace();
			}
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

}
