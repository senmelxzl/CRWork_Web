package com.crwork.web.util;

import java.text.DecimalFormat;

public class DataFormatUtil {
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将数据保留两位小数
	 */
	public static double getTwoDecimal(double num) {
		DecimalFormat dFormat = new DecimalFormat("#.00");
		String yearString = dFormat.format(num);
		Double temp = Double.valueOf(yearString);
		return temp;
	}
}
