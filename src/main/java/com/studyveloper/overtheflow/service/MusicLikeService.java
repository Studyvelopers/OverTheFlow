package com.studyveloper.overtheflow.service;

import java.util.List;

public interface MusicLikeService {
	public Boolean likeMusic(String memberId, String musicId) throws Exception;
	public Boolean cancelLikeMuisc(String memberId, String musicId) throws Exception;
	public List<String> getLikeMusics(String memberId) throws Exception;
	public Boolean isLike(String memberId, String musicId) throws Exception;
}
