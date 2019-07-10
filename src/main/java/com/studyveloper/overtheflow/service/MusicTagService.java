package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.util.SearchUnit;

public interface MusicTagService {
	public List<String> createTag(String musicNo, List<String> tagNames)throws Exception;
	public List<String> modifyTag(String musicNo, List<String> tagNames)throws Exception;
	public List<Integer> searchTag(SearchUnit searchUnit)throws Exception;
	public List<String> getTags(String musicNo)throws Exception;
	public boolean deleteTags(String musicNo)throws Exception;
}
