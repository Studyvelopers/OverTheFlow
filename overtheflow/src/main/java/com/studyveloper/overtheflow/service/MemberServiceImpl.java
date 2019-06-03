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
		logger.info("회원가입 요청");
		logger.trace("요청받은 회원가입 정보 : \n"+memberBean.toString());
		if(!nullChk(memberBean)){
			return null;
		}
		MemberVO memberVO = new MemberVO(memberBean);
		
		memberVO = memberDAO.register(memberVO);
		logger.trace("회원가입 성공에 대한 정보 : \n"+memberVO.toString());
		logger.info("회원가입 요청에 대한 응답");
		return new MemberBean(memberVO);
	}
	
	public boolean unRegister(MemberBean memberBean){
		logger.info("회원탈퇴 요청");
		if(!nullChk(memberBean)){
			logger.error("회원탈퇴를 위해 입력받은 정보가 NULL 또는 빈 문자열입니다.");
			return false;
		}
		logger.trace("회원 탈퇴를 요청한 회원정보 : " +memberBean.toString());
		boolean result = false;
		MemberVO memberVO = new MemberVO(memberBean);
		result = memberDAO.unregister(memberVO);
		if(result){
			logger.info("회원탈퇴 성공");
		}else{
			logger.error("회원탈퇴 실패");
		}
		
		logger.info("회원탈퇴요청에 대한 응답");
		return result;
	}
	
	public MemberBean modifyMember(String targetMemberId, String requestMemberId, String password, MemberBean memberBean){
		logger.info("회원정보 수정 요청");
		if(targetMemberId == null || targetMemberId.trim().equals("")){
			logger.error("회원정보를 수정하기 위한 대상 회원ID가 NULL 또는 빈문자입니다.");
			return null;
		}else if(requestMemberId == null || requestMemberId.trim().equals("")){
			logger.error("회원정보  수정을 요청한 회원ID가 NULL 또는 빈문자입니다.");
			return null;
		}else if(password == null || password.trim().equals("")){
			logger.error("회원정보를 수정하기 위해 필요한 식별정보 패스워드가 NULL 또는 빈문자입니다.");
			return null;
		}else if(memberBean == null){
			logger.error("회원정보  수정하기 위한 정보가 NULL 또는 빈문자입니다.");
		}
		
		logger.trace("회원정보 수정을 요청한 회원ID : " + requestMemberId);
		logger.trace("회원정보를 수정하려는 대상 회원ID : " + targetMemberId);
		logger.trace("회원정보 수정을 요청한 회원의 패스워드 : " + password);
		logger.trace("회원정보 수정을 요청한 정보 : " + memberBean.toString());
		boolean res = memberDAO.modifyMember(new MemberVO(memberBean));
		if(res){
			logger.info("회원정보 수정 성공");
		}else{
			logger.error("회원정보 수정 실패");
		}
		
		logger.info("회원정보 수정 요청에 대한 응답");
		return memberBean;
	}
	
	public MemberBean getMember(String memberId){
		logger.info("회원정보 조회 요청");
		logger.trace("회원정보 조회를 요청한 회원의ID : " + memberId);
		MemberVO memberVO = memberDAO.getMember(memberId);
		MemberBean memberBean = null;
		if(memberVO == null){
			logger.error("검색된 회원이 없습니다.");
		}else{
			logger.trace("검색된 회원의 정보 : "+ memberVO.toString());
			memberBean = new MemberBean(memberVO);
		}
		
		logger.info("회원정보 조회 요청에 대한 응답");
		
		return memberBean;
	}
	
	public List<MemberBean> getMembersByNickName(String memberId){
		logger.info("닉네임으로 회원검색 요청");
		logger.trace("검색을 요청한 닉네임 키워드 : " + memberId);
		List<MemberBean> memberBeanList = new ArrayList<>();
		List<MemberVO> memberVOList = memberDAO.getMemebersByNickName(memberId);
		MemberVO memberVO = null;
		if(memberVOList == null || memberVOList.size() == 0){
			logger.error("검색된 회원이 없습니다.");
		}else{
			logger.trace("검색된 회원의 수 : "+ memberVOList.size());
			for(int i=0; i<memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				logger.trace("검색된 회원의 정보 : " + memberVO.toString());
				memberBeanList.add(new MemberBean(memberVO));
			}
		}
		
		logger.info("회원정보 조회 요청에 대한 응답");
		
		return memberBeanList;
	}
	
	private boolean nullChk(MemberBean memberBean){
		boolean res = false;
		if(memberBean.getEmail() == null || memberBean.getEmail().trim().equals("")){
			logger.error("이메일이 NULL 또는 빈문자 입니다.");
		}
		else if(memberBean.getPassword() == null || memberBean.getPassword().trim().equals("")){
			logger.error("패스워드가 NULL 또는 빈문자 입니다.");
		}
		else if(memberBean.getNickname() == null || memberBean.getNickname().trim().equals("")){
			logger.error("닉네임이 NULL 또는 빈문자 입니다.");
		}
		else if(memberBean.getId() == null || memberBean.getId().trim().equals("")){
			logger.error("ID가 NULL 또는 빈문자 입니다.");
		}else{
			res= true;
		}
		
		return res;
	}
	
	public List<MemberBean> getMembersByEmail(String email){
		logger.info("이메일로 회원정보 검색 요청");
		List<MemberVO> memberVOList = null;
		MemberVO memberVO = null;
		List<MemberBean> memberBeanList = new ArrayList<>();
		memberVOList = memberDAO.getMembersByEmail(email);
		
		if(memberVOList == null || memberVOList.size() == 0){
			logger.warn("이메일로 검색된 회원이 없습니다.");
		}else{
			logger.trace("이메일로 검색된 회원의 수 : " + memberVOList.size() + "\n 이메일로 검색된 회원의 정보\n");
			for(int i=0; i<memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				logger.trace(memberVO.toString() );
				memberBeanList.add(new MemberBean(memberVO));
			}
		}
		
		logger.info("이메일로 회원정보 검색 응답");
		return memberBeanList;
	}
	
	public boolean follow(String loginId, String memberId){
		logger.info(loginId + "가 " + memberId + " 회원을 팔로우 요청했습니다. ");
		boolean res = false;
		
		if(loginId == null || loginId.trim().equals("")){
			logger.error("loginId가 NULL 또는 빈문자입니다.");
		}else if(memberId == null || memberId.trim().equals("")){
			logger.error("memberId가 NULL 또는 빈문자입니다.");
		}else{
			res = memberDAO.follow(loginId, memberId);
		}
		
		logger.info(loginId + "가 " + memberId + " 회원을 팔로우 요청에 대한 응답. ");
		return res;
	}
	
	public boolean unFollow(String loginId, String memberId){
		logger.info(loginId + "가 " + memberId + " 회원을 언팔로우 요청했습니다.");
		boolean res = false;
		
		if(loginId == null || loginId.trim().equals("")){
			logger.error("loginId가 NULL 또는 빈문자입니다.");
		}else if(memberId == null || memberId.trim().equals("")){
			logger.error("memberId가 NULL 또는 빈문자입니다.");
		}else{
			res = memberDAO.unFollow(loginId, memberId);
		}
		
		logger.info(loginId + "가 " + memberId + " 회원 언팔로우 요청에 대한 응답.");
		return res;
		
	}
	
	public List<MemberBean> getFollows(String memberId){
		logger.info(memberId + " 가 팔로우한 회원 목록 조회 요청");
		List<MemberBean> memberBeanList = new ArrayList<>();
		
		if(memberId == null || memberId.trim().equals("")){
			logger.error("memberId가 NULL 또는 빈문자입니다.");
		}else{
			List<MemberVO> memberVOList = memberDAO.getFollows(memberId);
			MemberVO memberVO = null;
			logger.trace(memberId + "가 팔로우한 회원의 수 : "+ memberVOList.size());
			
			for(int i=0; i< memberVOList.size(); i++){
				memberVO = memberVOList.get(i);
				if(memberVO != null){
					memberBeanList.add(new MemberBean(memberVO));
				}
			}
		}
		
		logger.info(memberId + " 가 팔로우한 회원 목록 조회 요청에 대한 응답");
		return memberBeanList;	
	}
	
	
}
