package com.studyveloper.overtheflow.mapper;

import java.util.List;
import java.util.Map;

import com.studyveloper.overtheflow.vo.MemberLikesPlaylistVO;

public interface MemberLikesPlaylistMapper {
	public void likesPlaylist(MemberLikesPlaylistVO memberLikesPlaylistVO) throws Exception;
	
	public void noLikesPlaylist(MemberLikesPlaylistVO memberLikesPlaylistVO) throws Exception;
	
	public List<String> searchPlaylistsByMemberId(Map<String, Object> conditions) throws Exception;
	
	public List<String> searchMembersByPlaylistId(Map<String, Object> conditions) throws Exception;
}
