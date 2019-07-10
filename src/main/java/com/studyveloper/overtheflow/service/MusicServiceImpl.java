package com.studyveloper.overtheflow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.studyveloper.overtheflow.bean.MusicBean;
import com.studyveloper.overtheflow.mapper.MusicMapper;
import com.studyveloper.overtheflow.mapper.MusicTagMapper;
import com.studyveloper.overtheflow.util.IdentifierGenerator;
import com.studyveloper.overtheflow.util.SearchUnit;
import com.studyveloper.overtheflow.vo.MusicTagVO;
import com.studyveloper.overtheflow.vo.MusicVO;

public class MusicServiceImpl implements MusicService {
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private MusicMapper musicMapper;
	@Autowired
	private MusicTagMapper musicTagMapper;
	
	public MusicBean createMusic(MusicBean musicBean) throws Exception {
		// TODO Auto-generated method stub
		if(this.musicBeanNullCheck(musicBean)) throw new Exception();
		
		TransactionStatus transactionStatus =
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			MusicVO musicVO = this.beanToVO(musicBean);
			
			this.musicMapper.addMusic(musicVO);
			
			if(musicBean.getMusicTags() != null){
				List<String> musicTags = musicBean.getMusicTags();
				
				for(String musicTag : musicTags){
					MusicTagVO musicTagVO = new MusicTagVO();
					
					musicTagVO.setMusicId(musicVO.getId());
					musicTagVO.setContents(musicTag);
					
					this.musicTagMapper.addMusicTag(musicTagVO);
				}
			}
			
		} catch(RuntimeException e){
			this.transactionManager.rollback(transactionStatus);
			
			throw new Exception();
		}
		
		this.transactionManager.commit(transactionStatus);
		
		//그냥 파라미터값을 줄 것인지 키값으로 Search한후 검증해서 줄 것인지
		return musicBean;
	}

	public MusicBean modifyMusic(MusicBean musicBean) throws Exception {
		// TODO Auto-generated method stub
		if(this.musicBeanNullCheck(musicBean)) throw new Exception();
		
		TransactionStatus transactionStatus =
			this.transactionManager.getTransaction(new DefaultTransactionDefinition());	
		
		try{
			MusicVO musicVO = this.beanToVO(musicBean);
			
			this.musicMapper.modifyMusic(musicVO);
			
			if(musicBean.getMusicTags() != null){
				List<String> musicTags = musicBean.getMusicTags();
				
				String musicId = musicBean.getId();
				
				this.musicTagMapper.deleteMusicTagByMusicId(musicId);
				
				for(String musicTag : musicTags){
					MusicTagVO musicTagVO = new MusicTagVO();
					
					musicTagVO.setMusicId(musicId);
					musicTagVO.setContents(musicTag);
					
					this.musicTagMapper.addMusicTag(musicTagVO);
				}
			}
			
		} catch(RuntimeException e){
			this.transactionManager.rollback(transactionStatus);
			
			throw new Exception();
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return musicBean;
	}

	public Boolean deleteMusic(String musicId) throws Exception {
		// TODO Auto-generated method stub
		if(musicId == null) throw new Exception();
		
		TransactionStatus transactionStatus =
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			this.musicMapper.deleteMusic(musicId);
			this.musicTagMapper.deleteMusicTagByMusicId(musicId);
		} catch(RuntimeException e){
			this.transactionManager.rollback(transactionStatus);
			
			return false;
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return true;
	}

	public MusicBean getMusicInfo(String musicId) throws Exception {
		// TODO Auto-generated method stub
		if(musicId == null) throw new Exception();
		
		MusicVO musicVO;
		List<MusicTagVO> musicTagVOs;
	
		musicVO = this.musicMapper.searchMusicByMusicId(musicId);
		musicTagVOs = this.musicTagMapper.searchMusicTagByMusicId(musicId);
		
		List<String> musicTags = new ArrayList<String>();
		
		for(MusicTagVO musicTagVO : musicTagVOs){
			musicTags.add(musicTagVO.getContents());
		}
		
		MusicBean musicBean = this.voToBean(musicVO, musicTags);
		
		return musicBean;
	}

	public List<MusicBean> searchMusic(SearchUnit searchUnit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MusicBean> searchMusics(SearchUnit searchUnit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MusicBean> searchMusics(List<Integer> musicIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setMusicMapper(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}

	public void setMusicTagMapper(MusicTagMapper musicTagMapper) {
		this.musicTagMapper = musicTagMapper;
	}
	
	private boolean musicBeanNullCheck(MusicBean musicBean){
		if(musicBean.getId() == null ||
				musicBean.getTitle() == null ||
				musicBean.getPlayTime() == null ||
				musicBean.getRegisterDate() == null ||
				musicBean.getDescription() == null ||
				musicBean.getVisibilityFlag() == null ||
				musicBean.getDownloadFlag() == null ||
				musicBean.getPlayCount() == null ||
				musicBean.getCategoryId() == null ||
				musicBean.getMemberId() == null ||
				musicBean.getMusicTags() == null) return true;
		
		List<String> musicTags = musicBean.getMusicTags();
		
		for(String musicTag : musicTags){
			if(musicTag == null) return true;
		}
		
		return false;
	}
	
	private MusicVO beanToVO(MusicBean musicBean){
		MusicVO musicVO = new MusicVO();
		
		musicVO.setCategoryId(musicBean.getCategoryId());
		musicVO.setDescription(musicBean.getDescription());
		musicVO.setDownloadFlag(musicBean.getDownloadFlag());
		musicVO.setId(musicBean.getId());
		
		if(musicBean.getId() == null){
			String id = IdentifierGenerator.generateId(musicBean.getTitle().hashCode() + musicBean.getMemberId().hashCode());
			musicVO.setId(id);
		}
		
		musicVO.setMemberId(musicBean.getMemberId());
		musicVO.setPlayCount(musicBean.getPlayCount());
		musicVO.setPlayTime(musicBean.getPlayTime());
		musicVO.setRegisterDate(new Date());
		musicVO.setTitle(musicBean.getTitle());
		musicVO.setVisibilityFlag(musicBean.getVisibilityFlag());
		
		return musicVO;
	}
	
	private MusicBean voToBean(MusicVO musicVO, List<String> musicTags){
		MusicBean musicBean = new MusicBean();
		
		musicBean.setCategoryId(musicVO.getCategoryId());
		musicBean.setDescription(musicVO.getDescription());
		musicBean.setDownloadFlag(musicVO.getDownloadFlag());
		musicBean.setId(musicVO.getId());
		musicBean.setMemberId(musicVO.getMemberId());
		musicBean.setPlayCount(musicVO.getPlayCount());
		musicBean.setPlayTime(musicVO.getPlayTime());
		musicBean.setRegisterDate(musicVO.getRegisterDate());
		musicBean.setTitle(musicVO.getTitle());
		musicBean.setVisibilityFlag(musicVO.getVisibilityFlag());
		musicBean.setMusicTags(musicTags);
		
		return musicBean;
	}
}
