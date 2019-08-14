package com.studyveloper.overtheflow.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.mapper.FollowMapper;
import com.studyveloper.overtheflow.vo.FollowVO;
import com.studyveloper.overtheflow.vo.MemberVO;

@Service
public class FollowServiceImpl implements FollowService {

	
	@Autowired
	private FollowMapper followMapper;
	@Autowired
	private MemberService memberService;
	
	private Logger logger = LoggerFactory.getLogger(FollowServiceImpl.class);
	@Override
	public List<MemberVO> getFollows(String memberId) throws Exception {
		List<String> memberIds = followMapper.searchFollowingIds(memberId);
		if(memberIds != null || memberIds.size() > 0){
			List<MemberVO> members = memberService.getMembers(memberIds);
			return members;
		}
		return null;
	}

	@Override
	public List<MemberVO> getFollowers(String memberId) throws Exception {
		List<String> memberIds = followMapper.searchFollowerIds(memberId);
		if(memberIds != null || memberIds.size() > 0){
			List<MemberVO> members = memberService.getMembers(memberIds);
			return members;
		}
		return null;
	}

	@Override
	public boolean follow(FollowVO followVO) throws Exception {
		Integer result = followMapper.addFollow(followVO);
		if(result == null){
			return false;
		}else{
			System.out.println(result);
			return true;
		}
	}

	@Override
	public boolean unFollow(FollowVO followVO) throws Exception {
		Integer result = followMapper.deleteFollow(followVO);
		if(result == null){
			return false;
		}else{
			System.out.println(result);
			return true;
		}
	}

}
