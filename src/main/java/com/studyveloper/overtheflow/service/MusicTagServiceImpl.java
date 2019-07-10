package com.studyveloper.overtheflow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.studyveloper.overtheflow.mapper.MusicTagMapper;
import com.studyveloper.overtheflow.util.SearchUnit;
import com.studyveloper.overtheflow.vo.MusicTagVO;

@Service
public class MusicTagServiceImpl implements MusicTagService{
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private MusicTagMapper musicTagMapper;
	
	public List<String> createTag(String musicNo, List<String> tagNames) throws Exception {
		// TODO Auto-generated method stub
		for(String tagName : tagNames){ 
			//Collection 여러개 중에 n개가 null 일 경우 Exception처리
			if(tagName == null) throw new Exception();
		}
		
		//해당 파라미터가 null일 경우 Exception처리
		if(musicNo == null) throw new Exception();
		
		List<String> result = new ArrayList<String>();
		
		TransactionStatus transactionStatus = 
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
			
			for(String tagName : tagNames){
				MusicTagVO musicTagVO = new MusicTagVO();
				musicTagVO.setMusicId(musicNo);
				musicTagVO.setContents(tagName);
				//DB에 제대로 등록이 되어있는지 확인하여 Exception처리 해줘야 하는가?
				if(this.musicTagMapper.addMusicTag(musicTagVO) == 1) result.add(tagName);
			}
			
		} catch(RuntimeException e){
			this.transactionManager.rollback(transactionStatus);
			//해당 RuntimeException 별로 상황에 맞는 Exception 처리
			e.printStackTrace();
			throw new Exception();
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return result;
	}

	public List<Integer> searchTag(SearchUnit searchUnit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> modifyTag(String musicNo, List<String> tagNames) throws Exception {
		// TODO Auto-generated method stub
		for(String tagName : tagNames){ 
			if(tagName == null) throw new Exception();
		}
		
		if(musicNo == null) throw new Exception();
		
		List<String> result = new ArrayList<String>();
		
		TransactionStatus transactionStatus = 
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			
			this.musicTagMapper.deleteMusicTagByMusicId(musicNo);
			
			for(String tagName : tagNames){
				MusicTagVO musicTagVO = new MusicTagVO();
				musicTagVO.setMusicId(musicNo);
				musicTagVO.setContents(tagName);
				
				if(this.musicTagMapper.addMusicTag(musicTagVO) == 1) result.add(tagName);
			}
			
		} catch(RuntimeException e){
			this.transactionManager.rollback(transactionStatus);
			
			throw new Exception();
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return result;
	}

	public List<String> getTags(String musicNo) throws Exception {
		// TODO Auto-generated method stub
		if(musicNo == null) throw new Exception();
		
		List<MusicTagVO> musicTags = this.musicTagMapper.searchMusicTagByMusicId(musicNo);
		
		List<String> result = new ArrayList<String>();
		
		//결과값이 0일때 null일때 return값과 프론트에서의 처리방식 
		if(musicTags == null) return result;
		
		for(MusicTagVO musicTag : musicTags){
			result.add(musicTag.getContents());
		}
		
		return result;
	}

	public Boolean deleteTags(String musicNo) throws Exception {
		// TODO Auto-generated method stub
		if(musicNo == null) throw new Exception();
		
		TransactionStatus transactionStatus = 
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			//원래부터 0개 였을 경우 처리방식 
			this.musicTagMapper.deleteMusicTagByMusicId(musicNo);
		} catch(RuntimeException e){
			this.transactionManager.rollback(transactionStatus);
			
			return false;
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return true;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setMusicTagMapper(MusicTagMapper musicTagMapper) {
		this.musicTagMapper = musicTagMapper;
	}
}
