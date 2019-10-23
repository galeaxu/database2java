package com.cn.tj.bean;
/**
  * @author zhaogl
  * @Description:用来封装查询出来的表信息
  * @date 2019年3月22日 上午9:50:41
  * @version 1.0
  * @since 1.0
  */

import java.util.List;
import java.util.Map;

public class TableInfo {

	/** 表名 */
	private String name;

	/** 存放字段信息， 字段名和字段信息 */
	private Map<String, ColumnInfo> columns;

	/** 存放主键 */
	private ColumnInfo onlyPriKey;

	/** 联合主键 */
	private List<ColumnInfo> priKeys;

	public TableInfo() {
	}

	public TableInfo(String name, Map<String, ColumnInfo> columns, ColumnInfo onlyPriKey) {
		this.name = name;
		this.columns = columns;
		this.onlyPriKey = onlyPriKey;
	}

	public TableInfo(String name, Map<String, ColumnInfo> columns, List<ColumnInfo> priKeys) {
		this.name = name;
		this.columns = columns;

		this.priKeys = priKeys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, ColumnInfo> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, ColumnInfo> columns) {
		this.columns = columns;
	}

	public ColumnInfo getOnlyPriKey() {
		return onlyPriKey;
	}

	public void setOnlyPriKey(ColumnInfo onlyPriKey) {
		this.onlyPriKey = onlyPriKey;
	}

	public List<ColumnInfo> getPriKeys() {
		return priKeys;
	}

	public void setPriKeys(List<ColumnInfo> priKeys) {
		this.priKeys = priKeys;
	}
}
