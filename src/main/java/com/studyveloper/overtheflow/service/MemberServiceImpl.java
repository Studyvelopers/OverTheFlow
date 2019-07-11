package com.studyveloper.overtheflow.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.bean.MemberBean;
import com.studyveloper.overtheflow.dao.MemberDAO;
import com.studyveloper.overtheflow.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	public MemberBean register(MemberBean memberBean) {
		// TODO Auto-generated method stub
		logger.info("?šŒ?›ê°??… ?š”ì²?");
		logger.trace("?š”ì²?ë°›ì? ?šŒ?›ê°??… ? •ë³? : \n"+memberBean.toString());
		if(!nullChk(memberBean)){
			return null;
		}
		MemberVO memberVO = new MemberVO(memberBean);
		
		memberVO = memberDAO.register(memberVO);
		logger.trace("?šŒ?›ê°??… ?„±ê³µì— ???•œ ? •ë³? : \n"+memberVO.toString());
		logger.info("?šŒ?›ê°??… ?š”ì²??— ???•œ ?‘?‹µ");
		return new MemberBean(memberVO);
	}
	
	public Boolean unRegister(MemberBean memberBean){
		logger.info("?šŒ?›?ƒˆ?‡´ ?š”ì²?");
		if(!nullChk(memberBean)){
			logger.error("?šŒ?›?ƒˆ?‡´ë¥? ?œ„?•´ ?…? ¥ë°›ì? ? •ë³´ê? NULL ?˜?Š” ë¹? ë¬¸ì?—´?…?‹ˆ?‹¤.");
			return false;
		}
		logger.trace("?šŒ?› ?ƒˆ?‡´ë¥? ?š”ì²??•œ ?šŒ?›? •ë³? : " +memberBean.toString());
		boolean result = false;
		MemberVO memberVO = new MemberVO(memberBean);
		result = memberDAO.unregister(memberVO);
		if(result){
			logger.info("?šŒ?›?ƒˆ?‡´ ?„±ê³?");
		}else{
			logger.error("?šŒ?›?ƒˆ?‡´ ?‹¤?Œ¨");
		}
		
		logger.info("?šŒ?›?ƒˆ?‡´?š”ì²??— ???•œ ?‘?‹µ");
		return result;
	}
	
	public MemberBean modifyMember(String targetMemberId, String requestMemberId, String password, MemberBean memberBean){
		logger.info("?šŒ?›? •ë³? ?ˆ˜? • ?š”ì²?");
		if(targetMemberId == null || targetMemberId.trim().equals("")){
			logger.error("?šŒ?›? •ë³´ë?? ?ˆ˜? •?•˜ê¸? ?œ„?•œ ???ƒ ?šŒ?›IDê°? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
			return null;
		}else if(requestMemberId == null || requestMemberId.trim().equals("")){
			logger.error("?šŒ?›? •ë³?  ?ˆ˜? •?„ ?š”ì²??•œ ?šŒ?›IDê°? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
			return null;
		}else if(password == null || password.trim().equals("")){
			logger.error("?šŒ?›? •ë³´ë?? ?ˆ˜? •?•˜ê¸? ?œ„?•´ ?•„?š”?•œ ?‹ë³„ì •ë³? ?Œ¨?Š¤?›Œ?“œê°? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
			return null;
		}else if(memberBean == null){
			logger.error("?šŒ?›? •ë³?  ?ˆ˜? •?•˜ê¸? ?œ„?•œ ? •ë³´ê? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
		}
		
		logger.trace("?šŒ?›? •ë³? ?ˆ˜? •?„ ?š”ì²??•œ ?šŒ?›ID : " + requestMemberId);
		logger.trace("?šŒ?›? •ë³´ë?? ?ˆ˜? •?•˜? ¤?Š” ???ƒ ?šŒ?›ID : " + targetMemberId);
		logger.trace("?šŒ?›? •ë³? ?ˆ˜? •?„ ?š”ì²??•œ ?šŒ?›?˜ ?Œ¨?Š¤?›Œ?“œ : " + password);
		logger.trace("?šŒ?›? •ë³? ?ˆ˜? •?„ ?š”ì²??•œ ? •ë³? : " + memberBean.toString());
		boolean res = memberDAO.modifyMember(new MemberVO(memberBean));
		if(res){
			logger.info("?šŒ?›? •ë³? ?ˆ˜? • ?„±ê³?");
		}else{
			logger.error("?šŒ?›? •ë³? ?ˆ˜? • ?‹¤?Œ¨");
		}
		
		logger.info("?šŒ?›? •ë³? ?ˆ˜? • ?š”ì²??— ???•œ ?‘?‹µ");
		return memberBean;
	}
	
	public MemberBean getMember(String memberId){
		logger.info("?šŒ?›? •ë³? ì¡°íšŒ ?š”ì²?");
		logger.trace("?šŒ?›? •ë³? ì¡°íšŒë¥? ?š”ì²??•œ ?šŒ?›?˜ID : " + memberId);
		MemberVO memberVO = memberDAO.getMember(memberId);
		MemberBean memberBean = null;
		if(memberVO == null){
			logger.error("ê²??ƒ‰?œ ?šŒ?›?´ ?—†?Šµ?‹ˆ?‹¤.");
		}else{
			logger.trace("ê²??ƒ‰?œ ?šŒ?›?˜ ? •ë³? : "+ memberVO.toString());
			memberBean = new MemberBean(memberVO);
		}
		
		logger.info("?šŒ?›? •ë³? ì¡°íšŒ ?š”ì²??— ???•œ ?‘?‹µ");
		
		return memberBean;
	}
	
	public List<MemberBean> getMembersByNickName(String memberId){
		logger.info("?‹‰?„¤?„?œ¼ë¡? ?šŒ?›ê²??ƒ‰ ?š”ì²?");
		logger.trace("ê²??ƒ‰?„ ?š”ì²??•œ ?‹‰?„¤?„ ?‚¤?›Œ?“œ : " + memberId);
		List<MemberBean> memberBeanList = new ArrayList<>();
		List<MemberVO> memberVOList = memberDAO.getMemebersByNickName(memberId);
		MemberVO memberVO = null;
		if(memberVOList == null || memberVOList.size() == 0){
			logger.error("ê²??ƒ‰?œ ?šŒ?›?´ ?—†?Šµ?‹ˆ?‹¤.");
		}else{
			logger.trace("ê²??ƒ‰?œ ?šŒ?›?˜ ?ˆ˜ : "+ memberVOList.size());
			for(int i=0; i<memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				logger.trace("ê²??ƒ‰?œ ?šŒ?›?˜ ? •ë³? : " + memberVO.toString());
				memberBeanList.add(new MemberBean(memberVO));
			}
		}
		
		logger.info("?šŒ?›? •ë³? ì¡°íšŒ ?š”ì²??— ???•œ ?‘?‹µ");
		
		return memberBeanList;
	}
	
	private Boolean nullChk(MemberBean memberBean){
		boolean res = false;
		if(memberBean.getEmail() == null || memberBean.getEmail().trim().equals("")){
			logger.error("?´ë©”ì¼?´ NULL ?˜?Š” ë¹ˆë¬¸? ?…?‹ˆ?‹¤.");
		}
		else if(memberBean.getPassword() == null || memberBean.getPassword().trim().equals("")){
			logger.error("?Œ¨?Š¤?›Œ?“œê°? NULL ?˜?Š” ë¹ˆë¬¸? ?…?‹ˆ?‹¤.");
		}
		else if(memberBean.getNickname() == null || memberBean.getNickname().trim().equals("")){
			logger.error("?‹‰?„¤?„?´ NULL ?˜?Š” ë¹ˆë¬¸? ?…?‹ˆ?‹¤.");
		}
		else if(memberBean.getId() == null || memberBean.getId().trim().equals("")){
			logger.error("IDê°? NULL ?˜?Š” ë¹ˆë¬¸? ?…?‹ˆ?‹¤.");
		}else{
			res= true;
		}
		
		return res;
	}
	
	public List<MemberBean> getMembersByEmail(String email){
		logger.info("?´ë©”ì¼ë¡? ?šŒ?›? •ë³? ê²??ƒ‰ ?š”ì²?");
		List<MemberVO> memberVOList = null;
		MemberVO memberVO = null;
		List<MemberBean> memberBeanList = new ArrayList<>();
		memberVOList = memberDAO.getMembersByEmail(email);
		
		if(memberVOList == null || memberVOList.size() == 0){
			logger.warn("?´ë©”ì¼ë¡? ê²??ƒ‰?œ ?šŒ?›?´ ?—†?Šµ?‹ˆ?‹¤.");
		}else{
			logger.trace("?´ë©”ì¼ë¡? ê²??ƒ‰?œ ?šŒ?›?˜ ?ˆ˜ : " + memberVOList.size() + "\n ?´ë©”ì¼ë¡? ê²??ƒ‰?œ ?šŒ?›?˜ ? •ë³?\n");
			for(int i=0; i<memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				logger.trace(memberVO.toString() );
				memberBeanList.add(new MemberBean(memberVO));
			}
		}
		
		logger.info("?´ë©”ì¼ë¡? ?šŒ?›? •ë³? ê²??ƒ‰ ?‘?‹µ");
		return memberBeanList;
	}
	
	public Boolean follow(String loginId, String memberId){
		logger.info(loginId + "ê°? " + memberId + " ?šŒ?›?„ ?Œ”ë¡œìš° ?š”ì²??–ˆ?Šµ?‹ˆ?‹¤. ");
		boolean res = false;
		
		if(loginId == null || loginId.trim().equals("")){
			logger.error("loginIdê°? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
		}else if(memberId == null || memberId.trim().equals("")){
			logger.error("memberIdê°? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
		}else{
			res = memberDAO.follow(loginId, memberId);
		}
		
		logger.info(loginId + "ê°? " + memberId + " ?šŒ?›?„ ?Œ”ë¡œìš° ?š”ì²??— ???•œ ?‘?‹µ. ");
		return res;
	}
	
	public Boolean unFollow(String loginId, String memberId){
		logger.info(loginId + "ê°? " + memberId + " ?šŒ?›?„ ?–¸?Œ”ë¡œìš° ?š”ì²??–ˆ?Šµ?‹ˆ?‹¤.");
		boolean res = false;
		
		if(loginId == null || loginId.trim().equals("")){
			logger.error("loginIdê°? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
		}else if(memberId == null || memberId.trim().equals("")){
			logger.error("memberIdê°? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
		}else{
			res = memberDAO.unFollow(loginId, memberId);
		}
		
		logger.info(loginId + "ê°? " + memberId + " ?šŒ?› ?–¸?Œ”ë¡œìš° ?š”ì²??— ???•œ ?‘?‹µ.");
		return res;
		
	}
	
	public List<MemberBean> getFollows(String memberId){
		logger.info(memberId + " ê°? ?Œ”ë¡œìš°?•œ ?šŒ?› ëª©ë¡ ì¡°íšŒ ?š”ì²?");
		List<MemberBean> memberBeanList = new ArrayList<>();
		
		if(memberId == null || memberId.trim().equals("")){
			logger.error("memberIdê°? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
		}else{
			List<MemberVO> memberVOList = memberDAO.getFollows(memberId);
			MemberVO memberVO = null;
			logger.trace(memberId + "ê°? ?Œ”ë¡œìš°?•œ ?šŒ?›?˜ ?ˆ˜ : "+ memberVOList.size());
			
			for(int i=0; i< memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				if(memberVO != null){
					memberBeanList.add(new MemberBean(memberVO));
				}
			}
		}
		
		logger.info(memberId + " ê°? ?Œ”ë¡œìš°?•œ ?šŒ?› ëª©ë¡ ì¡°íšŒ ?š”ì²??— ???•œ ?‘?‹µ");
		return memberBeanList;	
	}

	public List<MemberBean> getFollowers(String memberId){
		logger.info(memberId + " ë¥? ?Œ”ë¡œìš°?•œ ?šŒ?› ëª©ë¡ ì¡°íšŒ ?š”ì²?");
		List<MemberBean> memberBeanList = new ArrayList<>();
		
		if(memberId == null || memberId.trim().equals("")){
			logger.error("memberIdê°? NULL ?˜?Š” ë¹ˆë¬¸??…?‹ˆ?‹¤.");
		}else{
			List<MemberVO> memberVOList = memberDAO.getFollowers(memberId);
			MemberVO memberVO = null;
			logger.trace(memberId + "ë¥? ?Œ”ë¡œìš°?•œ ?šŒ?›?˜ ?ˆ˜ : "+ memberVOList.size());
			
			for(int i=0; i< memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				if(memberVO != null){
					memberBeanList.add(new MemberBean(memberVO));
				}
			}
		}
		
		logger.info(memberId + " ë¥? ?Œ”ë¡œìš°?•œ ?šŒ?› ëª©ë¡ ì¡°íšŒ ?š”ì²??— ???•œ ?‘?‹µ");
		return memberBeanList;	
	}
	
}
