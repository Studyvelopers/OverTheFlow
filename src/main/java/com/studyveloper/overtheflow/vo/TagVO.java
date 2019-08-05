package com.studyveloper.overtheflow.vo;

public class TagVO {
	private String id;
	private String tagName;
	
	public TagVO() {
		
	}
	
	public TagVO(String id, String tagName) {
		this.id = id;
		this.tagName = tagName;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagName() {
		return this.tagName;
	}
}