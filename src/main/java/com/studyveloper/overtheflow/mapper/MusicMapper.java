package com.studyveloper.overtheflow.mapper;

import java.util.List;
import java.util.Map;

import com.studyveloper.overtheflow.vo.MusicVO;

public interface MusicMapper {
	public Integer addMusic(MusicVO musicVO);
	public Integer deleteAllMusic();
	public Integer modifyMusic(MusicVO musicVO);
	public Boolean deleteMusic(String musicId);
	public List<MusicVO> getAllMusicList();
	public MusicVO searchMusicByMusicId(String musicId);
	public Integer maxSearchMusicByMusicTitle(String title);
	public List<MusicVO> searchMusicByMusicTitle(Map<String, Object> searchCondition);
	public Integer maxSearchMusicByMemberNickname(String memberNickname);
	public List<MusicVO> searchMusicByMemberNickname(Map<String, Object> searchCondition);
	public Integer maxSearchMusicByMemberId(String memberId);
	public List<MusicVO> searchMusicByMemberId(Map<String, Object> searchCondition);
}
