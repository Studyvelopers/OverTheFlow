package com.studyveloper.overtheflow.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.mapper.FollowMapper;
import com.studyveloper.overtheflow.mapper.MemberMapper;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.util.option.MemberUnit;
import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.util.option.OptionIntent.Builder;
import com.studyveloper.overtheflow.vo.FollowVO;
import com.studyveloper.overtheflow.vo.MemberVO;

@Service
public class FollowServiceImpl implements FollowService {

	
	@Autowired
	private FollowMapper followMapper;
	@Autowired
	private MemberMapper memberMapper;
	
	private Logger logger = LoggerFactory.getLogger(FollowServiceImpl.class);
	@Override
	public List<MemberVO> getFollows(SearchInfo searchInfo) throws Exception {
		List<String> memberIds = followMapper.searchFollowingIds(searchInfo.getKeyword());
		Builder builder = new OptionIntent.Builder();
		OptionIntent optionIntent = builder.build();
		int offSet = (searchInfo.getCurrentPageNumber()-1)*searchInfo.getPerPageCount(); 
		builder.setOffset(offSet);
		builder.setSize(searchInfo.getPerPageCount());
		builder.appendInSearchOption(MemberUnit.ID, memberIds.toArray(), true);
		builder.appendSortingOption(MemberUnit.valueOf(searchInfo.getSortionOption()), searchInfo.getOrdering());
		
		if(memberIds != null || memberIds.size() > 0){
			List<MemberVO> members = memberMapper.searchMembers(optionIntent);
			return members;
		}
		return null;
	}

	@Override
	public List<MemberVO> getFollowers(SearchInfo searchInfo) throws Exception {
		List<String> memberIds = followMapper.searchFollowerIds(searchInfo.getKeyword());
		Builder builder = new OptionIntent.Builder();
		OptionIntent optionIntent = builder.build();
		int offSet = (searchInfo.getCurrentPageNumber()-1)*searchInfo.getPerPageCount(); 
		builder.setOffset(offSet);
		builder.setSize(searchInfo.getPerPageCount());
		builder.appendInSearchOption(MemberUnit.ID, memberIds.toArray(), true);
		builder.appendSortingOption(MemberUnit.valueOf(searchInfo.getSortionOption()), searchInfo.getOrdering());
		
		if(memberIds != null || memberIds.size() > 0){
			List<MemberVO> members = memberMapper.searchMembers(optionIntent);
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
