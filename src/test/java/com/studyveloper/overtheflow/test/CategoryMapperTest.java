package com.studyveloper.overtheflow.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studyveloper.overtheflow.mapper.CategoryMapper;
import com.studyveloper.overtheflow.vo.CategoryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:categorymapper-context.xml"})
public class CategoryMapperTest {
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Before
	public void setUp() throws Exception {
		this.categoryMapper.deleteAllCategory();
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId("0");
		categoryVO.setSuperId("0");
		categoryVO.setMemberId("0");
		categoryVO.setName("root");
		
		this.categoryMapper.addCategory(categoryVO);
		
		categoryVO.setId("1");
		categoryVO.setSuperId("0");
		categoryVO.setMemberId("1");
		categoryVO.setName("멤버1 카테고리1");
		
		this.categoryMapper.addCategory(categoryVO);
		
		categoryVO.setId("2");
		categoryVO.setSuperId("0");
		categoryVO.setMemberId("1");
		categoryVO.setName("멤버1 카테고리2");
		
		this.categoryMapper.addCategory(categoryVO);
		
		categoryVO.setId("3");
		categoryVO.setSuperId("1");
		categoryVO.setMemberId("1");
		categoryVO.setName("멤버1 카테고리1 하위 카테고리1");
		
		this.categoryMapper.addCategory(categoryVO);
		
		categoryVO.setId("4");
		categoryVO.setSuperId("1");
		categoryVO.setMemberId("1");
		categoryVO.setName("멤버1 카테고리1 하위 카테고리2");
		
		this.categoryMapper.addCategory(categoryVO);
		
		categoryVO.setId("5");
		categoryVO.setSuperId("3");
		categoryVO.setMemberId("1");
		categoryVO.setName("멤버1 카테고리1 하위 카테고리1 하위 카테고리1");
		
		this.categoryMapper.addCategory(categoryVO);
	}

	@Test
	public void addCategoryTest() {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId("6");
		categoryVO.setSuperId("0");
		categoryVO.setMemberId("1");
		categoryVO.setName("멤버1 카테고리3");
		
		int result = this.categoryMapper.addCategory(categoryVO);
		
		assertThat(result, is(1));
	}
	
	@Test
	public void modifyCategoryTest() {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId("1");
		categoryVO.setSuperId("0");
		categoryVO.setMemberId("1");
		categoryVO.setName("멤버1 카테고리1 수정");
		
		int result = this.categoryMapper.modifyCategory(categoryVO);
		
		assertThat(result , is(1));
	}
	
	@Test
	public void deleteCategoryTest() {
		String categoryId = "1";
		
		boolean result = this.categoryMapper.deleteCategory(categoryId);
		
		assertThat(result, is(true));
	}
	
	@Test
	public void searchCategoryByCategoryIdTest() {
		String categoryId = "1";
		
		CategoryVO categoryVO = this.categoryMapper.searchCategoryByCategoryId(categoryId);
		
		assertThat(categoryVO.getName(), is("멤버1 카테고리1"));
	}
}
