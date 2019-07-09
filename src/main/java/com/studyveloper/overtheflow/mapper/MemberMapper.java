package com.studyveloper.overtheflow.mapper;

import java.util.List;
import java.util.Map;

import com.studyveloper.overtheflow.vo.MemberVO;

public interface MemberMapper {
	public void registerMember(MemberVO memberVO);
	public Integer unregisterMember(MemberVO memberVO);
	public Integer modifyMember(MemberVO memberVO);
	public MemberVO getMember(String memberId);
	public List<MemberVO> getMembersByNickName(String nickName);
	public List<MemberVO> getMembersByEmail(String email);
	
	public void follow(Map<String, String> map);
	public Integer unFollow(Map<String, String> map);
	public List<String> getFollows(String loginId);
	public List<String> getFollowers(String loginId);
}
