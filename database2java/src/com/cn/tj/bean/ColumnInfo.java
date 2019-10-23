package com.cn.tj.bean;

/**
 * @author zhaogl
 * @Description:
 * @date 2019年3月22日 下午4:20:19
 * @version 1.0
 * @since 1.0
 */
public class ColumnInfo {

	/** 字段名称 */
	private String name;

	/** 字段数据类型 */
	private String dataType;
	
	/**	字段键类型	 */
	private int keyType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public ColumnInfo() {
    }
	
	public ColumnInfo(String name, String dataType,int keyType) {
		super();
		this.name = name;
		this.dataType = dataType;
		this.keyType = keyType;
	}

	public int getKeyType() {
		return keyType;
	}

	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}

}
