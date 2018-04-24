package com.crwork.web.util;

import java.io.InputStream;
import java.util.Properties;

public class FileUtil {

	public static final String[] sqlpro_value = readsqlpro();

	private static String[] readsqlpro() {
		Properties sqlprop = new Properties();
		String[] message = new String[4];
		try {
			InputStream sqlpropin = FileUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			sqlprop.load(sqlpropin);
			message[0] = sqlprop.getProperty("jdbc_driverClassName");
			message[1] = sqlprop.getProperty("jdbc_url");
			message[2] = sqlprop.getProperty("jdbc_username");
			message[3] = sqlprop.getProperty("jdbc_password");
			sqlpropin.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return message;
	}
}
