package com.studyveloper.overtheflow.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class PageInfo {
	private Integer currentPageNumber;
	private Integer perPageCount;
	private String sortionOption;
	private Boolean ordering;
	private Integer maxCount;
	
	public PageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PageInfo(Integer currentPageNumber, Integer perPageCount, String sortionOption, Boolean ordering,
			Integer maxCount) {
		super();
		this.currentPageNumber = currentPageNumber;
		this.perPageCount = perPageCount;
		this.sortionOption = sortionOption;
		this.ordering = ordering;
		this.maxCount = maxCount;
	}

	public Integer getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(Integer currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public Integer getPerPageCount() {
		return perPageCount;
	}

	public void setPerPageCount(Integer perPageCount) {
		this.perPageCount = perPageCount;
	}

	public String getSortionOption() {
		return sortionOption;
	}

	public void setSortionOption(String sortionOption) {
		this.sortionOption = sortionOption;
	}

	public Boolean getOrdering() {
		return ordering;
	}

	public void setOrdering(Boolean ordering) {
		this.ordering = ordering;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}
}
