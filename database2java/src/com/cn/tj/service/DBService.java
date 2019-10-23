package com.cn.tj.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cn.tj.bean.ColumnInfo;
import com.cn.tj.bean.TableInfo;
import com.cn.tj.core.DBManager;
import com.cn.tj.util.JavaFileUtils;
import com.cn.tj.util.StringUtils;

/**
 * @author zhaogl
 * @Description:
 * @date 2019年3月21日 下午7:20:32
 * @version 1.0
 * @since 1.0
 */
public class DBService {

	/**
	 * 表名为key，表信息对象为value
	 */
	public static Map<String, TableInfo> tables = new HashMap<String, TableInfo>();

	/**
	 * 将po的class对象和表信息对象关联起来，便于重用！
	 */
	public static Map<Class, TableInfo> poClassTableMap = new HashMap<Class, TableInfo>();

	public static void main(String[] args) {
		
	}

	
}
