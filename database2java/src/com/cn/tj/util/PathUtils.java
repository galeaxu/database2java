package com.cn.tj.util;

import java.io.File;
import java.io.IOException;

/**
 * @author zhaogl
 * @Description:
 * @date 2019年3月23日 下午1:34:03
 * @version 1.0
 * @since 1.0
 */
public class PathUtils {

	/**
	 * 根据包名，生成包目录
	 */
	public static String getFilePathFromPackage(String packageName) {

		String str = packageName; // "jk.zmn.auto.dfd";
		str = str.replace(".", "\\");
		File file = new File("");
		String canonicalPath = null;
		try {
			canonicalPath = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}

		canonicalPath += "\\src\\" + str;
		// String resource =
		// Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		// resource = resource.substring(1);
		// System.out.println(resource);
		File packageFile = new File(canonicalPath);
		if (!packageFile.exists()) {
			packageFile.mkdirs();
		}

		return canonicalPath;
	}
}
