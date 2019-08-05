package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.vo.TagVO;

public interface MusicTagMapper {
	/**
	 * 음악 태그 정보를 추가합니다.
	 * @param tagVO 음악 식별키와 태그 이름
	 * @return 추가된 정보의 개수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer addMusicTag(TagVO tagVO) throws Exception;
	
	/**
	 * 음악에 설정된 태그정보를 모두 삭제합니다.
	 * @param musicId 삭제할 음악 식별키
	 * @return 삭제된 태그 정보의 개수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer deleteMusicTag(String musicId) throws Exception;
	
	/**
	 * 해당 태그명을 가진 음악 식별키를 가져옵니다.
	 * @param tagName 태그명입니다.
	 * @return 태그명을 가진 식별키 목록을 반환합니다.
	 * @throws Exception 미정
	 */
	public List<String> searchMusicIds(String tagName) throws Exception;
	
	/**
	 * 해당 음악 식별키에 등록된 태그명을 가져옵니다.
	 * @param musicId 음악 식별키 입니다.
	 * @return 식별키에 등록된 태그명 목록을 반환합니다.
	 * @throws Exception 미정
	 */
	public List<String> searchTagNames(String musicId) throws Exception;
}
