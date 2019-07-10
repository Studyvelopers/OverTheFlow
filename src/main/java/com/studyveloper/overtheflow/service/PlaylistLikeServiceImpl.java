package com.studyveloper.overtheflow.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.mapper.MemberLikesPlaylistMapper;
import com.studyveloper.overtheflow.vo.MemberLikesPlaylistVO;

@Service
public class PlaylistLikeServiceImpl implements PlaylistLikeService {
	
	@Autowired
	private MemberLikesPlaylistMapper likeMapper;
	
	private Logger logger = LoggerFactory.getLogger(PlaylistLikeServiceImpl.class);

	public Boolean likePlaylist(String playlistId, String memberId) {
		// 전달인자 체크
		if (playlistId == null || memberId == null) {
			logger.error("전달인자 오류");
			return null;
		}
		
		try {
			MemberLikesPlaylistVO like = new MemberLikesPlaylistVO();
			like.setMemberId(memberId);
			like.setPlaylistId(playlistId);
			
			likeMapper.likesPlaylist(like);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		
		return true;
	}

}
