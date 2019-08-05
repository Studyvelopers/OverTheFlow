package com.studyveloper.overtheflow.util.option;

public class PagingOption {
	private int size;
	private int offset;
	
	public PagingOption() {
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getOffset() {
		return this.offset;
	}
}
