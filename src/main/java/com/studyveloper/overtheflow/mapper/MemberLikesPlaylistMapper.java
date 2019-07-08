package com.studyveloper.overtheflow.mapper;

import com.studyveloper.overtheflow.vo.MemberLikesPlaylistVO;

public interface MemberLikesPlaylistMapper {
	public void likesPlaylist(MemberLikesPlaylistVO memberLikesPlaylistVO) throws Exception;
	
	public void noLikesPlaylist(MemberLikesPlaylistVO memberLikesPlaylistVO) throws Exception;
}
