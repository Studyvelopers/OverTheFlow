package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.vo.PlaylistVO;

public interface PlaylistMapper {
	public void addPlaylist(PlaylistVO playlistVO) throws Exception;
	
	public PlaylistVO searchPlaylistById(String id) throws Exception;
	
	public List<PlaylistVO> searchPlaylistsByMemberId(String memberId) throws Exception;
	
	public void clean() throws Exception;
}
