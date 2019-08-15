package com.studyveloper.overtheflow.util.option;

public enum CategoryUnit implements OptionUnit {
	ID("category_id"),
	NAME("category_name"),
	SUPER_ID("category_super_id"),
	MEMBER_ID("member_id");
	
	private final String columnName;
	
	private CategoryUnit(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return this.columnName;
	}
}
