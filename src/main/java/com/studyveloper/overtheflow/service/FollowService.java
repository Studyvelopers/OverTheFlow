package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.FollowVO;
import com.studyveloper.overtheflow.vo.MemberVO;

public interface FollowService {
	/**
	 * @param searchInfo 한 회원이 팔로우한 회원목록을 검색하기 위한 정보를 담고있습니다.
	 * @return List<MemberVO> 검색된 팔로우한 회원목록을 List로 반환합니다.
	 * @exception Exception 미정.
	 */
	public List<MemberVO> getFollows(SearchInfo searchInfo)throws Exception;
	
	/**
	 * @param searchInfo 한 회원을 팔로우한 회원목록을 검색하기 위한 정보를 담고있습니다.
	 * @return List<MemberVO> 검색된 팔로우한 회원목록을 List로 반환합니다.
	 * @exception Exception 미정.
	 */
	public List<MemberVO> getFollowers(SearchInfo searchInfo)throws Exception;
	
	/**
	 * @param FollowVo 팔로우를 요청의 회원 식별키와 팔로우 대상의 회원 식별키를 담고있습니다.
	 * @return boolean 팔로우에 성공한 경우 true 팔로우에 실패한 경우 false를 반환합니다.
	 * @exception Exception 미정.
	 */
	public boolean follow(FollowVO followVO)throws Exception;
	
	/**
	 * @param FollowVo 팔로우 취소를 요청의 회원 식별키와 팔로우 대상의 회원 식별키를 담고있습니다.
	 * @return boolean 팔로우에 성공한 경우 true 팔로우에 실패한 경우 false를 반환합니다.
	 * @exception Exception 미정.
	 */
	public boolean unFollow(FollowVO followVO)throws Exception;
}
