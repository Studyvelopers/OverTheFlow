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
		logger.info("?šŒ?›ê°??… ?š”ì²?");
		logger.trace("?š”ì²?ë°›ì? ?šŒ?›ê°??… ? •ë³? : "+memberVO.toString());
		memberVO.setId(""+memberVO.hashCode());
		try{
			memberMapper.registerMember(memberVO);
		}catch(DuplicateKeyException e){
			e.printStackTrace();
		}
		logger.info("?šŒ?›ê°??… ?š”ì²??— ???•œ ?‘?‹µ");
		return memberVO;
	}
	
	public boolean unregister(MemberVO memberVO){
		logger.info("?šŒ?›?ƒˆ?‡´ ?š”ì²?");
		logger.trace("?šŒ?›?ƒˆ?‡´ë¥? ?š”ì²??•œ ?šŒ?›? •ë³? : " + memberVO.toString());
		boolean result = false;
		
		int res = memberMapper.unregisterMember(memberVO);
		if(res == 1){
			result = true;
			logger.info("?ƒˆ?‡´ ?„±ê³µì— ?„±ê³µí•˜???Šµ?‹ˆ?‹¤.");
		}else{
			logger.error("?ƒˆ?‡´ë¥? ?š”ì²??•œ ?šŒ?›ê³? ?¼ì¹˜í•˜?Š” ? •ë³´ê? ?—†?Šµ?‹ˆ?‹¤.");
		}
		
		logger.info("?šŒ?› ?ƒˆ?‡´?š”ì²??— ???•œ ?‘?‹µ");
		return result;
	}
	
	public boolean modifyMember(MemberVO memberVO){
		logger.info("?šŒ?›? •ë³? ?ˆ˜? • ?š”ì²?");
		if(memberVO == null){
			logger.error("?ˆ˜? •?•˜? ¤?Š” ?šŒ?›?˜ ? •ë³´ê? NULL?…?‹ˆ?‹¤.");
		}else{
			logger.info("?ˆ˜? •?•˜? ¤?Š” ?šŒ?›?˜ ? •ë³? : " + memberVO.toString());
		}
		boolean result = false;
		
		int res = memberMapper.modifyMember(memberVO);
		if(res == 1){
			logger.info("?šŒ?›? •ë³? ?ˆ˜? • ?„±ê³?");
			result = true;
		}else{
			logger.error("?šŒ?›? •ë³? ?ˆ˜? • ?‹¤?Œ¨");
		}
		
		logger.info("?šŒ?›? •ë³? ?ˆ˜? • ?š”ì²??— ???•œ ?‘?‹µ");
		return result;
	}
	
	public MemberVO getMember(String memberId){
		logger.info("?šŒ?›? •ë³? ì¡°íšŒ ?š”ì²?");
		
		MemberVO memberVO = null;
		
		memberVO = memberMapper.getMember(memberId);
		
		if(memberVO == null){
			logger.error("ê²??ƒ‰?œ ?šŒ?›?´ ?—†?Šµ?‹ˆ?‹¤.");
		}else{
			logger.info("ê²??ƒ‰?œ ?šŒ?›?˜ ? •ë³? : " + memberVO.toString());
		}
		
		logger.info("?šŒ?›? •ë³? ì¡°íšŒ ?š”ì²??— ???•œ ?‘?‹µ");
		
		return memberVO;
	}
	
	public List<MemberVO> getMemebersByNickName(String nickname){
		logger.info("?‹‰?„¤?„?œ¼ë¡? ?šŒ?›? •ë³? ê²??ƒ‰ ?š”ì²?");
		List<MemberVO> memberList = memberMapper.getMembersByNickName(nickname);
		logger.info("?‹‰?„¤?„?œ¼ë¡? ?šŒ?›? •ë³? ê²??ƒ‰ ê²°ê³¼ ?‘?‹µ");
		return memberList;
	}
	
	public List<MemberVO> getMembersByEmail(String email){
		logger.info("?´ë©”ì¼ë¡? ?šŒ?›? •ë³? ê²??ƒ‰ ?š”ì²?");
		List<MemberVO> memberVOList = memberMapper.getMembersByEmail(email);
		logger.info("?´ë©”ì¼ë¡? ?šŒ?›? •ë³? ê²??ƒ‰ ?‘?‹µ");
		return memberVOList;
	}
	
	public boolean follow(String loginId, String memberId){
		logger.info(loginId + "ê°? " + memberId + " ?šŒ?›?„ ?Œ”ë¡œìš° ?š”ì²??–ˆ?Šµ?‹ˆ?‹¤. ");
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
			logger.info(memberId + "ê°? ì¡´ì¬?•˜ì§? ?•Š?Š” ?šŒ?›?…?‹ˆ?‹¤.");
			res = false;
		}
		
		if(res){
			logger.info("?Œ”ë¡œìš° ?„±ê³µí–ˆ?Šµ?‹ˆ?‹¤.");
		}else{
			logger.error("?Œ”ë¡œìš° ?‹¤?Œ¨?–ˆ?Šµ?‹ˆ?‹¤.");
		}
		logger.info(loginId + "ê°? " + memberId + " ?šŒ?›?„ ?Œ”ë¡œìš° ?š”ì²??— ???•œ ?‘?‹µ. ");
		
		return res;
	}
	
	public boolean unFollow(String loginId, String memberId){
		logger.info(loginId + "ê°? " + memberId + " ?šŒ?›?„ ?–¸?Œ”ë¡œìš° ?š”ì²??–ˆ?Šµ?‹ˆ?‹¤.");
		
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
			logger.info(memberId + "ê°? ì¡´ì¬?•˜ì§? ?•Š?Š” ?šŒ?›?…?‹ˆ?‹¤.");
			res = false;
		}
		
		if(res){
			logger.info("?–¸?Œ”ë¡œìš° ?„±ê³µí–ˆ?Šµ?‹ˆ?‹¤.");
		}else{
			logger.error("?–¸?Œ”ë¡œìš° ?‹¤?Œ¨?–ˆ?Šµ?‹ˆ?‹¤.");
		}
		
		logger.info(loginId + "ê°? " + memberId + " ?šŒ?› ?–¸?Œ”ë¡œìš° ?š”ì²??— ???•œ ?‘?‹µ.");
		return res;
	}
	
	public List<MemberVO> getFollows(String memberId){
		logger.info(memberId + " ?šŒ?›?´ ?Œ”ë¡œìš°?•œ ?šŒ?› ëª©ë¡ ì¡°íšŒ ?š”ì²?");
		List<String> followList = memberMapper.getFollows(memberId);
		logger.trace(memberId+"ê°? ?Œ”ë¡œìš°?•œ ?šŒ?› ?ˆ˜ : " + followList.size());
		List<MemberVO> memberList = new ArrayList<>();
		MemberVO memberVO = null;
		logger.trace(memberId + "ê°? ?Œ”ë¡œìš°?•œ ?šŒ?› ? •ë³?");
		for(int i=0; i<followList.size(); i++){
			memberVO = getMember(followList.get(i));
			memberList.add(memberVO);
			logger.trace(memberVO.toString());
		}
		
		logger.info(memberId + " ?šŒ?›?´ ?Œ”ë¡œìš°?•œ ?šŒ?› ëª©ë¡ ì¡°íšŒ ?š”ì²??— ???•œ ?‘?‹µ");
		return memberList;
	}

	public List<MemberVO> getFollowers(String memberId){
		logger.info(memberId + " ?šŒ?›?„ ?Œ”ë¡œìš°?•œ ?šŒ?› ëª©ë¡ ì¡°íšŒ ?š”ì²?");
		List<String> followerList = memberMapper.getFollowers(memberId);
		logger.trace(memberId+"ë¥? ?Œ”ë¡œìš°?•œ ?šŒ?› ?ˆ˜ : " + followerList.size());
		List<MemberVO> memberList = new ArrayList<>();
		MemberVO memberVO = null;
		logger.trace(memberId + "ë¥? ?Œ”ë¡œìš°?•œ ?šŒ?› ? •ë³?");
		for(int i=0; i<followerList.size(); i++){
			memberVO = getMember(followerList.get(i));
			memberList.add(memberVO);
			logger.trace(memberVO.toString());
		}
		
		logger.info(memberId + " ?šŒ?›?„ ?Œ”ë¡œìš°?•œ ?šŒ?› ëª©ë¡ ì¡°íšŒ ?š”ì²??— ???•œ ?‘?‹µ");
		return memberList;
	}

	
}
