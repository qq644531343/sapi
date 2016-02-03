package com.libo.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.libo.db.DBFactory;
import com.libo.tools.XLog;

public class ServerStartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		XLog.setup();
		XLog.logger.info("服务器部署");
		DBFactory.setupDB();
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		XLog.logger.info("服务器停止服务");
	}

}
