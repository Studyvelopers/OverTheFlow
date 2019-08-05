package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.vo.MusicVO;

public interface MusicMapper {
	/**
	 * 음악 정보를 추가합니다.
	 * @param musicVO
	 * @return 추가한 음악 정보의 수를 반환합니다.
	 */
	public Integer addMusic(MusicVO musicVO) throws Exception;
	
	/**
	 * 음악 정보를 수정합니다.
	 * 음악 정보 식별키를 이용하여 해당 식별키의 음악 정보를 수정합니다.
	 * @param musicVO
	 * @return 수정한 음악 정보의 수를 반환합니다.
	 */
	public Integer modifyMusic(MusicVO musicVO) throws Exception;
	
	/**
	 * 음악 정보를 삭제합니다.
	 * 식별키와 같은 음악을 삭제합니다. 
	 * @param musicId 삭제할 음악의 식별키입니다.
	 * @return 삭제된 음악의 수 입니다.
	 */
	public Integer deleteMusic(String musicId) throws Exception;
	
	/**
	 * 조건에 맞는 음악들을 삭제합니다.
	 * @param optionIntent 삭제할 음악의 조건입니다. (SearchOption만 기능합니다.)
	 * @return 삭제된 음악의 수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer deleteMusics(OptionIntent optionIntent) throws Exception;
	
	/**
	 * 음악 식별키를 이용하여 음악 정보를 찾습니다.
	 * @param musicId 찾을 음악의 식별키입니다.
	 * @return 성공적으로 찾았다면 MusicVO를 반환하고, 찾는 음악이 없다면 null을 반환합니다.
	 * @throws Exception 미정
	 */
	public MusicVO searchMusic(String musicId) throws Exception;
	
	/**
	 * 검색 조건을 이용하여 음악 정보를 찾습니다.
	 * OptionIntent에 검색할 음악의 정보, 정렬과 페이징 정보를 설정하여 음악 정보를 가져옵니다.
	 * @param optionIntent 검색할 조건, 정렬 조건, 페이징 정보를 포함합니다.
	 * @return 검색된 음악 목록을 반환합니다. 조건에 맞는 음악이 없다면 크기가 0인 목록이 반환됩니다.
	 * @throws Exception 미정
	 */
	public List<MusicVO> searchMusics(OptionIntent optionIntent) throws Exception;
	
	/**
	 * 음악정보의 개수를 가져옵니다.
	 * @param optionIntent 특정 조건을 이용하여 음악정보의 개수를 가져옵니다. (null일 경우 전체 음악정보)
	 * @return 조건에 맞는 총 음악 정보의 개수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer getMusicSize(OptionIntent optionIntent) throws Exception;
}
