package com.libo.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.libo.constraint.MsgString;
import com.libo.model.BaseHTTPBean;
import com.mchange.v2.c3p0.*;
import com.mchange.v2.log.MLevel;
import com.mchange.v2.log.MLog;

public class DBFactory {
	
	private static  ComboPooledDataSource cpds = null;
	
	public static String DBName = "infodb";
	
	public static void setupDB() {
		
		MLog.getLogger().setLevel(MLevel.INFO);
//		System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
//		System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "INFO");
		MLog.info("DB初始化");
		
	    cpds = new ComboPooledDataSource(); 
		try {
			cpds.setDriverClass( "com.mysql.jdbc.Driver" );
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} 
		cpds.setJdbcUrl( "jdbc:mysql://localhost/"+DBName +"?useUnicode=true&characterEncoding=UTF-8"); 
		cpds.setUser("libo"); 
		cpds.setPassword("libo123");
		
		cpds.setMinPoolSize(3); 
		cpds.setAcquireIncrement(5); 
		cpds.setMaxPoolSize(30);
	}
	
	public static final Connection getConnection() throws BaseHTTPBean{
		try {
			if (cpds == null) {
				setupDB();
			}
			return cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BaseHTTPBean(MsgString.CODE_ERROR_DB_CONN, MsgString.MSG_ERROR_DB_CONN);
		}
	}
}
