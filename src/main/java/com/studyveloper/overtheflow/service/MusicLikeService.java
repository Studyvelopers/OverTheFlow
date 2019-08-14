package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.vo.LikeVO;
import com.studyveloper.overtheflow.vo.MusicVO;

public interface MusicLikeService {
	public Boolean likeMusic(LikeVO likeVO) throws Exception;
	public Boolean cancelLikeMuisc(LikeVO likeVO) throws Exception;
	public List<MusicVO> getLikeMusics(String memberId) throws Exception;
}
