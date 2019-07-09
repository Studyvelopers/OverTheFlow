package com.studyveloper.overtheflow.mapper;

import java.util.List;
import java.util.Map;

import com.studyveloper.overtheflow.vo.MusicTagVO;

public interface MusicTagMapper {
	public Integer addMusicTag(MusicTagVO musicTagVO);
	public Boolean modifyMusicTag(Map<String,Object> map);
	public void deleteAllMusicTag();
	public Boolean deleteMusicTag(MusicTagVO musicTagVO);
	public Boolean deleteMusicTagByMusicId(String musicId);
	public List<MusicTagVO> searchMusicTagByMusicId(String musicId);
}
