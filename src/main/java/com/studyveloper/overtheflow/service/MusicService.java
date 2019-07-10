package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.bean.MusicBean;
import com.studyveloper.overtheflow.util.SearchUnit;

public interface MusicService {
	public MusicBean createMusic(MusicBean musicBean) throws Exception;
	public MusicBean modifyMusic(MusicBean musicBean) throws Exception;
	public Boolean deleteMusic(String musicId) throws Exception;
	public MusicBean getMusicInfo(String musicId) throws Exception;
	public List<MusicBean> searchMusic(SearchUnit searchUnit) throws Exception;
	public List<MusicBean> searchMusics(SearchUnit searchUnit) throws Exception;
	public List<MusicBean> searchMusics(List<Integer> musicIds) throws Exception;
}
