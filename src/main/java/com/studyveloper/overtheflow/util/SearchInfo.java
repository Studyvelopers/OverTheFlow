package com.studyveloper.overtheflow.util;

import java.util.Map;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class SearchInfo extends PageInfo{
	private String keyword;
	private String searchOption;
	private String conjunction;
	
	public SearchInfo(){
		
	}
	
	public SearchInfo(String keyword, String searchOption, String conjunction) {
		super();
		this.keyword = keyword;
		this.searchOption = searchOption;
		this.conjunction = conjunction;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	public String getConjunction() {
		return conjunction;
	}
	public void setConjunction(String conjunction) {
		this.conjunction = conjunction;
	}
}
