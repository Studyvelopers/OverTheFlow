package com.studyveloper.overtheflow.util.option;

import java.util.ArrayList;
import java.util.List;

import com.studyveloper.overtheflow.util.option.SearchOption.Conjunction;

public final class OptionIntent {
	private List<SearchOption> searchOptions = 
			new ArrayList<SearchOption>();
	private final List<SortingOption> sortingOptions = 
			new ArrayList<SortingOption>();
	private final PagingOption pagingOption = new PagingOption();
	
	private OptionIntent() {
	}
	
	protected void setSize(int size) {
		this.pagingOption.setSize(size);
	}
	protected void setOffset(int offset) {
		this.pagingOption.setOffset(offset);
	}
	protected void setPagingOption(int size, int offset) {
		this.pagingOption.setSize(size);
		this.pagingOption.setOffset(offset);
	}
	protected void addSearchOption(SearchOption searchOption) {
		this.searchOptions.add(searchOption);
	}
	protected void addSortingOption(SortingOption sortingOption) {
		this.sortingOptions.add(sortingOption);
	}
	public PagingOption getPagingOption() {
		return this.pagingOption;
	}
	
	public static class Builder {
		private final OptionIntent optionIntent;
		
		public Builder() {
			this.optionIntent = new OptionIntent();
		}
		
		public Builder setSize(int size){
			this.optionIntent.setSize(size);
			return this;
		};
		
		public Builder setOffset(int offset){
			this.optionIntent.setOffset(offset);
			return this;
		};
		
		public Builder setPagingOption(int size, int offset) {
			this.optionIntent.setPagingOption(size, offset);
			return this;
		}
		
		public Builder appendInSearchOption(OptionUnit optionUnit, Object[] keywords, boolean isAnd) {
			SearchOption option = new SearchOption();
			option.setColumnName(optionUnit.getColumnName());
			option.setKeywords(keywords);
			option.setCompareType(SearchOption.CompareType.IN);
			if (isAnd) {
				option.setConjunction(Conjunction.AND);
			} else {
				option.setConjunction(Conjunction.OR);
			}
			this.optionIntent.addSearchOption(option);
			return this;
		}
		
		public Builder appendLikeSearchOption(OptionUnit optionUnit, Object keyword, boolean isAnd) {
			SearchOption option = new SearchOption();
			option.setColumnName(optionUnit.getColumnName());
			option.setKeyword(keyword);
			option.setCompareType(SearchOption.CompareType.LIKE);
			if (isAnd) {
				option.setConjunction(Conjunction.AND);
			} else {
				option.setConjunction(Conjunction.OR);
			}
			this.optionIntent.addSearchOption(option);
			return this;
		}
		
		public Builder appendEqualSearchOption(OptionUnit optionUnit, Object keyword, boolean isAnd) {
			SearchOption option = new SearchOption();
			option.setColumnName(optionUnit.getColumnName());
			option.setKeyword(keyword);
			option.setCompareType(SearchOption.CompareType.EQUAL);
			if (isAnd) {
				option.setConjunction(Conjunction.AND);
			} else {
				option.setConjunction(Conjunction.OR);
			}
			this.optionIntent.addSearchOption(option);
			return this;
		}
		
		public Builder appendSortingOption(OptionUnit optionUnit, boolean isDescending) {
			SortingOption option = new SortingOption(optionUnit, isDescending);
			this.optionIntent.addSortingOption(option);
			return this;
		}
		
		public OptionIntent build() {
			return this.optionIntent;
		}
	}
}
