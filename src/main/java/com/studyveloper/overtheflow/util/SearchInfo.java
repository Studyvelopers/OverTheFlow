package com.studyveloper.overtheflow.util;

import java.util.Map;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class SearchInfo extends PageInfo{
	private Map<String, String> conditions;
	
	public SearchInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchInfo(Integer currentPageNumber, Integer perPageCount, Integer orderRule, Integer sort,
			Integer maxCount, Map<String, String> conditions) {
		super(currentPageNumber, perPageCount, orderRule, sort, maxCount);
		this.conditions = conditions;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String makeUrl(String path, Integer pageNumber) {
		// TODO Auto-generated method stub
		String keyword = this.conditions.get("keyword");
		String condition = this.conditions.get("condition");

		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.path("/{path}")
				.queryParam("currentPage", this.getCurrentPageNumber())
				.queryParam("perPageCount", this.getPerPageCount())
				.queryParam("keyword", keyword)
				.queryParam("condition", condition)
				.queryParam("sort", this.getSort())
				.queryParam("orderRuel", this.getOrderRule())
				.build()
				.expand(path)
				.encode();
		
		return uriComponents.toUriString();
	}

	public Map<String, String> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}
}
