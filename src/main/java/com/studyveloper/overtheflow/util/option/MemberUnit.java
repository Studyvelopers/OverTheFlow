package com.studyveloper.overtheflow.util.option;

public enum MemberUnit implements OptionUnit {
	ID("member_id"),
	EMAIL("member_email"),
	PASSWORD("member_password"),
	NICKNAME("member_nickname"),
	INTRODUCTION("member_introduction"),
	REGISTER_DATE("member_register_date"),
	FOLLOWING_COUNT("member_following_count"),
	FOLLOWER_COUNT("member_follower_count"),
	TYPE_ID("member_type_id");
	
	private final String columnName;
	
	private MemberUnit(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return this.columnName;
	}
}
