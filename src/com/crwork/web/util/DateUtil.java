package com.crwork.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public DateUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return dateNowStr
	 */
	public static String getLitterDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String dateNowStr = sdf.format(d);
		return dateNowStr;
	}
}
