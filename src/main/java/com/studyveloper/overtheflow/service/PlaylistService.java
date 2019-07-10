package com.studyveloper.overtheflow.service;

import com.studyveloper.overtheflow.bean.PlaylistBean;

public interface PlaylistService {
	public PlaylistBean createPlaylist(PlaylistBean playlistBean);
	
	public Boolean deletePlaylist(String playlistId);
	
	public PlaylistBean modifyPlaylist(PlaylistBean playlistBean);
	
	public PlaylistBean searchPlaylistById(String playlistId);
}
