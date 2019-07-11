package com.studyveloper.overtheflow.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import com.studyveloper.overtheflow.mapper.MemberMapper;
import com.studyveloper.overtheflow.vo.MemberVO;

public class MemberDAO {
	
	@Autowired
	private MemberMapper memberMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	public MemberVO register(MemberVO memberVO){
		logger.info("?��?���??�� ?���?");
		logger.trace("?���?받�? ?��?���??�� ?���? : "+memberVO.toString());
		memberVO.setId(""+memberVO.hashCode());
		try{
			memberMapper.registerMember(memberVO);
		}catch(DuplicateKeyException e){
			e.printStackTrace();
		}
		logger.info("?��?���??�� ?���??�� ???�� ?��?��");
		return memberVO;
	}
	
	public boolean unregister(MemberVO memberVO){
		logger.info("?��?��?��?�� ?���?");
		logger.trace("?��?��?��?���? ?���??�� ?��?��?���? : " + memberVO.toString());
		boolean result = false;
		
		int res = memberMapper.unregisterMember(memberVO);
		if(res == 1){
			result = true;
			logger.info("?��?�� ?��공에 ?��공하???��?��?��.");
		}else{
			logger.error("?��?���? ?���??�� ?��?���? ?��치하?�� ?��보�? ?��?��?��?��.");
		}
		
		logger.info("?��?�� ?��?��?���??�� ???�� ?��?��");
		return result;
	}
	
	public boolean modifyMember(MemberVO memberVO){
		logger.info("?��?��?���? ?��?�� ?���?");
		if(memberVO == null){
			logger.error("?��?��?��?��?�� ?��?��?�� ?��보�? NULL?��?��?��.");
		}else{
			logger.info("?��?��?��?��?�� ?��?��?�� ?���? : " + memberVO.toString());
		}
		boolean result = false;
		
		int res = memberMapper.modifyMember(memberVO);
		if(res == 1){
			logger.info("?��?��?���? ?��?�� ?���?");
			result = true;
		}else{
			logger.error("?��?��?���? ?��?�� ?��?��");
		}
		
		logger.info("?��?��?���? ?��?�� ?���??�� ???�� ?��?��");
		return result;
	}
	
	public MemberVO getMember(String memberId){
		logger.info("?��?��?���? 조회 ?���?");
		
		MemberVO memberVO = null;
		
		memberVO = memberMapper.getMember(memberId);
		
		if(memberVO == null){
			logger.error("�??��?�� ?��?��?�� ?��?��?��?��.");
		}else{
			logger.info("�??��?�� ?��?��?�� ?���? : " + memberVO.toString());
		}
		
		logger.info("?��?��?���? 조회 ?���??�� ???�� ?��?��");
		
		return memberVO;
	}
	
	public List<MemberVO> getMemebersByNickName(String nickname){
		logger.info("?��?��?��?���? ?��?��?���? �??�� ?���?");
		List<MemberVO> memberList = memberMapper.getMembersByNickName(nickname);
		logger.info("?��?��?��?���? ?��?��?���? �??�� 결과 ?��?��");
		return memberList;
	}
	
	public List<MemberVO> getMembersByEmail(String email){
		logger.info("?��메일�? ?��?��?���? �??�� ?���?");
		List<MemberVO> memberVOList = memberMapper.getMembersByEmail(email);
		logger.info("?��메일�? ?��?��?���? �??�� ?��?��");
		return memberVOList;
	}
	
	public boolean follow(String loginId, String memberId){
		logger.info(loginId + "�? " + memberId + " ?��?��?�� ?��로우 ?���??��?��?��?��. ");
		boolean res = true;
		Map<String, String> map = new HashMap<>();
		try{
			map.put("loginId", loginId);
			map.put("memberId", memberId);
			memberMapper.follow(map);
		}catch(DuplicateKeyException e){
			e.printStackTrace();
			res= false;
		}
		catch(DataIntegrityViolationException ex){
			ex.printStackTrace();
			logger.info(memberId + "�? 존재?���? ?��?�� ?��?��?��?��?��.");
			res = false;
		}
		
		if(res){
			logger.info("?��로우 ?��공했?��?��?��.");
		}else{
			logger.error("?��로우 ?��?��?��?��?��?��.");
		}
		logger.info(loginId + "�? " + memberId + " ?��?��?�� ?��로우 ?���??�� ???�� ?��?��. ");
		
		return res;
	}
	
	public boolean unFollow(String loginId, String memberId){
		logger.info(loginId + "�? " + memberId + " ?��?��?�� ?��?��로우 ?���??��?��?��?��.");
		
		boolean res = true;
		Map<String, String> map = new HashMap<>();
		try{	
			map.put("loginId", loginId);
			map.put("memberId", memberId);
			memberMapper.unFollow(map);
		}catch(DuplicateKeyException e){
			e.printStackTrace();
			res= false;
		}
		catch(DataIntegrityViolationException ex){
			ex.printStackTrace();
			logger.info(memberId + "�? 존재?���? ?��?�� ?��?��?��?��?��.");
			res = false;
		}
		
		if(res){
			logger.info("?��?��로우 ?��공했?��?��?��.");
		}else{
			logger.error("?��?��로우 ?��?��?��?��?��?��.");
		}
		
		logger.info(loginId + "�? " + memberId + " ?��?�� ?��?��로우 ?���??�� ???�� ?��?��.");
		return res;
	}
	
	public List<MemberVO> getFollows(String memberId){
		logger.info(memberId + " ?��?��?�� ?��로우?�� ?��?�� 목록 조회 ?���?");
		List<String> followList = memberMapper.getFollows(memberId);
		logger.trace(memberId+"�? ?��로우?�� ?��?�� ?�� : " + followList.size());
		List<MemberVO> memberList = new ArrayList<>();
		MemberVO memberVO = null;
		logger.trace(memberId + "�? ?��로우?�� ?��?�� ?���?");
		for(int i=0; i<followList.size(); i++){
			memberVO = getMember(followList.get(i));
			memberList.add(memberVO);
			logger.trace(memberVO.toString());
		}
		
		logger.info(memberId + " ?��?��?�� ?��로우?�� ?��?�� 목록 조회 ?���??�� ???�� ?��?��");
		return memberList;
	}

	public List<MemberVO> getFollowers(String memberId){
		logger.info(memberId + " ?��?��?�� ?��로우?�� ?��?�� 목록 조회 ?���?");
		List<String> followerList = memberMapper.getFollowers(memberId);
		logger.trace(memberId+"�? ?��로우?�� ?��?�� ?�� : " + followerList.size());
		List<MemberVO> memberList = new ArrayList<>();
		MemberVO memberVO = null;
		logger.trace(memberId + "�? ?��로우?�� ?��?�� ?���?");
		for(int i=0; i<followerList.size(); i++){
			memberVO = getMember(followerList.get(i));
			memberList.add(memberVO);
			logger.trace(memberVO.toString());
		}
		
		logger.info(memberId + " ?��?��?�� ?��로우?�� ?��?�� 목록 조회 ?���??�� ???�� ?��?��");
		return memberList;
	}

	
}
