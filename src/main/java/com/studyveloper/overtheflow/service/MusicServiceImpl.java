package com.studyveloper.overtheflow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.studyveloper.overtheflow.mapper.MusicMapper;
import com.studyveloper.overtheflow.mapper.MusicTagMapper;
import com.studyveloper.overtheflow.util.IdentifierGenerator;
import com.studyveloper.overtheflow.util.PageInfo;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.util.option.MusicUnit;
import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.util.option.OptionIntent.Builder;
import com.studyveloper.overtheflow.util.option.SearchOption;
import com.studyveloper.overtheflow.vo.MusicVO;
import com.studyveloper.overtheflow.vo.TagVO;

@Service
public class MusicServiceImpl implements MusicService{
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private MusicTagMapper musicTagMapper;
	
	public MusicVO createMusic(MusicVO musicVO) throws Exception {
		// TODO Auto-generated method stub
		if(this.isMusicNull(musicVO)) throw new Exception();
		
		TransactionStatus transactionStatus =
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			musicVO.setId(IdentifierGenerator.generateId(musicVO.getTitle()));
			
			int addResult = this.musicMapper.addMusic(musicVO);
			
			if(addResult == 0) throw new Exception();
			
			List<String> tags = musicVO.getTags();
			
			if(tags != null && tags.size() > 0){
				String musicId = musicVO.getId();
				
				for(String tag : tags){
					TagVO tagVO = new TagVO();
					tagVO.setId(musicId);
					tagVO.setTagName(tag);
					
					this.musicTagMapper.addMusicTag(tagVO);
				}
			}
		} catch(RuntimeException exception){
			this.transactionManager.rollback(transactionStatus);
			throw exception;
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return musicVO;
	}

	public MusicVO modifyMusic(String loginId, MusicVO musicVO) throws Exception {
		// TODO Auto-generated method stub
		if(this.isMusicNull(musicVO) || loginId == null) throw new Exception();
		
		if(!loginId.equals(musicVO.getMemberId())) throw new Exception();
		
		TransactionStatus transactionStatus =
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			String musicId = musicVO.getId();
			
			int result = this.musicMapper.modifyMusic(musicVO);
			
			if(result == 0) throw new Exception();
			
			this.musicTagMapper.deleteMusicTag(musicId);
			
			List<String> tags = musicVO.getTags();
			
			if(tags != null && tags.size() > 0){
				for(String tag : tags){
					TagVO tagVO = new TagVO();
					
					tagVO.setId(musicId);
					tagVO.setTagName(tag);
					
					this.musicTagMapper.addMusicTag(tagVO);
				}
			}
		} catch(RuntimeException exception){
			this.transactionManager.rollback(transactionStatus);
			throw exception;
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return musicVO;
	}

	public boolean deleteMusic(String musicId, String loginId) throws Exception {
		// TODO Auto-generated method stub
		if(musicId == null || loginId == null) throw new Exception();
		
		MusicVO target = this.musicMapper.searchMusic(musicId);
		
		if(target == null) throw new Exception();
		
		if(!target.getMemberId().equals(loginId)) throw new Exception();
		
		TransactionStatus transactionStatus =
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			this.musicMapper.deleteMusic(musicId);
			this.musicTagMapper.deleteMusicTag(musicId);
		} catch(RuntimeException exception){
			this.transactionManager.rollback(transactionStatus);
			
			return false;
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return true;
	}

	public MusicVO getMusic(String musicId, String loginId) throws Exception {
		// TODO Auto-generated method stub
		if(musicId == null || loginId == null) throw new Exception();
		
		MusicVO result = this.musicMapper.searchMusic(musicId);
		
		if(result == null) throw new Exception();
		
		return result;
	}

	public List<MusicVO> getMusicsByTitle(SearchInfo searchInfo) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> conditions = searchInfo.getConditions();
		
		String title = conditions.get("title");
		String compareType = conditions.get("compareType");
		
		int currentPageNumber = searchInfo.getCurrentPageNumber();
		int perPageCount = searchInfo.getPerPageCount();
		String orderRule = searchInfo.getOrderRule();
		boolean sort;
		
		if(searchInfo.getSort() == 1){
			sort = true;
		} else {
			sort = false;
		}
		
		OptionIntent optionIntent = null;
		
