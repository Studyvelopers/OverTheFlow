package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.util.PageInfo;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.PlaylistVO;

public interface PlaylistService {
	public PlaylistVO createPlaylist(PlaylistVO playlistVO);

	public PlaylistVO modifyPlaylist(PlaylistVO playlistVO);

	public boolean deletePlaylist(String playlistId, String loginId);
	
	public PlaylistVO getPlaylist(String playlistId, String loginId);

	public List<PlaylistVO> getAllPlaylists(PageInfo pageInfo);

	public List<PlaylistVO> getPlaylists(List<String> playlistIds);

	public List<PlaylistVO> getPlaylistsByTitle(SearchInfo searchInfo);

	public List<PlaylistVO> getPlaylistByNickname(SearchInfo searchInfo);

	public List<PlaylistVO> getPlaylistsByTag(SearchInfo searchInfo);

	public List<PlaylistVO> getPlaylistsByMember(SearchInfo searchInfo);

	public List<PlaylistVO> getMyPlaylists(SearchInfo searchInfo);

	public List<PlaylistVO> getMyPlaylistsByTitle(SearchInfo searchInfo);
}
