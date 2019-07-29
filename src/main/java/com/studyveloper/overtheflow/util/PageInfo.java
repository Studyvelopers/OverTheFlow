package com.studyveloper.overtheflow.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class PageInfo {
	private Integer currentPageNumber;
	private Integer perPageCount;
	private Integer orderRule;
	private Integer sort;
	private Integer maxCount;
	
	public PageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageInfo(Integer currentPageNumber, Integer perPageCount, Integer orderRule, Integer sort,
			Integer maxCount) {
		super();
		this.currentPageNumber = currentPageNumber;
		this.perPageCount = perPageCount;
		this.orderRule = orderRule;
		this.sort = sort;
		this.maxCount = maxCount;
	}
	
	public abstract String makeUrl(String path, Integer pageNumber);
	
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
	public Integer getOrderRule() {
		return orderRule;
	}
	public void setOrderRule(Integer orderRule) {
		this.orderRule = orderRule;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}
}
