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
		//URL 협의가 이루어지지 않은 관계로 협의후 다시 작업진행
		/*UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.path("/{path}")
				.queryParam("currentPage", this.getCurrentPageNumber())
				.queryParam("perPageCount", this.getPerPageCount())
				.queryParam("keyword", this.searchPageInfo.getKeyword())
				.queryParam("condition", this.searchPageInfo.getCondition())
				.queryParam("sortOption", this.searchPageInfo.getSortOption())
				.build()
				.expand(this.url)
				.encode();
		
		return uriComponents.toUriString();*/
		return null;
	}

	public Map<String, String> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}
}
