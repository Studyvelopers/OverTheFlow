package com.studyveloper.overtheflow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.studyveloper.overtheflow.vo.TagVO;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	// logger
	private static final Logger logger = LoggerFactory.getLogger(PlaylistServiceImpl.class);
		
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Autowired
	private PlaylistTagMapper playlistTagMapper;
	
	public PlaylistVO createPlaylist(PlaylistVO playlistVO) {
		// 파라미터 널 체크
		if (playlistVO == null) {
			logger.error("전달인자 오류.");
			return null;
		}
		
		// 데이터 검증
		if (playlistVO.getTitle() == null 
			|| playlistVO.getVisibility() == null
			|| playlistVO.getDescription() == null
			|| playlistVO.getMemberId() == null) {
			logger.error("데이터가 비어 있습니다.");
			return null;
		}
	
		// 식별키 생성
		String id = IdentifierGenerator.generateId(playlistVO.getTitle());
		playlistVO.setId(id);
		
		// 날짜 정보 삽입
		playlistVO.setRegisterDate(new Date());
		
		// 플레이리스트 정보 등록
		try {
			playlistMapper.addPlaylist(playlistVO);
		} catch (Exception e) {
			// 예외 처리
			logger.error(e.getMessage());
		}
		
		// 태그 정보 등록
		List<String> tags = playlistVO.getTags();
		try {
			if (tags != null && tags.size() > 0) {
				for (String tag : tags) {
					TagVO tagVO = new TagVO();
					tagVO.setId(id);
					tagVO.setTagName(tag);
					playlistTagMapper.addPlaylistTag(tagVO);
				}
			}
		}  catch (Exception e) {
			// 예외 처리
			logger.error(e.getMessage());
		}
		
		return playlistVO;
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

	public PlaylistVO modifyPlaylist(PlaylistVO playlistVO) {
		// 전달인자 null 체크
		if (playlistVO == null) {
			logger.error("수정할 대상이 없습니다.");
			return null;
		}
		
		logger.info("플레이리스트 수정 (" + playlistVO.getId() + ")");
		
		// 데이터 체크
		if (playlistVO.getVisibility() == null 
			|| playlistVO.getDescription() == null
			|| playlistVO.getId() == null
			|| playlistVO.getMemberId() == null
			|| playlistVO.getRegisterDate() == null
			|| playlistVO.getTitle() == null) {
			logger.error("누락된 정보가 있습니다.");
		}
		
		try {
			// 플레이리스트 정보 수정
			playlistMapper.modifyPlaylist(playlistVO);
			
			// 태그 정보 수정
			// 기존 태그 정보 모두 삭제
			playlistTagMapper.deletePlaylistTag(playlistVO.getId());
			
			// 태그 정보 등록
			List<String> tags = playlistVO.getTags();
			if (tags != null && tags.size() > 0) {
				for (String tag : tags) {
					TagVO newTag = new TagVO();
					newTag.setId(playlistVO.getId());
					newTag.setTagName(tag);
					playlistTagMapper.addPlaylistTag(newTag);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
		return playlistVO;
	}
	
	public PlaylistBean searchPlaylistById(String playlistId) {
		// 전달인자 체크
		if (playlistId == null) {
			logger.error("조회할 식별키가 없습니다.");
			return null;
		}
		
		PlaylistBean result = null;
		try {
			// 플레이리스트 정보 조회
			PlaylistVO playlistVO = playlistMapper.searchPlaylistById(playlistId);
			
			// 정보가 없다면 널 리턴
			if (playlistVO == null) {
				return result;
			}
			
			// 정보가 있으면 태그 정보 조회
			List<String> tags = null;
			tags = playlistTagMapper.searchTagsByPlaylistId(playlistId);
			
			// Bean 객체 생성
			result = new PlaylistBean();
			result.setDescription(playlistVO.getDescription());
			result.setId(playlistVO.getId());
			result.setMemberId(playlistVO.getMemberId());
			result.setRegisterDate(playlistVO.getRegisterDate());
			result.setTags(tags);
			result.setTitle(playlistVO.getTitle());
			result.setVisibility(playlistVO.getVisibility());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return result;
	}

	public List<PlaylistBean> searchPlaylistsByMemberId(String memberId, Integer pageNumber, Integer perPageCount) {
		// 전달인자 체크
		if (memberId == null) {
			logger.error("전달인자 오류");
			return null;
		}
		
		List<PlaylistBean> playlists = new ArrayList<PlaylistBean>();
		
		// 회원아이디로 플레이리스트 검색
		try {
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("memberId", memberId);
			conditions.put("pageNumber", pageNumber);
			conditions.put("perPageCount", perPageCount);
			
			// 정보 조회
			List<PlaylistVO> data = playlistMapper.searchPlaylistsByMemberId(conditions);
			
			// 태그 정보 조회
			for (PlaylistVO playlist : data) {
				List<String> tags = playlistTagMapper.searchTagsByPlaylistId(playlist.getId());
				
				// 빈 생성
				PlaylistBean playlistBean = new PlaylistBean();
				playlistBean.setDescription(playlist.getDescription());
				playlistBean.setId(playlist.getId());
				playlistBean.setMemberId(playlist.getMemberId());
				playlistBean.setRegisterDate(playlist.getRegisterDate());
				playlistBean.setTitle(playlist.getTitle());
				playlistBean.setVisibility(playlist.getVisibility());
				playlistBean.setTags(tags);
				
				// 빈 추가
				playlists.add(playlistBean);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return playlists;
	}
}
