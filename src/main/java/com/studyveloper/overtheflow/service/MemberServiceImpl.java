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
		logger.info("?��?���??�� ?���?");
		logger.trace("?���?받�? ?��?���??�� ?���? : \n"+memberBean.toString());
		if(!nullChk(memberBean)){
			return null;
		}
		MemberVO memberVO = new MemberVO(memberBean);
		
		memberVO = memberDAO.register(memberVO);
		logger.trace("?��?���??�� ?��공에 ???�� ?���? : \n"+memberVO.toString());
		logger.info("?��?���??�� ?���??�� ???�� ?��?��");
		return new MemberBean(memberVO);
	}
	
	public Boolean unRegister(MemberBean memberBean){
		logger.info("?��?��?��?�� ?���?");
		if(!nullChk(memberBean)){
			logger.error("?��?��?��?���? ?��?�� ?��?��받�? ?��보�? NULL ?��?�� �? 문자?��?��?��?��.");
			return false;
		}
		logger.trace("?��?�� ?��?���? ?���??�� ?��?��?���? : " +memberBean.toString());
		boolean result = false;
		MemberVO memberVO = new MemberVO(memberBean);
		result = memberDAO.unregister(memberVO);
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
		boolean res = memberDAO.modifyMember(new MemberVO(memberBean));
		if(res){
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
		MemberVO memberVO = memberDAO.getMember(memberId);
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
	
	public List<MemberBean> getMembersByNickName(String memberId){
		logger.info("?��?��?��?���? ?��?���??�� ?���?");
		logger.trace("�??��?�� ?���??�� ?��?��?�� ?��?��?�� : " + memberId);
		List<MemberBean> memberBeanList = new ArrayList<>();
		List<MemberVO> memberVOList = memberDAO.getMemebersByNickName(memberId);
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
		List<MemberBean> memberBeanList = new ArrayList<>();
		memberVOList = memberDAO.getMembersByEmail(email);
		
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
			res = memberDAO.follow(loginId, memberId);
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
			res = memberDAO.unFollow(loginId, memberId);
		}
		
		logger.info(loginId + "�? " + memberId + " ?��?�� ?��?��로우 ?���??�� ???�� ?��?��.");
		return res;
		
	}
	
	public List<MemberBean> getFollows(String memberId){
		logger.info(memberId + " �? ?��로우?�� ?��?�� 목록 조회 ?���?");
		List<MemberBean> memberBeanList = new ArrayList<>();
		
		if(memberId == null || memberId.trim().equals("")){
			logger.error("memberId�? NULL ?��?�� 빈문?��?��?��?��.");
		}else{
			List<MemberVO> memberVOList = memberDAO.getFollows(memberId);
			MemberVO memberVO = null;
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
		List<MemberBean> memberBeanList = new ArrayList<>();
		
		if(memberId == null || memberId.trim().equals("")){
			logger.error("memberId�? NULL ?��?�� 빈문?��?��?��?��.");
		}else{
			List<MemberVO> memberVOList = memberDAO.getFollowers(memberId);
			MemberVO memberVO = null;
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
