package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.vo.MusicTagVO;

public interface MusicTagMapper {
	public int addMusicTag(MusicTagVO musicTagVO);
	public void deleteAllMusicTag();
	public boolean deleteMusicTag(MusicTagVO musicTagVO);
	public boolean deleteMusicTagByMusicId(String musicId);
	public List<MusicTagVO> searchMusicTagByMusicId(String musicId);
}
