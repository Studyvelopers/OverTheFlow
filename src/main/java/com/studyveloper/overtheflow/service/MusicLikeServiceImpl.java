package com.studyveloper.overtheflow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.studyveloper.overtheflow.mapper.MemberLikesMusicMapper;
import com.studyveloper.overtheflow.vo.LikeVO;
import com.studyveloper.overtheflow.vo.MemberLikesMusicVO;

@Service
public class MusicLikeServiceImpl implements MusicLikeService {
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private MemberLikesMusicMapper memberLikesMusicMapper;
	
	public Boolean likeMusic(LikeVO likeVO) throws Exception {
		// TODO Auto-generated method stub
		if(this.isLikeVoNull(likeVO)) throw new Exception();
		
		TransactionStatus transactionStatus = 
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			
			this.memberLikesMusicMapper.addMemberLikesMusic(likeVO);
			
		} catch(RuntimeException e){
			this.transactionManager.rollback(transactionStatus);
			
			throw new Exception();
			
			//return false;
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return true;
	}

	public Boolean cancelLikeMuisc(LikeVO likeVO) throws Exception {
		// TODO Auto-generated method stub
		if(this.isLikeVoNull(likeVO)) throw new Exception();
		
		TransactionStatus transactionStatus = 
				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			
			this.memberLikesMusicMapper.deleteMemberLikesMusic(likeVO);
			
		} catch(RuntimeException e){
			this.transactionManager.rollback(transactionStatus);
			
			throw new Exception();
			
			//return false;
		}
		
		this.transactionManager.commit(transactionStatus);
		
		return true;
	}
 
	public List<String> getLikeMusics(String memberId) throws Exception {
		// TODO Auto-generated method stub
		if(memberId == null) throw new Exception();
		
		List<String> result = new ArrayList<String>();
		
		this.memberLikesMusicMapper.searchMemberIds(memberId);
		
		if(memberLikesMusicVOs != null){
			for(MemberLikesMusicVO memberLikesMusicVO : memberLikesMusicVOs){
				result.add(memberLikesMusicVO.getMusicId()); 
			}
		}
		
		return result;
	}

	private boolean isLikeVoNull(LikeVO likeVO){
		if(likeVO == null
				|| likeVO.getid() == null
				|| likeVO.getMemberId() == null)
			return true;
		
		return false;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setMemberLikesMusicMapper(MemberLikesMusicMapper memberLikesMusicMapper) {
		this.memberLikesMusicMapper = memberLikesMusicMapper;
	}
}
