package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.vo.PlaylistVO;

public interface PlaylistMapper {
	/**
	 * 플레이리스트 정보를 추가합니다.
	 * @param playlistVO
	 * @throws Exception
	 */
	public Integer addPlaylist(PlaylistVO playlistVO) throws Exception;
	
	/**
	 * 식별키로 플레이리스트 정보를 가져옵니다.
	 * @param playlistId 플레이리스트 식별키입니다.
	 * @return
	 * @throws Exception
	 */
	public PlaylistVO searchPlaylist(String playlistId) throws Exception;
	
	/**
	 * 조건을 이용하여 플레이리스트 목록을 가져옵니다.
	 * @param optionIntent null을 줄 경우 전체 목록을 모두 가져옵니다.
	 * @return 조건에 맞는 플레이리스트 목록을 반환합니다.
	 * @throws Exception
	 */
	public List<PlaylistVO> searchPlaylists(OptionIntent optionIntent) throws Exception;
	
	/**
	 * 플레이리스트를 삭제합니다.
	 * @param playlistId 삭제할 플레이리스트 식별키입니다.
	 * @return 삭제에 성공한 플레이리스트 개수를 반환합니다.
	 * @throws Exception
	 */
	public Integer deletePlaylist(String playlistId) throws Exception;
	
	/**
	 * 삭제할 조건을 설정하여 해당 조건에 맞는 플레이리스트를 삭제합니다.
	 * @param optionIntent 삭제할 조건 정보입니다.(searchOption만 사용)
	 * @return 삭제한 플레이리스트 개수를 반환합니다.
	 * @throws Exception
	 */
	public Integer deletePlaylists(OptionIntent optionIntent) throws Exception;
	
	/**
	 * 플레이리스트 정보를 변경합니다.
	 * @param playlist 변경할 플레이리스트 정보
	 * @return 성공적으로 수정한 플레이리스트의 개수를 반환합니다.
	 * @throws Exception
	 */
	public Integer modifyPlaylist(PlaylistVO playlist) throws Exception;
	
	/**
	 * 플레이리스트의 총 개수를 가져옵니다.
	 * 특정 조건을 설정하여 해당 조건과 일치하는 정보의 개수를 구할 수 있습니다.
	 * @param optionIntent 검색할 조건입니다.
	 * @return 조건에 맞는 플레이리스트 정보의 개수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer getPlaylistSize(OptionIntent optionIntent) throws Exception;
}
