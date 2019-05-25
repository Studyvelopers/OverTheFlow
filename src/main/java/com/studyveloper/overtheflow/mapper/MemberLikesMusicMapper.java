package com.studyveloper.overtheflow.mapper;

import com.studyveloper.overtheflow.vo.MemberLikesMusicVO;

public interface MemberLikesMusicMapper {
	public int addMemberLikesMusic(MemberLikesMusicVO memberLikesMusicVO);
	public boolean deleteMemberLikesMusic(MemberLikesMusicVO memberLikesMusicVO);
	public void deleteAllMemberLikesMusic();
}
