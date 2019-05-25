package com.studyveloper.overtheflow.mapper;

import com.studyveloper.overtheflow.vo.CategoryVO;

public interface CategoryMapper {
	public int addCategory(CategoryVO categoryVO);
	public int modifyCategory(CategoryVO categoryVO);
	public void deleteAllCategory();
	public boolean deleteCategory(String id);
}
