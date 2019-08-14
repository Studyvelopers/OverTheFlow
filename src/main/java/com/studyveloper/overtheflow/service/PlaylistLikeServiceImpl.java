package com.studyveloper.overtheflow.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.mapper.MemberLikesPlaylistMapper;
import com.studyveloper.overtheflow.mapper.PlaylistMapper;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.util.option.PlaylistUnit;
import com.studyveloper.overtheflow.vo.LikeVO;
import com.studyveloper.overtheflow.vo.PlaylistVO;

@Service
public class PlaylistLikeServiceImpl implements PlaylistLikeService {
	
	@Autowired
	private MemberLikesPlaylistMapper likeMapper;
	
	@Autowired
	private PlaylistMapper playlistMapper;
	
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


	public List<PlaylistVO> getMyLikedPlaylists(SearchInfo searchInfo, String loginId) {
		if (searchInfo == null) {
			return null;
		}
		
		List<String> myLikedPlaylistIdList = null;
		
		try {
			myLikedPlaylistIdList = this.likeMapper.searchPlaylistIds(loginId);
			if (myLikedPlaylistIdList == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
		
		// 논의를 해야할것같습니다.
		OptionIntent.Builder builder = new OptionIntent.Builder();
		int size = searchInfo.getPerPageCount() != null ? searchInfo.getPerPageCount() : 0;
		int offset = (searchInfo.getCurrentPageNumber() != null ? (searchInfo.getCurrentPageNumber() - 1) * size: 0);
		String orderRule = searchInfo.getSortionOption();
		String[] orderList = orderRule.trim().split("+");
		for (int i = 0; i < orderList.length; i++) {
			builder.appendSortingOption(PlaylistUnit.valueOf(orderList[i]), true);
		}
		builder.appendInSearchOption(PlaylistUnit.ID, myLikedPlaylistIdList.toArray(), true)
			   .setPagingOption(size, offset);
		
		List<PlaylistVO> playlists = null;
		
		try {
			playlists = playlistMapper.searchPlaylists(builder.build());
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
		
		return playlists;
	}
}
