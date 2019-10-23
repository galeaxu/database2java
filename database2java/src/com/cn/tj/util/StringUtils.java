package com.cn.tj.util;

/**
 * @author zhaogl
 * @Description:
 * @date 2019年3月23日 下午12:11:06
 * @version 1.0
 * @since 1.0
 */
public class StringUtils {

	/**
	 * 将字符串第一个字符大写，并去掉其中的下划线
	 */
	public static String UpFirstString(String s) {
		s = s.replace("_", "");
		s = s.substring(0, 1).toUpperCase() + s.substring(1);
		return s;
	}

	/**
	 * 去掉字符串中的下划线
	 */
	public static String trimUnderLine(String s) {
		return s.replace("_", "");
	}

}
