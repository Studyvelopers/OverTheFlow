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
import com.studyveloper.overtheflow.util.option.OptionUnit;
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
			exception.printStackTrace();
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
		Integer currentPageNumber = searchInfo.getCurrentPageNumber()-1;
		Integer perPageCount = searchInfo.getPerPageCount();
		MusicUnit sortingOption = MusicUnit.valueOf(searchInfo.getSortionOption());
		Boolean ordering = searchInfo.getOrdering();
		
		String keyword = searchInfo.getKeyword();
		MusicUnit searchOption = MusicUnit.valueOf(searchInfo.getSearchOption());
		String conjunction = searchInfo.getConjunction();
		
		 OptionIntent optionIntent = new Builder()
				.appendLikeSearchOption(searchOption, keyword, true)
				.appendEqualSearchOption(MusicUnit.VISIBILITY, 1, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(sortingOption, ordering)
				.build();
		
		List<MusicVO> result = this.musicMapper.searchMusics(optionIntent);
		
		return result;
	}

	public List<MusicVO> getMusicsByNickName(SearchInfo searchInfo) throws Exception {
		// TODO Auto-generated method stub
		Integer currentPageNumber = searchInfo.getCurrentPageNumber()-1;
		Integer perPageCount = searchInfo.getPerPageCount();
		MusicUnit sortingOption = MusicUnit.valueOf(searchInfo.getSortionOption());
		Boolean ordering = searchInfo.getOrdering();
		
		String keyword = searchInfo.getKeyword();
		MusicUnit searchOption = MusicUnit.valueOf(searchInfo.getSearchOption());
		String conjunction = searchInfo.getConjunction();
		
		 OptionIntent optionIntent = new Builder()
				.appendLikeSearchOption(searchOption, keyword, true)
				.appendEqualSearchOption(MusicUnit.VISIBILITY, 1, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(sortingOption, ordering)
				.build();
		
		List<MusicVO> result = this.musicMapper.searchMusics(optionIntent);
		
		return result;
	}
	
	public List<MusicVO> getMusicsByTag(SearchInfo searchInfo) throws Exception {
		// TODO Auto-generated method stub
		Integer currentPageNumber = searchInfo.getCurrentPageNumber()-1;
		Integer perPageCount = searchInfo.getPerPageCount();
		MusicUnit sortingOption = MusicUnit.valueOf(searchInfo.getSortionOption());
		Boolean ordering = searchInfo.getOrdering();
		
		String keyword = searchInfo.getKeyword();
		String conjunction = searchInfo.getConjunction(); 
		
		List<String> idList = this.musicTagMapper.searchMusicIds(keyword);
		
		if(idList == null || idList.size() == 0) return new ArrayList<MusicVO>(); 
		
		 OptionIntent optionIntent = new Builder()
				 .appendInSearchOption(MusicUnit.ID, idList.toArray(), true)
				.appendEqualSearchOption(MusicUnit.VISIBILITY, 1, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(sortingOption, ordering)
				.build();
		
		List<MusicVO> result = this.musicMapper.searchMusics(optionIntent);
		
		return result;
	}

	public List<MusicVO> getMyMusicsByTitle(SearchInfo searchInfo, String loginId)
			throws Exception {
		// TODO Auto-generated method stub
		Integer currentPageNumber = searchInfo.getCurrentPageNumber()-1;
		Integer perPageCount = searchInfo.getPerPageCount();
		MusicUnit sortingOption = MusicUnit.valueOf(searchInfo.getSortionOption());
		Boolean ordering = searchInfo.getOrdering();
		
		String keyword = searchInfo.getKeyword();
		MusicUnit searchOption = MusicUnit.valueOf(searchInfo.getSearchOption());
		String conjunction = searchInfo.getConjunction();
		
		 OptionIntent optionIntent = new Builder()
				.appendLikeSearchOption(searchOption, keyword, true)
				.appendEqualSearchOption(MusicUnit.MEMBER_ID, loginId, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(sortingOption, ordering)
				.build();
		
		List<MusicVO> result = this.musicMapper.searchMusics(optionIntent);
		
		return result;
	}

	public List<MusicVO> getMyMusicsByCategory(SearchInfo searchInfo, String loginId) 
			throws Exception {
		// TODO Auto-generated method stub
		Integer currentPageNumber = searchInfo.getCurrentPageNumber()-1;
		Integer perPageCount = searchInfo.getPerPageCount();
		MusicUnit sortingOption = MusicUnit.valueOf(searchInfo.getSortionOption());
		Boolean ordering = searchInfo.getOrdering();
		
		String keyword = searchInfo.getKeyword();
		MusicUnit searchOption = MusicUnit.valueOf(searchInfo.getSearchOption());
		String conjunction = searchInfo.getConjunction();
		
		 OptionIntent optionIntent = new Builder()
				.appendEqualSearchOption(searchOption, keyword, true)
				.appendEqualSearchOption(MusicUnit.MEMBER_ID, loginId, true)
				.setPagingOption(perPageCount, currentPageNumber)
				.appendSortingOption(sortingOption, ordering)
				.build();
		
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
		if(musicVO == null
				|| musicVO.getMemberId() == null
				|| musicVO.getMemberNickname() == null 
				|| musicVO.getPlayCount() == null
				|| musicVO.getPlaytime() == null 
				|| musicVO.getRegisterDate() == null
				|| musicVO.getTitle() == null
				|| musicVO.getDownloadable() == null 
				|| musicVO.getVisibility() == null
				|| musicVO.getCategoryId() == null 
				|| musicVO.getDescription() == null)
			return true;
		
		return false;
	}
}
