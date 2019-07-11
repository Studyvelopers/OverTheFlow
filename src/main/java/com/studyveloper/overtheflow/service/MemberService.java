package com.studyveloper.overtheflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.bean.MemberBean;

@Service
public interface MemberService {
	public MemberBean register(MemberBean memberBean);
	public Boolean unRegister(MemberBean memberBean);
	public MemberBean modifyMember(String targetMemberId, String requestMemberId, String password, MemberBean memberBean);
	public MemberBean getMember(String memberId);
	public List<MemberBean> getMembersByNickName(String nickname);
	public List<MemberBean> getMembersByEmail(String email);
	public Boolean follow(String loginId, String memberId);
	public Boolean unFollow(String loginId, String memberId);
	public List<MemberBean> getFollows(String memberId);
	public List<MemberBean> getFollowers(String memberId);
}
