package com.studyveloper.overtheflow.service;

import java.util.List;

import com.studyveloper.overtheflow.util.PageInfo;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.MusicVO;

public interface MusicService {
	/**
	 * parameter로 전달받은 musicVO를 이용해 DB에 해당 musicVO정보 저장을 위임한다
	 * 저장에 실패할시에 Exception을 던진다
	 * @param musicVO
	 * @return 저장한 음악 정보
	 * @throws
	 */
	public MusicVO createMusic(MusicVO musicVO) throws Exception;
	
	/**
	 * parameter로 전달받은 musicVO의 id와 loginId가 일치하는 DB 음악테이블의 데이터 수정을 위임한다
	 * loginId와 해당 musicVO의 memberId가 일치할 경우 성공적으로 정보 수정을 위임한다
	 * loginId와 musicVO의 memberId가 일치하지 않을 경우 Exception을 던진다 
	 * @param loginId, musicVO
	 * @return 수정한 음악 정보
	 * @throws
	 */
	public MusicVO modifyMusic(String loginId, MusicVO musicVO) throws Exception;
	
	/**
	 * parameter로 전달받은 musicVO를 이용해 DB에 해당 musicVO정보 삭제를 위임한다
	 * musicId에 해당하는 음악정보의 memberId가 loginId와 일치할 경우 삭제에 성공한다 
	 * 두 조건이 일치하지 않을 경우 Exception을 던진다
	 * @param musicId, loginId
	 * @return 삭제한 음악 정보
	 * @throws 
	 */
	public boolean deleteMusic(String musicId, String loginId) throws Exception;
	
	/**
	 * musicId와 일치하는 DB의 음악 정보 조회를 위임한다
	 * 비공개 곡일 경우 memberId가 loginId와 일치하는 경우에만 조회가 가능하다 
	 * @param musicId, loginId
	 * @return 조회한 음악 정보
	 * @throws
	 */
	public MusicVO getMusic(String musicId, String loginId) throws Exception;
	
	/**
	 * */
	
	public List<MusicVO> getMusicsByTitle(SearchInfo searchInfo) throws Exception;
	public List<MusicVO> getMusicsByNickName(SearchInfo searchInfo) throws Exception;
	public List<MusicVO> getMusicsByTag(SearchInfo searchInfo) throws Exception;
	public List<MusicVO> getMyMusicsByTitle(SearchInfo searchInfo, String loginId) throws Exception;
	public List<MusicVO> getMyMusicsByCategory(SearchInfo searchInfo, String loginId) throws Exception;
	public List<MusicVO> getMusics(List<String> musicIds) throws Exception;
	public List<MusicVO> getAllMusics(PageInfo pageInfo) throws Exception;
}
