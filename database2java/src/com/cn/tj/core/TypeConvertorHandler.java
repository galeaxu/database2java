package com.cn.tj.core;

/**
 * @author zhaogl
 * @Description:
 * @date 2019年3月23日 下午1:05:48
 * @version 1.0
 * @since 1.0
 */
public interface TypeConvertorHandler {
	/** java数据类型转换成数据库数据类型 */
	public String JavaType2JdbcType(String javaTypeData);

	/** 数据库数据类型转换成java数据类型 */
	public String JdbcType2JavaType(String jdbcTypeData);
}
