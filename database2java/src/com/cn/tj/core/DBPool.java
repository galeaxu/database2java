package com.cn.tj.core;
/**
  * @author zhaogl
  * @Description:建立数据库连接池
  * @date 2019年3月21日 下午7:20:32
  * @version 1.0
  * @since 1.0
  */

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DBPool {
	/**
	 * 连接池对象
	 */
	List<Connection> pool;

	/**
	 * 最大连接数
	 */
	private static final int POOL_MAX_SIZE = 10;

	/**
	 * 最小连接池
	 */
	private static final int POOL_MIN_SIZE = 1;

	private void initPool() {
		if (pool == null) {
			pool = new ArrayList<Connection>();
		}
		while (pool.size() < POOL_MIN_SIZE) {
			// 往连接池脸面添加数据
			pool.add(DBManager.createConnection());
			System.out.println("初始化池，池中连接数：" + pool.size());
		}
	}

	/**
	 * 从连接池中取出一个连接
	 */
	public synchronized Connection getConnection() {
		int last_index = pool.size() - 1;
		Connection conn = pool.get(last_index);
		pool.remove(last_index);
		return conn;
	}

	/**
	 * 将连接放回到池子里
	 */
	public synchronized void close(Connection conn) {
		if (pool.size() >= POOL_MAX_SIZE) {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		} else {
			pool.add(conn);
		}
	}

	/**
	 * 初始化连接池
	 */
	public DBPool() {
		this.initPool();
	}

}
