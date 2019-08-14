package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.LikeVO;
import com.studyveloper.overtheflow.vo.MusicVO;

public interface MusicLikeService {
	/**
	 * 음악의 좋아요 정보를 등록하는 메소드
	 * 이미 좋아요 정보가 등록되어있는 경우 아무런 동작을 하지 않는다
	 * @param likeVO
	 * @return 좋아요 정보 등록 성공여부
	 * @throws*/
	
	public Boolean likeMusic(LikeVO likeVO) throws Exception;
	
	/**
	 * 음악의 좋아요 정보를 삭제하는 메소드
	 * 존재하지 않는 정보가 삭제요청될 경우 아무런 동작을 하지 않는다
	 * @param likeVO
	 * @return 좋아요 정보 삭제 성공여부
	 * @throws*/
	
	public Boolean cancelLikeMuisc(LikeVO likeVO) throws Exception;
	
	
	/**
	 * 음악의 좋아요 정보에 등록된 식별키를 바탕으로 식별키에 해당하는 음악정보들을 조회한다 
	 * @param searchInfo
	 * @return 해당 회원이 좋아요한 음악 정보 목록
	 * @throws*/
	
	public List<MusicVO> getLikeMusics(SearchInfo searchInfo) throws Exception;
}
