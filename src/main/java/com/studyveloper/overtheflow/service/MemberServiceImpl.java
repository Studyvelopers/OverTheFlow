package com.studyveloper.overtheflow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.bean.MemberBean;
import com.studyveloper.overtheflow.mapper.MemberMapper;
import com.studyveloper.overtheflow.util.IdentifierGenerator;
import com.studyveloper.overtheflow.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	public MemberBean register(MemberBean memberBean) {
		// TODO Auto-generated method stub
		if(!nullChk(memberBean)){
			return null;
		}
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail(memberBean.getEmail());
		memberVO.setIntroduction(memberBean.getIntroduction());
		memberVO.setNickname(memberBean.getNickname());
		memberVO.setPassword(memberBean.getPassword());
		memberVO.setRegisterDate(new Date());
		memberVO.setTypeId(memberBean.getTypeId());
		memberVO.setId(IdentifierGenerator.generateId(memberBean.getNickname().hashCode()));
		
		try{
			memberMapper.registerMember(memberVO);
		}catch(DuplicateKeyException e){
			e.printStackTrace();
		}
		
		return new MemberBean(memberVO);
	}
	
	public Boolean unRegister(MemberBean memberBean){
		if(!nullChk(memberBean)){
			
			return false;
		}
		logger.trace("?��?�� ?��?���? ?���??�� ?��?��?���? : " +memberBean.toString());
		boolean result = false;
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail(memberBean.getEmail());
		memberVO.setIntroduction(memberBean.getIntroduction());
		memberVO.setNickname(memberBean.getNickname());
		memberVO.setPassword(memberBean.getPassword());
		memberVO.setRegisterDate(memberBean.getRegisterDate());
		memberVO.setTypeId(memberBean.getTypeId());
		memberVO.setId(memberBean.getId());
		
		logger.info("?��?��?��?�� ?���?");
		logger.trace("?��?��?��?���? ?���??�� ?��?��?���? : " + memberVO.toString());

		int res = memberMapper.unregisterMember(memberVO);
		if(res == 1){
			result = true;
			logger.info("?��?�� ?��공에 ?��공하???��?��?��.");
		}else{
			logger.error("?��?���? ?���??�� ?��?���? ?��치하?�� ?��보�? ?��?��?��?��.");
		}
		
		logger.info("?��?�� ?��?��?���??�� ???�� ?��?��");
		
		if(result){
			logger.info("?��?��?��?�� ?���?");
		}else{
			logger.error("?��?��?��?�� ?��?��");
		}
		
		logger.info("?��?��?��?��?���??�� ???�� ?��?��");
		return result;
	}
	
	public MemberBean modifyMember(String targetMemberId, String requestMemberId, String password, MemberBean memberBean){
		logger.info("?��?��?���? ?��?�� ?���?");
		if(targetMemberId == null || targetMemberId.trim().equals("")){
			logger.error("?��?��?��보�?? ?��?��?���? ?��?�� ???�� ?��?��ID�? NULL ?��?�� 빈문?��?��?��?��.");
			return null;
		}else if(requestMemberId == null || requestMemberId.trim().equals("")){
			logger.error("?��?��?���?  ?��?��?�� ?���??�� ?��?��ID�? NULL ?��?�� 빈문?��?��?��?��.");
			return null;
		}else if(password == null || password.trim().equals("")){
			logger.error("?��?��?��보�?? ?��?��?���? ?��?�� ?��?��?�� ?��별정�? ?��?��?��?���? NULL ?��?�� 빈문?��?��?��?��.");
			return null;
		}else if(memberBean == null){
			logger.error("?��?��?���?  ?��?��?���? ?��?�� ?��보�? NULL ?��?�� 빈문?��?��?��?��.");
		}
		
		logger.trace("?��?��?���? ?��?��?�� ?���??�� ?��?��ID : " + requestMemberId);
		logger.trace("?��?��?��보�?? ?��?��?��?��?�� ???�� ?��?��ID : " + targetMemberId);
		logger.trace("?��?��?���? ?��?��?�� ?���??�� ?��?��?�� ?��?��?��?�� : " + password);
		logger.trace("?��?��?���? ?��?��?�� ?���??�� ?���? : " + memberBean.toString());
		
		boolean result = false;
		
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail(memberBean.getEmail());
		memberVO.setIntroduction(memberBean.getIntroduction());
		memberVO.setNickname(memberBean.getNickname());
		memberVO.setPassword(memberBean.getPassword());
		memberVO.setRegisterDate(memberBean.getRegisterDate());
		memberVO.setTypeId(memberBean.getTypeId());
		memberVO.setId(memberBean.getId());
		
		int res = memberMapper.modifyMember(memberVO);
		if(res == 1){
			logger.info("?��?��?���? ?��?�� ?���?");
			result = true;
		}else{
			logger.error("?��?��?���? ?��?�� ?��?��");
		}
		
		logger.info("?��?��?���? ?��?�� ?���??�� ???�� ?��?��");
		if(result){
			logger.info("?��?��?���? ?��?�� ?���?");
		}else{
			logger.error("?��?��?���? ?��?�� ?��?��");
		}
		
		logger.info("?��?��?���? ?��?�� ?���??�� ???�� ?��?��");
		return memberBean;
	}
	
	public MemberBean getMember(String memberId){
		logger.info("?��?��?���? 조회 ?���?");
		logger.trace("?��?��?���? 조회�? ?���??�� ?��?��?��ID : " + memberId);
		
		MemberVO memberVO = null;
		
		memberVO = memberMapper.getMember(memberId);
		
		if(memberVO == null){
			logger.error("�??��?�� ?��?��?�� ?��?��?��?��.");
		}else{
			logger.info("�??��?�� ?��?��?�� ?���? : " + memberVO.toString());
		}
		
		logger.info("?��?��?���? 조회 ?���??�� ???�� ?��?��");
		
		MemberBean memberBean = null;
		if(memberVO == null){
			logger.error("�??��?�� ?��?��?�� ?��?��?��?��.");
		}else{
			logger.trace("�??��?�� ?��?��?�� ?���? : "+ memberVO.toString());
			memberBean = new MemberBean(memberVO);
		}
		
		logger.info("?��?��?���? 조회 ?���??�� ???�� ?��?��");
		
		return memberBean;
	}
	
	public List<MemberBean> getMembersByNickName(String nickname){
		logger.info("?��?��?��?���? ?��?���??�� ?���?");

		List<MemberBean> memberBeanList = new ArrayList<MemberBean>();
		
		List<MemberVO> memberVOList = memberMapper.getMembersByNickName(nickname);

		MemberVO memberVO = null;
		if(memberVOList == null || memberVOList.size() == 0){
			logger.error("�??��?�� ?��?��?�� ?��?��?��?��.");
		}else{
			logger.trace("�??��?�� ?��?��?�� ?�� : "+ memberVOList.size());
			for(int i=0; i<memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				logger.trace("�??��?�� ?��?��?�� ?���? : " + memberVO.toString());
				memberBeanList.add(new MemberBean(memberVO));
			}
		}
		
		logger.info("?��?��?���? 조회 ?���??�� ???�� ?��?��");
		
		return memberBeanList;
	}
	
	private Boolean nullChk(MemberBean memberBean){
		boolean res = false;
		if(memberBean.getEmail() == null || memberBean.getEmail().trim().equals("")){
			logger.error("?��메일?�� NULL ?��?�� 빈문?�� ?��?��?��.");
		}
		else if(memberBean.getPassword() == null || memberBean.getPassword().trim().equals("")){
			logger.error("?��?��?��?���? NULL ?��?�� 빈문?�� ?��?��?��.");
		}
		else if(memberBean.getNickname() == null || memberBean.getNickname().trim().equals("")){
			logger.error("?��?��?��?�� NULL ?��?�� 빈문?�� ?��?��?��.");
		}
		else if(memberBean.getId() == null || memberBean.getId().trim().equals("")){
			logger.error("ID�? NULL ?��?�� 빈문?�� ?��?��?��.");
		}else{
			res= true;
		}
		
		return res;
	}
	
	public List<MemberBean> getMembersByEmail(String email){
		logger.info("?��메일�? ?��?��?���? �??�� ?���?");
		List<MemberVO> memberVOList = null;
		MemberVO memberVO = null;
		List<MemberBean> memberBeanList = new ArrayList<MemberBean>();
		
		memberVOList = memberMapper.getMembersByEmail(email);

		
		if(memberVOList == null || memberVOList.size() == 0){
			logger.warn("?��메일�? �??��?�� ?��?��?�� ?��?��?��?��.");
		}else{
			logger.trace("?��메일�? �??��?�� ?��?��?�� ?�� : " + memberVOList.size() + "\n ?��메일�? �??��?�� ?��?��?�� ?���?\n");
			for(int i=0; i<memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				logger.trace(memberVO.toString() );
				memberBeanList.add(new MemberBean(memberVO));
			}
		}
		
		logger.info("?��메일�? ?��?��?���? �??�� ?��?��");
		return memberBeanList;
	}
	
	public Boolean follow(String loginId, String memberId){
		logger.info(loginId + "�? " + memberId + " ?��?��?�� ?��로우 ?���??��?��?��?��. ");
		boolean res = false;
		
		if(loginId == null || loginId.trim().equals("")){
			logger.error("loginId�? NULL ?��?�� 빈문?��?��?��?��.");
		}else if(memberId == null || memberId.trim().equals("")){
			logger.error("memberId�? NULL ?��?�� 빈문?��?��?��?��.");
		}else{
			logger.info(loginId + "�? " + memberId + " ?��?��?�� ?��로우 ?���??��?��?��?��. ");
			res = true;
			Map<String, String> map = new HashMap<String, String>();
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
		}
		
		logger.info(loginId + "�? " + memberId + " ?��?��?�� ?��로우 ?���??�� ???�� ?��?��. ");
		return res;
	}
	
	public Boolean unFollow(String loginId, String memberId){
		logger.info(loginId + "�? " + memberId + " ?��?��?�� ?��?��로우 ?���??��?��?��?��.");
		boolean res = false;
		
		if(loginId == null || loginId.trim().equals("")){
			logger.error("loginId�? NULL ?��?�� 빈문?��?��?��?��.");
		}else if(memberId == null || memberId.trim().equals("")){
			logger.error("memberId�? NULL ?��?�� 빈문?��?��?��?��.");
		}else{
			//

			logger.info(loginId + "�? " + memberId + " ?��?��?�� ?��?��로우 ?���??��?��?��?��.");
			
			res = true;
			Map<String, String> map = new HashMap<String, String>();
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
			//
		}
		
		logger.info(loginId + "�? " + memberId + " ?��?�� ?��?��로우 ?���??�� ???�� ?��?��.");
		return res;
		
	}
	
	public List<MemberBean> getFollows(String memberId){
		logger.info(memberId + " �? ?��로우?�� ?��?�� 목록 조회 ?���?");
		List<MemberBean> memberBeanList = new ArrayList<MemberBean>();
		
		if(memberId == null || memberId.trim().equals("")){
			logger.error("memberId�? NULL ?��?�� 빈문?��?��?��?��.");
		}else{
			//
			logger.info(memberId + " ?��?��?�� ?��로우?�� ?��?�� 목록 조회 ?���?");
			List<String> followList = memberMapper.getFollows(memberId);
			logger.trace(memberId+"�? ?��로우?�� ?��?�� ?�� : " + followList.size());
			List<MemberVO> memberVOList = new ArrayList<MemberVO>();
			MemberVO memberVO = null;
			logger.trace(memberId + "�? ?��로우?�� ?��?�� ?���?");
			for(int i=0; i<followList.size(); i++){
				memberVO = memberMapper.getMember(followList.get(i));
				memberVOList.add(memberVO);
				logger.trace(memberVO.toString());
			}
			
			logger.info(memberId + " ?��?��?�� ?��로우?�� ?��?�� 목록 조회 ?���??�� ???�� ?��?��");
			//
			memberVO = null;
			logger.trace(memberId + "�? ?��로우?�� ?��?��?�� ?�� : "+ memberVOList.size());
			
			for(int i=0; i< memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				if(memberVO != null){
					memberBeanList.add(new MemberBean(memberVO));
				}
			}
		}
		
		logger.info(memberId + " �? ?��로우?�� ?��?�� 목록 조회 ?���??�� ???�� ?��?��");
		return memberBeanList;	
	}

	public List<MemberBean> getFollowers(String memberId){
		logger.info(memberId + " �? ?��로우?�� ?��?�� 목록 조회 ?���?");
		List<MemberBean> memberBeanList = new ArrayList<MemberBean>();
		
		if(memberId == null || memberId.trim().equals("")){
			logger.error("memberId�? NULL ?��?�� 빈문?��?��?��?��.");
		}else{
			//
//			List<MemberVO> memberVOList = memberDAO.getFollowers(memberId);
			
			logger.info(memberId + " ?��?��?�� ?��로우?�� ?��?�� 목록 조회 ?���?");
			List<String> followerList = memberMapper.getFollowers(memberId);
			logger.trace(memberId+"�? ?��로우?�� ?��?�� ?�� : " + followerList.size());
			List<MemberVO> memberVOList = new ArrayList<MemberVO>();
			MemberVO memberVO = null;
			logger.trace(memberId + "�? ?��로우?�� ?��?�� ?���?");
			for(int i=0; i<followerList.size(); i++){
				memberVO = memberMapper.getMember(followerList.get(i));
				memberVOList.add(memberVO);
				logger.trace(memberVO.toString());
			}
			
			logger.info(memberId + " ?��?��?�� ?��로우?�� ?��?�� 목록 조회 ?���??�� ???�� ?��?��");
			
			//
			memberVO = null;
			logger.trace(memberId + "�? ?��로우?�� ?��?��?�� ?�� : "+ memberVOList.size());
			
			for(int i=0; i< memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				if(memberVO != null){
					memberBeanList.add(new MemberBean(memberVO));
				}
			}
		}
		
		logger.info(memberId + " �? ?��로우?�� ?��?�� 목록 조회 ?���??�� ???�� ?��?��");
		return memberBeanList;	
	}
	
}
