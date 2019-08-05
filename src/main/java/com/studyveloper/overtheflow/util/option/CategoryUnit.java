package com.studyveloper.overtheflow.util.option;

public enum CategoryUnit implements OptionUnit {
	ID("playlist_id"),
	TITLE("playlist_title"),
	DESCRIPTION("playlist_description"),
	REGISTER_DATE("playlist_register_date"),
	VISIBILITY("playlist_visibility_flag"),
	MEMBER_ID("member_id"),
	MEMBER_NICKNAME("member_nickname");
	
	private final String columnName;
	
	private CategoryUnit(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return this.columnName;
	}
}
