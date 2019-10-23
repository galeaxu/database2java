package com.cn.tj.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cn.tj.bean.ColumnInfo;
import com.cn.tj.bean.TableInfo;
import com.cn.tj.util.JavaFileUtils;
import com.cn.tj.util.StringUtils;

/**
 * @author zhaogl
 * @Description:负责获取数据库的表结构和类结构的关系，并可以根据表结构生成类结构，
 * @date 2019年3月23日 下午2:38:18
 * @version 1.0
 * @since 1.0
 */
public class TableContext {
	/**
	 * 表名为key，表信息对象为value
	 */
	public static Map<String, TableInfo> tables = new HashMap<String, TableInfo>();

	/**
	 * 将po的class对象和表信息对象关联起来，便于重用！
	 */
	public static Map<Class, TableInfo> poClassTableMap = new HashMap<Class, TableInfo>();

	private TableContext() {
	};

	static {
		try {
			ResultSet rs = null;
			Connection con = DBManager.getConnection();
			DatabaseMetaData dbmd = con.getMetaData();
			// 从元数据中获取到所有的表名
			rs = dbmd.getTables(null, null, null, new String[] { "TABLE" });
			while (rs.next()) {
				String tableName = (String) rs.getObject("TABLE_NAME");
				TableInfo ti = new TableInfo(tableName, new HashMap<String, ColumnInfo>(), new ArrayList<ColumnInfo>());
				tables.put(tableName, ti);
				// 查询某一个表中所有的字段
				ResultSet set = dbmd.getColumns(null, "%", tableName, "%");
				while (set.next()) {
					ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"), set.getString("TYPE_NAME"), 0);
					ti.getColumns().put(set.getString("COLUMN_NAME"), ci);
				}
				// 查询出某一张表里面的所有的主键
				ResultSet primarySet = dbmd.getPrimaryKeys(null, "%", tableName);
				while (primarySet.next()) {
					ColumnInfo ci2 = (ColumnInfo) ti.getColumns().get(primarySet.getObject("COLUMN_NAME"));
					ci2.setKeyType(1); // 设置为主键类型
					ti.getPriKeys().add(ci2);
				}
				if (ti.getPriKeys().size() > 0) { // 取唯一主键。。方便使用。如果是联合主键。则为空！
					ti.setOnlyPriKey(ti.getPriKeys().get(0));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updatePoFile();
	}

	private static void updatePoFile() {
		JavaFileUtils.createJavaFileToPackage();
	}
}
