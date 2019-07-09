package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.vo.MemberLikesMusicVO;

public interface MemberLikesMusicMapper {
	public Integer addMemberLikesMusic(MemberLikesMusicVO memberLikesMusicVO);
	public Boolean deleteMemberLikesMusic(MemberLikesMusicVO memberLikesMusicVO);
	public void deleteAllMemberLikesMusic();
	public List<MemberLikesMusicVO> searchMemberLikesMusicByMusicId(String musicId);
	public List<MemberLikesMusicVO> searchMemberLikesMusicByMemberId(String memberId);
}
