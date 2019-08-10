package com.studyveloper.overtheflow.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.mapper.MemberLikesPlaylistMapper;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.LikeVO;
import com.studyveloper.overtheflow.vo.MemberLikesPlaylistVO;
import com.studyveloper.overtheflow.vo.PlaylistVO;

@Service
public class PlaylistLikeServiceImpl implements PlaylistLikeService {
	
	@Autowired
	private MemberLikesPlaylistMapper likeMapper;
	
	private Logger logger = LoggerFactory.getLogger(PlaylistLikeServiceImpl.class);

	public boolean likePlaylist(LikeVO likeVO) {
		try {
			int result = likeMapper.addMemberLikesPlaylist(likeVO);
			if (result > 0) {
				return true;
			}
		} catch (Exception e) {
			logger.error(e.toString());			
		}
		return false;
	}
	
	public boolean cancelLikePlaylist(LikeVO likeVO) {
		try {
			int result = likeMapper.deleteMemberLikesPlaylist(likeVO);
			if (result > 0) {
				return true;
			}
		} catch (Exception e) {
			logger.error(e.toString());			
		}
		return false;
	}


	public List<PlaylistVO> getMyLikedPlaylists(SearchInfo searchInfo) {
		return null;
	}
}
