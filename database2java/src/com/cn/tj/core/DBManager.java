package com.cn.tj.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.cn.tj.bean.Configuration;

/**
 * @author zhaogl
 * @Description:根据配置信息，维持连接对象的管理
 * @date 2019年3月21日 下午7:57:48
 * @version 1.0
 * @since 1.0
 */
public class DBManager {

	private static Configuration configuration;

	private static DBPool pool;

	/**
	 * 初始化configuration
	 */
	static {
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream("db.properties"));

			configuration = new Configuration();
			// 根据properties构建configuration
			configuration.setDriver(properties.getProperty("driver"));
			configuration.setPackageName(properties.getProperty("packageName"));
			configuration.setPassword(properties.getProperty("password"));
			configuration.setUrl(properties.getProperty("url"));
			configuration.setUseDB(properties.getProperty("useDB"));
			configuration.setUsername(properties.getProperty("username"));
			configuration.setPoolMinSize(Integer.parseInt(properties.getProperty("poolMinSize")));
			configuration.setPoolMaxSize(Integer.parseInt(properties.getProperty("poolMaxSize")));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 创建数据库连接
	 */
	public static Connection createConnection() {
		try {
			Class.forName(configuration.getDriver());
			return DriverManager.getConnection(configuration.getUrl(), configuration.getUsername(),
					configuration.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 得到数据库连接
	 */
	public static Connection getConnection() {
		if (pool == null) {
			pool = new DBPool();
		}
		return pool.getConnection();
	}

	/**
	 * 关闭数据库连接
	 */
	public static void close(Connection connection, PreparedStatement preparedStatement) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Configuration getConfiguration() {
		return configuration;
	}
}
