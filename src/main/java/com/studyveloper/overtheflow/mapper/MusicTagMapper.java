package com.studyveloper.overtheflow.mapper;

import com.studyveloper.overtheflow.vo.MusicTagVO;

public interface MusicTagMapper {
	public int addMusicTag(MusicTagVO musicTagVO);
	public void deleteAllMusicTag();
	public boolean deleteMusicTag(MusicTagVO musicTagVO);
	public boolean deleteMusicTagByMusicId(String musicId);
}