		if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.EQUAL){
			
			optionIntent = new Builder()
				.appendEqualSearchOption(MusicUnit.TITLE, title, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
				.build();
			
		} else if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.LIKE){
			
			optionIntent = new Builder()
					.appendLikeSearchOption(MusicUnit.TITLE, title, true)
					.setPagingOption(perPageCount, currentPageNumber)
					.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
					.build();
			
		} else {
			
		}
		
		List<MusicVO> result = this.musicMapper.searchMusics(optionIntent);
		
		return result;
	}

	public List<MusicVO> getMusicsByNickName(SearchInfo searchInfo) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> conditions = searchInfo.getConditions();
		
		String nickname = conditions.get("nickname");
		String compareType = conditions.get("compareType");
		
		int currentPageNumber = searchInfo.getCurrentPageNumber();
		int perPageCount = searchInfo.getPerPageCount();
		String orderRule = searchInfo.getOrderRule();
		boolean sort;
		
		if(searchInfo.getSort() == 1){
			sort = true;
		} else {
			sort = false;
		}
		
		OptionIntent optionIntent = null;
		
		if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.EQUAL){
			
			optionIntent = new Builder()
				.appendEqualSearchOption(MusicUnit.MEMBER_NICKNAME, nickname, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
				.build();
			
		} else if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.LIKE){
			
			optionIntent = new Builder()
					.appendLikeSearchOption(MusicUnit.MEMBER_NICKNAME, nickname, true)
					.setPagingOption(perPageCount, currentPageNumber)
					.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
					.build();
			
		} else {
			
		}
		
		List<MusicVO> result = this.musicMapper.searchMusics(optionIntent);
		
		return result;
	}
	
	//태그 처리 어떻게 함?
	public List<MusicVO> getMusicsByTag(SearchInfo searchInfo) throws Exception {
		// TODO Auto-generated method stub
	Map<String,String> conditions = searchInfo.getConditions();
		
		String tag = conditions.get("tag");
		String compareType = conditions.get("compareType");
		
		int currentPageNumber = searchInfo.getCurrentPageNumber();
		int perPageCount = searchInfo.getPerPageCount();
		String orderRule = searchInfo.getOrderRule();
		boolean sort;
		
		if(searchInfo.getSort() == 1){
			sort = true;
		} else {
			sort = false;
		}
		
		OptionIntent optionIntent = null;
		
		if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.EQUAL){
			
			optionIntent = new Builder()
				.appendEqualSearchOption(M, tag, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
				.build();
			
		} else if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.LIKE){
			
			optionIntent = new Builder()
					.appendLikeSearchOption(MusicUnit.MEMBER_NICKNAME, nickname, true)
					.setPagingOption(perPageCount, currentPageNumber)
					.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
					.build();
			
		} else {
			
		}
		
		List<MusicVO> result = this.musicMapper.searchMusics(optionIntent);
		
		return result;
	}

	public List<MusicVO> getMyMusicsByTitle(SearchInfo searchInfo) throws Exception {
		// TODO Auto-generated method stub
	Map<String,String> conditions = searchInfo.getConditions();
		
		String title = conditions.get("title");
		String loginId = conditions.get("loginId");
		String compareType = conditions.get("compareType");
		
		int currentPageNumber = searchInfo.getCurrentPageNumber();
		int perPageCount = searchInfo.getPerPageCount();
		String orderRule = searchInfo.getOrderRule();
		boolean sort;
		
		if(searchInfo.getSort() == 1){
			sort = true;
		} else {
			sort = false;
		}
		
		OptionIntent optionIntent = null;
		
		if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.EQUAL){
			
			optionIntent = new Builder()
				.appendEqualSearchOption(MusicUnit.TITLE, title, true)
				.appendEqualSearchOption(MusicUnit.MEMBER_ID, loginId, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
				.build();
			
		} else if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.LIKE){
			
			optionIntent = new Builder()
					.appendLikeSearchOption(MusicUnit.TITLE, title, true)
					.appendLikeSearchOption(MusicUnit.MEMBER_ID, loginId, true)
					.setPagingOption(perPageCount, currentPageNumber)
					.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
					.build();
			
		} else {
			
		}
		
		List<MusicVO> result = this.musicMapper.searchMusics(optionIntent);
		
		return result;
	}

	public List<MusicVO> getMyMusicsByCategory(SearchInfo searchInfo) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> conditions = searchInfo.getConditions();
		
		String categoryId = conditions.get("categoryId");
		String loginId = conditions.get("loginId");
		String compareType = conditions.get("compareType");
		
		int currentPageNumber = searchInfo.getCurrentPageNumber();
		int perPageCount = searchInfo.getPerPageCount();
		String orderRule = searchInfo.getOrderRule();
		boolean sort;
		
		if(searchInfo.getSort() == 1){
			sort = true;
		} else {
			sort = false;
		}
		
		OptionIntent optionIntent = null;
		
		if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.EQUAL){
			
			optionIntent = new Builder()
				.appendEqualSearchOption(MusicUnit.CATEGORY_ID, categoryId, true)
				.appendEqualSearchOption(MusicUnit.MEMBER_ID, loginId, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
				.build();
			
		} else if(SearchOption.CompareType.valueOf(compareType) == SearchOption.CompareType.LIKE){
			
			optionIntent = new Builder()
					.appendLikeSearchOption(MusicUnit.CATEGORY_ID, categoryId, true)
					.appendLikeSearchOption(MusicUnit.MEMBER_ID, loginId, true)
					.setPagingOption(perPageCount, currentPageNumber)
					.appendSortingOption(MusicUnit.valueOf(orderRule), sort)
					.build();
			
		} else {
			
		}
		
		List<MusicVO> result = this.musicMapper.searchMusics(optionIntent);
		
		return result;
	}

	public List<MusicVO> getMusics(List<String> musicIds) throws Exception {
		// TODO Auto-generated method stub
		if(musicIds == null) throw new Exception();
		
		TransactionStatus transactionStatus =
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		List<MusicVO> result = new ArrayList<MusicVO>();
		
		try{
			for(String musicId : musicIds){
				MusicVO musicVO = this.musicMapper.searchMusic(musicId);
				result.add(musicVO);
			}
			
		} catch(RuntimeException e){
			this.transactionManager.rollback(transactionStatus);
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return result;
	}

	public List<MusicVO> getAllMusics(PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		List<MusicVO> result = new ArrayList<MusicVO>();
		
		result = this.musicMapper.searchMusics(null);
		
		return result;
	}
	
	private boolean isMusicNull(MusicVO musicVO){
		if(musicVO == null || musicVO.getId() == null 
				|| musicVO.getMemberId() == null
				|| musicVO.getMemberNickname() == null 
				|| musicVO.getPlayCount() == null
				|| musicVO.getPlaytime() == null 
				|| musicVO.getRegisterDate() == null
				|| musicVO.getTags() == null 
				|| musicVO.getTitle() == null
				|| musicVO.getDownloadable() == null 
				|| musicVO.getVisibility() == null
				|| musicVO.getCategoryId() == null 
				|| musicVO.getDescription() == null)
			return true;
		
		return false;
	}
}
