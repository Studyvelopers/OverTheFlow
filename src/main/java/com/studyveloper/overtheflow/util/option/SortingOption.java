package com.studyveloper.overtheflow.util.option;

public class SortingOption {
	private String columnName;
	private boolean isDescending;
	
	public SortingOption(OptionUnit optionUnit) {
		this.columnName = optionUnit.getColumnName();
	}
	
	public SortingOption(OptionUnit optionUnit, boolean isDescending) {
		this(optionUnit);
		this.isDescending = isDescending;
	}
	
	public String getColumnName() {
		return this.columnName;
	}
	
	public boolean getOrdering() {
		return this.isDescending;
	}
}
