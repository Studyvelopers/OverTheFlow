package com.studyveloper.overtheflow.mapper;

import java.util.List;
import java.util.Map;

import com.studyveloper.overtheflow.vo.PlaylistVO;

public interface PlaylistMapper {
	public void addPlaylist(PlaylistVO playlistVO) throws Exception;
	
	public PlaylistVO searchPlaylistById(String id) throws Exception;
	
	public List<PlaylistVO> searchPlaylistsByMemberId(Map<String, Object> conditions) throws Exception;
	
	public void clean() throws Exception;
	
	public void deletePlaylistById(String id) throws Exception;
	
	public void deletePlaylistsByMemberId(String memberId) throws Exception;
	
	public void modifyPlaylist(PlaylistVO playlist) throws Exception;
}
