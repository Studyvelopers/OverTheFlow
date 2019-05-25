package com.studyveloper.overtheflow.mapper;

import java.util.List;
import java.util.Map;

import com.studyveloper.overtheflow.vo.MusicTagVO;

public interface MusicTagMapper {
	public int addMusicTag(MusicTagVO musicTagVO);
	public boolean modifyMusicTag(Map<String,Object> map);
	public void deleteAllMusicTag();
	public boolean deleteMusicTag(MusicTagVO musicTagVO);
	public boolean deleteMusicTagByMusicId(String musicId);
	public List<MusicTagVO> searchMusicTagByMusicId(String musicId);
}
