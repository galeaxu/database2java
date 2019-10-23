package com.cn.tj.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.cn.tj.bean.ColumnInfo;
import com.cn.tj.bean.Configuration;
import com.cn.tj.bean.TableInfo;
import com.cn.tj.bean.javaFeildInfo;
import com.cn.tj.core.DBManager;
import com.cn.tj.core.MysqlConvertorHandler;
import com.cn.tj.core.TableContext;
import com.cn.tj.core.TypeConvertorHandler;
import com.cn.tj.service.DBService;

/**
 * @author zhaogl
 * @Description:
 * @date 2019年3月23日 下午1:03:14
 * @version 1.0
 * @since 1.0
 */
public class JavaFileUtils {

	private static Configuration configuration = DBManager.getConfiguration();

	public static void createJavaFileToPackage() {
		Map<String, TableInfo> tables = DBService.tables;
		TypeConvertorHandler convertorHandler = null;
		if (configuration.getUseDB().equalsIgnoreCase("mysql")) {
			convertorHandler = new MysqlConvertorHandler();
		}
		for (TableInfo tableInfo : tables.values()) {
			createJavaFile(tableInfo, convertorHandler);
		}
	}

	private static void createJavaFile(TableInfo tableInfo, TypeConvertorHandler typeConvertorHandler) {
		// 得到所有的列信息
		Map<String, ColumnInfo> columns = tableInfo.getColumns();

		ArrayList<javaFeildInfo> javaFeildInfos = new ArrayList<>();
		Collection<ColumnInfo> values = columns.values();

		// 生成所有的java属性信息和get set方法
		for (ColumnInfo columnInfo : values) {
			javaFeildInfo javaFeild = createJavaFeild(columnInfo, typeConvertorHandler);
			javaFeildInfos.add(javaFeild);
		}

		StringBuilder sb = new StringBuilder();

		sb.append("package " + configuration.getPackageName() + ";\n\n");
		sb.append("import java.sql.*;\n");
		sb.append("import java.util.*;\n\n");
		sb.append("public class " + StringUtils.UpFirstString(tableInfo.getName()) + " {\n\n");

		for (javaFeildInfo javaFeildInfo : javaFeildInfos) {
			sb.append(javaFeildInfo.getFeildInfo());
		}
		sb.append("\n");
		for (javaFeildInfo javaFeildInfo : javaFeildInfos) {
			sb.append(javaFeildInfo.getGetFeildInfo());
		}
		for (javaFeildInfo javaFeildInfo : javaFeildInfos) {
			sb.append(javaFeildInfo.getSetFeildInfo());
		}

		sb.append("}\n");
		// System.out.println(sb.toString());

		String classInfo = sb.toString();

		String filePathFromPackage = PathUtils.getFilePathFromPackage(configuration.getPackageName());
		File file = new File(filePathFromPackage, StringUtils.UpFirstString(tableInfo.getName()) + ".java");
		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

			bufferedOutputStream.write(classInfo.getBytes(), 0, classInfo.getBytes().length);

			bufferedOutputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(
				"表" + tableInfo.getName() + "对应的类" + StringUtils.UpFirstString(tableInfo.getName()) + "已自动生成..");
	}

	private static javaFeildInfo createJavaFeild(ColumnInfo columnInfo, TypeConvertorHandler convertorHandler) {
		// 将字段数据类型转换成java数据类型
		String javaType = convertorHandler.JdbcType2JavaType(columnInfo.getDataType());

		String columnName = columnInfo.getName().toLowerCase();
		javaFeildInfo feildInfo = new javaFeildInfo();
		// 生成属性语句
		feildInfo.setFeildInfo("\tprivate " + javaType + " " + StringUtils.trimUnderLine(columnName) + ";\n");

		StringBuilder sb = new StringBuilder();
		sb.append("\tpublic " + javaType + " " + "get" + StringUtils.UpFirstString(columnName) + "() {\n");

		sb.append("\t\treturn " + columnName + ";\n");

		sb.append("\t}\n");

		feildInfo.setGetFeildInfo(sb.toString());

		StringBuilder sb1 = new StringBuilder();

		sb1.append("\tpublic void " + "set" + StringUtils.UpFirstString(columnName) + "(" + javaType + " " + columnName
				+ ") {\n");

		sb1.append("\t\t this." + columnName + " = " + columnName + ";\n");

		sb1.append("\t}\n");

		feildInfo.setSetFeildInfo(sb1.toString());

		return feildInfo;
	}

}
