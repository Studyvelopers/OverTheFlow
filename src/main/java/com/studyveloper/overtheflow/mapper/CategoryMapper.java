package com.studyveloper.overtheflow.mapper;

import com.studyveloper.overtheflow.vo.CategoryVO;

public interface CategoryMapper {
	public Integer addCategory(CategoryVO categoryVO);
	public Integer modifyCategory(CategoryVO categoryVO);
	public void deleteAllCategory();
	public Boolean deleteCategory(String id);
	public CategoryVO searchCategoryByCategoryId(String id);
}
