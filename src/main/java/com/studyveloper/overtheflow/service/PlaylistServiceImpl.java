package com.studyveloper.overtheflow.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.bean.PlaylistBean;
import com.studyveloper.overtheflow.mapper.PlaylistMapper;
import com.studyveloper.overtheflow.mapper.PlaylistTagMapper;
import com.studyveloper.overtheflow.util.IdentifierGenerator;
import com.studyveloper.overtheflow.vo.PlaylistTagVO;
import com.studyveloper.overtheflow.vo.PlaylistVO;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	// logger
	private static final Logger logger = LoggerFactory.getLogger(PlaylistServiceImpl.class);
		
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Autowired
	private PlaylistTagMapper playlistTagMapper;
	
	public PlaylistBean createPlaylist(PlaylistBean playlistBean) {
		// 파라미터 널 체크
		if (playlistBean == null) {
			logger.error("전달인자 오류.");
			return null;
		}
		
		// 데이터 검증
		if (playlistBean.getTitle() == null ||
				playlistBean.getVisibility() == null ||
				playlistBean.getDescription() == null ||
				playlistBean.getMemberId() == null) {
			
			logger.error("데이터가 비어 있습니다.");
			return null;
		}
		
		// bean -> vo 전환
		PlaylistVO playlistVO = new PlaylistVO();
		playlistVO.setTitle(playlistBean.getTitle());
		playlistVO.setDescription(playlistBean.getDescription());
		playlistVO.setMemberId(playlistBean.getMemberId());
		playlistVO.setRegisterDate(new Date());
		playlistVO.setVisibility(playlistBean.getVisibility());
		
		// 식별키 생성
		String id = IdentifierGenerator.generateId(playlistBean.getTitle().hashCode() + playlistBean.getMemberId().hashCode());
		playlistVO.setId(id);
		
		// 플레이리스트 정보 등록
		try {
			playlistMapper.addPlaylist(playlistVO);
		} catch (Exception e) {
			// 예외 처리
			logger.error(e.getMessage());
		}
		
		// 태그 정보 등록
		List<PlaylistTagVO> tags = playlistBean.getTags();
		try {
			if (tags != null && tags.size() > 0) {
				for (PlaylistTagVO tag : tags) {
					tag.setPlaylistId(id);
					playlistTagMapper.addPlaylistTag(tag);
				}
			}
		}  catch (Exception e) {
			// 예외 처리
			logger.error(e.getMessage());
		}
		
		// 식별키와 날짜 정보 삽입
		playlistBean.setId(id);
		playlistBean.setRegisterDate(playlistVO.getRegisterDate());
		
		// 로깅
		logger.info(playlistBean.toString());
		
		return playlistBean;
	}

	public Boolean deletePlaylist(String playlistId) {
		// 전달인자 null 체크
		if (playlistId == null) {
			logger.error("삭제할 대상이 없습니다.");
			return false;
		}
				
		logger.info("플레이리스트 삭제 요청 (" + playlistId + ")");
		
		try {
			// 태그 정보 제거
			playlistTagMapper.deletePlaylistTagsByPlaylistId(playlistId);
			
			// 플레이리스트 정보 제거
			playlistMapper.deletePlaylistById(playlistId);
			
		} catch (Exception e) {
			// 예외 처리
			logger.error(e.getMessage());
			return false;
		}
		
		return true;
	}

	public PlaylistBean modifyPlaylist(PlaylistBean playlistBean) {
		// 전달인자 null 체크
		if (playlistBean == null) {
			logger.error("수정할 대상이 없습니다.");
			return null;
		}
		
		logger.info("플레이리스트 수정 (" + playlistBean.getId() + ")");
		
		// 데이터 체크
		if (playlistBean.getVisibility() == null ||
				playlistBean.getDescription() == null ||
				playlistBean.getId() == null ||
				playlistBean.getMemberId() == null ||
				playlistBean.getRegisterDate() == null ||
				playlistBean.getTitle() == null) {
			logger.error("누락된 정보가 있습니다.");
		}
		
		// VO 객체 생성
		PlaylistVO playlistVO = new PlaylistVO();
		playlistVO.setId(playlistBean.getId());
		playlistVO.setDescription(playlistBean.getDescription());
		playlistVO.setMemberId(playlistBean.getMemberId());
		playlistVO.setRegisterDate(playlistBean.getRegisterDate());
		playlistVO.setTitle(playlistBean.getTitle());
		playlistVO.setVisibility(playlistBean.getVisibility());
		
		try {
			// 플레이리스트 정보 수정
			playlistMapper.modifyPlaylist(playlistVO);
			
			// 태그 정보 수정
			playlistTagMapper.deletePlaylistTagsByPlaylistId(playlistVO.getId());
			
			// 태그 정보 등록
			List<PlaylistTagVO> tags = playlistBean.getTags();
			if (tags != null && tags.size() > 0) {
				for (PlaylistTagVO tag : tags) {
					playlistTagMapper.addPlaylistTag(tag);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
		return playlistBean;
	}
}
