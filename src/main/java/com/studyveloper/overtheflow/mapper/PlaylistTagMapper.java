package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.vo.PlaylistTagVO;

public interface PlaylistTagMapper {
	public void addPlaylistTag(PlaylistTagVO playlistTagVO) throws Exception;
	
	public void deletePlaylistTagsByPlaylistId(String playlistId) throws Exception;
	
	public void deletePlaylistTag(PlaylistTagVO playlistTagVO) throws Exception;
	
	public List<String> searchPlaylistsByTag(String tag) throws Exception;
	
	public List<String> searchTagsByPlaylistId(String playlistId) throws Exception;
}
