package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.vo.LikeVO;

public interface MusicLikeService {
	public Boolean likeMusic(LikeVO likeVO) throws Exception;
	public Boolean cancelLikeMuisc(LikeVO likeVO) throws Exception;
	public List<String> getLikeMusics(String memberId) throws Exception;
}
