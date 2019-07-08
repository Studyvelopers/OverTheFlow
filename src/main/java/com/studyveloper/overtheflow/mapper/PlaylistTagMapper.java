package com.studyveloper.overtheflow.mapper;

import com.studyveloper.overtheflow.vo.PlaylistTagVO;

public interface PlaylistTagMapper {
	public void addPlaylistTag(PlaylistTagVO playlistTagVO) throws Exception;
	
	public void deletePlaylistTagByPlaylistId(String playlistId) throws Exception;
}
