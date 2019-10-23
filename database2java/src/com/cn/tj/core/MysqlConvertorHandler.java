package com.cn.tj.core;

/**
 * @author zhaogl
 * @Description:
 * @date 2019年3月23日 下午1:06:57
 * @version 1.0
 * @since 1.0
 */
public class MysqlConvertorHandler implements TypeConvertorHandler {

	@Override
	public String JavaType2JdbcType(String javaTypeData) {
		return null;
	}

	@Override
	public String JdbcType2JavaType(String jdbcTypeData) {
		// varchar-->String
		if ("varchar".equalsIgnoreCase(jdbcTypeData) || "char".equalsIgnoreCase(jdbcTypeData)) {
			return "String";
		} else if ("int".equalsIgnoreCase(jdbcTypeData) || "tinyint".equalsIgnoreCase(jdbcTypeData)
				|| "smallint".equalsIgnoreCase(jdbcTypeData) || "integer".equalsIgnoreCase(jdbcTypeData)) {
			return "Integer";
		} else if ("bigint".equalsIgnoreCase(jdbcTypeData)) {
			return "Long";
		} else if ("double".equalsIgnoreCase(jdbcTypeData) || "float".equalsIgnoreCase(jdbcTypeData)) {
			return "Double";
		} else if ("clob".equalsIgnoreCase(jdbcTypeData)) {
			return "CLob";
		} else if ("blob".equalsIgnoreCase(jdbcTypeData)) {
			return "BLob";
		} else if ("date".equalsIgnoreCase(jdbcTypeData)) {
			return "Date";
		} else if ("time".equalsIgnoreCase(jdbcTypeData)) {
			return "Time";
		} else if ("timestamp".equalsIgnoreCase(jdbcTypeData)) {
			return "Timestamp";
		}
		return null;
	}

}
