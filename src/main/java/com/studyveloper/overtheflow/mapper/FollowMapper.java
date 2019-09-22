package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.vo.FollowVO;

public interface FollowMapper {
	/**
	 * 회원끼리의 팔로우 정보를 추가합니다. 
	 * @param followVO 팔로우하는 회원의 아이디와 팔로우 하려는 회원의 아이디 정보입니다.
	 * @return 성공했다면 1을, 실패하면 0을 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer addFollow(FollowVO followVO) throws Exception;
	
	/**
	 * 팔로우 정보를 삭제합니다.
	 * 팔로잉하는 회원아이디와 팔로워의 회원아이디에 맞는 정보를 삭제합니다.
	 * @param followVO 팔로잉 아이디와 팔로워 아이디 정보입니다.
	 * @throws Exception 미정
	 * @return 성공적으로 삭제했다면 1을, 실패했다면 0을 반환합니다.
	 */
	public Integer deleteFollow(FollowVO followVO) throws Exception;
	
	/**
	 * 팔로워 아이디를 기준으로 팔로워가 팔로우하고있는 회원 아이디 목록을 가져옵니다.
	 * @param followerId 팔로워 아이디입니다.
	 * @return 팔로우중인 회원 아이디 목록입니다.
	 * @throws Exception 미정
	 */
	public List<String> searchFollowingIds(String followerId) throws Exception;
	
	/**
	 * 팔로잉 아이디를 기준으로 팔로잉 회원을 팔로우중인 팔로워 아이디 목록을 가져옵니다.
	 * @param followingId 팔로잉 대상의 아이디입니다.
	 * @return 팔로잉 회원을 팔로우중인 회원 아이디 목록을 반환합니다.
	 * @throws Exception 미정
	 */
	public List<String> searchFollowerIds(String followingId) throws Exception;
	
	/**
	 * 팔로잉Id와 팔로워Id를 통해서 팔로우 관계를 확인합니다.
	 * @param followingId 팔로잉 대상의 아이디입니다, followerId 팔로워를 한 대상의 아이디입니다.
	 * @return FollowVO 검색된 팔로우 관계를 반환합니다.
	 * @throws Exception 미정
	 */
	public FollowVO getFollow(FollowVO followVO) throws Exception;
}
