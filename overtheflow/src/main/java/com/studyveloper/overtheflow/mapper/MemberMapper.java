package com.studyveloper.overtheflow.mapper;

import java.util.List;
import java.util.Map;

import com.studyveloper.overtheflow.vo.MemberVO;

public interface MemberMapper {
	public void registerMember(MemberVO memberVO);
	public int unregisterMember(MemberVO memberVO);
	public int modifyMember(MemberVO memberVO);
	public MemberVO getMember(String memberId);
	public List<MemberVO> getMembersByNickName(String nickName);
	public List<MemberVO> getMembersByEmail(String email);
	
//	follow
	public void follow(Map<String, String> map);
	public int unFollow(Map<String, String> map);
	public List<String> getFollows(String memberId);
	public List<String> getFollowers(String memberId);
}
