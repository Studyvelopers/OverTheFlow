package com.studyveloper.overtheflow.util.option;

public enum MusicUnit implements OptionUnit {
	ID("music_id"),
	TITLE("music_title"),
	PLAY_TIME("music_play_tiem"),
	REGISTER_DATE("music_register_date"),
	DESCRIPTION("music_description"),
	VISIBILITY("music_visibility_flag"),
	DOWNLOADABLE("music_download_flag"),
	PLAY_COUNT("music_play_count"),
	CATEGORY_ID("category_id"),
	MEMBER_ID("member_id"),
	MEMBER_NICKNAME("member_nickname");
	
	private final String columnName;
	
	private MusicUnit(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return this.columnName;
	}
}
