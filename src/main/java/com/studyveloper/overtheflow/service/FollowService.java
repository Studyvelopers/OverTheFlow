package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.vo.FollowVO;
import com.studyveloper.overtheflow.vo.MemberVO;

public interface FollowService {
	public List<MemberVO> getFollows(String memberId)throws Exception;
	public List<MemberVO> getFollowers(String memberId)throws Exception;
	public boolean follow(FollowVO followVO)throws Exception;
	public boolean unFollow(FollowVO followVO)throws Exception;
}
