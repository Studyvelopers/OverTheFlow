package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.LikeVO;
import com.studyveloper.overtheflow.vo.PlaylistVO;

public interface PlaylistLikeService {
	public boolean likePlaylist(LikeVO likeVO);
	
	public boolean cancelLikePlaylist(LikeVO likeVO);
	
	public List<PlaylistVO> getMyLikedPlaylists(SearchInfo searchInfo, String loginId);
}
