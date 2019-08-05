package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.vo.TagVO;

public interface PlaylistTagMapper {
	/**
	 * 플레이리스트 태그 정보를 추가합니다.
	 * @param tagVO 플레이리스트 식별키와 태그 이름
	 * @return 추가된 정보의 개수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer addPlaylistTag(TagVO tagVO) throws Exception;
	
	/**
	 * 플레이리스트에 설정된 태그정보를 모두 삭제합니다.
	 * @param playlistId 삭제할 플레이리스트 식별키
	 * @return 삭제된 태그 정보의 개수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer deletePlaylistTag(String playlistId) throws Exception;
	
	/**
	 * 해당 태그명을 가진 플레이리스트 식별키를 가져옵니다.
	 * @param tagName 태그명입니다.
	 * @return 태그명을 가진 식별키 목록을 반환합니다.
	 * @throws Exception 미정
	 */
	public List<String> searchPlaylistIds(String tagName) throws Exception;
	
	/**
	 * 해당 플레이리스트 식별키에 등록된 태그명을 가져옵니다.
	 * @param playlistId 플레이리스트 식별키 입니다.
	 * @return 식별키에 등록된 태그명 목록을 반환합니다.
	 * @throws Exception 미정
	 */
	public List<String> searchTagNames(String playlistId) throws Exception;
}
