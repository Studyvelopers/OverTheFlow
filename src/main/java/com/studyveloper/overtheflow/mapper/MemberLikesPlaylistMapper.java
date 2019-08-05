package com.studyveloper.overtheflow.mapper;

import java.util.List;
import java.util.Map;

import com.studyveloper.overtheflow.vo.LikeVO;

public interface MemberLikesPlaylistMapper {
	/**
	 * 회원이 플레이리스트를 좋아요할 때 좋아요 정보를 추가합니다.
	 * @param likeVO 회원 아이디와 좋아요 할 플레이리스트의 아이디정보가 있습니다.
	 * @return 추가에 성공한 경우 1, 실패한 경우 0을 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer addMemberLikesPlaylist(LikeVO likeVO) throws Exception;
	
	/**
	 * 회원이 좋아요한 플레이리스트에 대한 정보를 삭제합니다.
	 * @param likeVO 회원 아이디와 좋아요 할 플레이리스트의 아이디정보가 있습니다.
	 * @return 삭제에 성공한 경우 1, 실패한 경우 0을 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer deleteMemberLikesPlaylist(LikeVO likeVO) throws Exception;
	
	/**
	 * 특정 플레이리스트를 좋아요한 회원 아이디 목록을 가져옵니다.
	 * @param playlistId 검색할 음악 아이디입니다.
	 * @return 전달인자로 준 플레이리스트 아이디에 해당하는 플레이리스트를 좋아요한 회원 아이디 목록을 반환합니다.
	 * @throws Exception 미정
	 */
	public List<String> searchMemberIds(String playlistId) throws Exception;
	
	/**
	 * 특정 회원이 좋아요한 플레이리스트 목록을 가져옵니다.
	 * @param memberId 검색할 회원 아이디
	 * @return 전달인자로 준 회원 아이디에 해당하는 회원이 좋아요한 플레이리스트 아이디 목록을 반환합니다.
	 * @throws Exception 미정
	 */
	public List<String> searchMusicIds(String memberId) throws Exception;
}
