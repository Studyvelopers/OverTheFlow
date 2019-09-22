package com.studyveloper.overtheflow.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.exception.MemberException;
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
		if(searchInfo == null){
			throw new MemberException("searchInfo가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}else if(searchInfo.getKeyword() == null){
			throw new MemberException("keyword가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}
		
		MemberVO memberId = memberMapper.searchMember(searchInfo.getKeyword());
		if(memberId == null){
			throw new MemberException("ID와 일치하는 회원이 없습니다.", MemberException.ErrorCode.NOT_EXISTS);
		}
		
		List<String> memberIds = followMapper.searchFollowingIds(searchInfo.getKeyword());
		if(memberIds == null){
			return new ArrayList<MemberVO>();
		}
		Builder builder = new OptionIntent.Builder();
		OptionIntent optionIntent = builder.build();
		int offSet = (searchInfo.getCurrentPageNumber()-1)*searchInfo.getPerPageCount(); 
		builder.setOffset(offSet);
		builder.setSize(searchInfo.getPerPageCount());
		builder.appendInSearchOption(MemberUnit.ID, memberIds.toArray(), true);
		builder.appendSortingOption(MemberUnit.valueOf(searchInfo.getSortionOption()), searchInfo.getOrdering());
		
		List<MemberVO> members = memberMapper.searchMembers(optionIntent);
		return members;
	}

	@Override
	public List<MemberVO> getFollowers(SearchInfo searchInfo) throws Exception {
		if(searchInfo == null){
			throw new MemberException("searchInfo가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}else if(searchInfo.getKeyword() == null){
			throw new MemberException("keyword가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}
		
		MemberVO memberId = memberMapper.searchMember(searchInfo.getKeyword());
		if(memberId == null){
			throw new MemberException("팔로워 ID와 일치하는 회원이 없습니다.", MemberException.ErrorCode.NOT_EXISTS);
		}
		
		List<String> memberIds = followMapper.searchFollowerIds(searchInfo.getKeyword());
		if(memberIds == null){
			return new ArrayList<MemberVO>();
		}
		
		Builder builder = new OptionIntent.Builder();
		OptionIntent optionIntent = builder.build();
		int offSet = (searchInfo.getCurrentPageNumber()-1)*searchInfo.getPerPageCount(); 
		builder.setOffset(offSet);
		builder.setSize(searchInfo.getPerPageCount());
		builder.appendInSearchOption(MemberUnit.ID, memberIds.toArray(), true);
		builder.appendSortingOption(MemberUnit.valueOf(searchInfo.getSortionOption()), searchInfo.getOrdering());
		
		List<MemberVO> members = memberMapper.searchMembers(optionIntent);
		return members;
	}

	@Override
	public boolean follow(FollowVO followVO) throws Exception {
		if(followVO == null){
			throw new MemberException("followVO가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}else if(followVO.getFollowerId() == null){
			throw new MemberException("followerId가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}else if(followVO.getFollowingId() == null){
			throw new MemberException("followingId가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}
		MemberVO memberId = memberMapper.searchMember(followVO.getFollowerId());
		if(memberId == null){
			throw new MemberException("팔로워 ID와 일치하는 회원이 없습니다.", MemberException.ErrorCode.NOT_EXISTS);
		}
		
		memberId = memberMapper.searchMember(followVO.getFollowingId());
		if(memberId == null){
			throw new MemberException("팔로잉 ID와 일치하는 회원이 없습니다.", MemberException.ErrorCode.NOT_EXISTS);
		}
		
		followVO = followMapper.getFollow(followVO);
		if(followVO != null){
			throw new MemberException("이미 팔로우중인 회원입니다.", MemberException.ErrorCode.EXISTS);
		}else{
			Integer result = followMapper.addFollow(followVO);
			if(result == 0){
				logger.info("팔로우 실패.");
				return false;
			}else{
				logger.info("팔로우 성공.");
				return true;
			}
		}
	}

	@Override
	public boolean unFollow(FollowVO followVO) throws Exception {
		if(followVO == null){
			throw new MemberException("followVO가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}else if(followVO.getFollowerId() == null){
			throw new MemberException("followerId가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}else if(followVO.getFollowingId() == null){
			throw new MemberException("followingId가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}
		
		MemberVO memberId = memberMapper.searchMember(followVO.getFollowerId());
		if(memberId == null){
			throw new MemberException("팔로워 ID와 일치하는 회원이 없습니다.", MemberException.ErrorCode.NOT_EXISTS);
		}
		memberId = memberMapper.searchMember(followVO.getFollowingId());
		if(memberId == null){
			throw new MemberException("팔로잉 ID와 일치하는 회원이 없습니다.", MemberException.ErrorCode.NOT_EXISTS);
		}
		
		followVO = followMapper.getFollow(followVO);
		if(followVO == null){
			throw new MemberException("팔로우하지 않은 회원입니다.", MemberException.ErrorCode.EXISTS);
		}else{
			Integer result = followMapper.deleteFollow(followVO);
			if(result == 0){
				logger.info("팔로우 취소 실패.");
				return false;
			}else{
				logger.info("팔로우 취소 성공.");
				return true;
			}
		}
	}
}
