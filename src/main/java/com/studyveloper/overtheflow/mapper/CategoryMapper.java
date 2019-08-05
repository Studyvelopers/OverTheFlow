package com.studyveloper.overtheflow.mapper;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.vo.CategoryVO;
 
public interface CategoryMapper {
	/**
	 * 카테고리 정보를 추가합니다. 
	 * CategoryVO의 모든 필드값이 반드시 의미있는 값이어야 합니다.
	 * @param categoryVO 카테고리 정보를 담고 있습니다.
	 * @return 쿼리가 성공적으로 실행되었을 때 영향을 받은 레코드 수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer addCategory(CategoryVO categoryVO) throws Exception;
	
	/**
	 * 카테고리 정보를 수정합니다.
	 * 카테고리 아이디값을 이용하여 수정할 카테고리를 찾습니다.
	 * 카테고리 아이디값은 변경할 수 없습니다.
	 * @param categoryVO 카테고리 정보를 담고 있습니다.
	 * @return 쿼리가 성공적으로 실행되었을 때 영향을 받은 레코드 수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer modifyCategory(CategoryVO categoryVO) throws Exception;
	
	/**
	 * 삭제할 카테고리 정보를 전달하여 조건에 맞는 모든 카테고리를 삭제합니다.
	 * <br>
	 * deleteOptions
	 * <br>
	 * 삭제 조건
	 * <ul><li>superId</li><li>memberId</li></ul>
	 * <br>
	 * 주의!! 삭제 조건을 주지않는다면 모든 카테고리가 삭제됩니다.
	 * @param optionIntent 삭제할 조건입니다.
	 * @return 성공적으로 삭제한 개수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer deleteCategories(OptionIntent optionIntent) throws Exception;
	
	/**
	 * 카테고리를 삭제합니다.
	 * 삭제할 카테고리의 아이디를 이용해 삭제할 카테고리를 찾습니다.
	 * @param categoryId 삭제할 카테고리 아이디입니다.
	 * @return 성공적으로 삭제되면 1을 반환하고 삭제할 대상이 없다면 0을 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer deleteCategory(String categoryId) throws Exception;
	
	/**
	 * 카테고리 아이디를 이용하여 카테고리를 찾아 해당 카테고리를 반환합니다.
	 * @param categoryId
	 * @return 카테고리를 반환합니다. 일치하는 카테고리가 없다면 null을 반환합니다.
	 * @throws Exception 미정
	 */
	public CategoryVO searchCategory(String categoryId) throws Exception;
	
	/**
	 * 여러가지 검색 조건을 이용하여 카테고리 목록을 반환합니다.
	 * 
	 * 다음은 searchOptions에 설정할 수 있는 값들입니다. 이 이외의 값을 설정할 경우 모두 무시합니다.
	 * <br>
	 * <br>검색 가능한 조건
	 * <ul><li>name</li><li>superId</li><li>memberId</li></ul>
	 * <br>정렬 가능한 조건(orderRule)
	 * <ul><li>category_name</li></ul>
	 * <br>정렬 순서 - isDescending 키에 true 값을 추가하면 내림차순으로 정렬됩니다.
	 * <br>검색 개수 제한
	 * <ul><li>offset - 0번 인덱스를 기준으로 결과를 반환할 레코드의 오프셋 인덱스입니다.</li><li>size - 총 가져올 결과값의 개수입니다.</li></ul>
	 * @param optionIntent 검색할 조건입니다.
	 * @return 설정한 조건에 따라 검색된 결과입니다.
	 * @throws Exception 미정
	 */
	public List<CategoryVO> searchCategories(OptionIntent optionIntent) throws Exception;
	
	/**
	 * 카테고리의 개수를 가져옵니다.
	 * @param optionIntent
	 * @return
	 * @throws Exception
	 */
	public Integer getCategorySize(OptionIntent optionIntent) throws Exception;
}
