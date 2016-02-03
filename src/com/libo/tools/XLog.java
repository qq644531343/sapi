package com.libo.tools;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class XLog {
	
	public static Logger logger = null;

	public static void setup() {
		try {
			PropertyConfigurator
					.configure("/Users/libo/Desktop/java/workspace/sapi/src/log4j.properties");
			logger = Logger.getLogger(XLog.class);
			logger.info("log4j完成初始化");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
