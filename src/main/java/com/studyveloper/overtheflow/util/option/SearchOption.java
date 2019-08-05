package com.studyveloper.overtheflow.util.option;

import java.util.Arrays;

public class SearchOption {
	private String columnName;
	private Object keyword;
	private Object[] keywords;
	private CompareType compareType;
	private Conjunction conjunction;
	
	public SearchOption() {
		this.compareType = CompareType.EQUAL;
		this.conjunction = Conjunction.AND;
	}
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnName() {
		return this.columnName;
	}
	
	public void setKeyword(Object keyword) {
		this.keyword = keyword;
	}
	public Object getKeyword() {
		return this.keyword;
	}
	
	public void setKeywords(Object[] keywords) {
		this.keywords = Arrays.copyOf(keywords, keywords.length);
	}
	public Object[] getkeywords() {
		return this.keywords;
	}
	
	public void setCompareType(CompareType compareType) {
		this.compareType = compareType;
	}
	public int getCompareType() {
		return this.compareType.getType();
	}
	
	public void setConjunction(Conjunction conjunction) {
		this.conjunction = conjunction;
	}
	public String getConjunction() {
		return this.conjunction.getSql();
	}
	
	public enum CompareType {
		IN(-1),
		EQUAL(0),
		LIKE(1);
		private int type;
		private CompareType(int type) {
			this.type = type;
		}
		
		public int getType() {
			return this.type;
		}
	}
	
	public enum Conjunction {
		AND("AND"),
		OR("OR");
		
		private final String sql;
		private Conjunction(String sql) {
			this.sql = sql;
		}
		
		public String getSql() {
			return this.sql;
		}
	}
}
