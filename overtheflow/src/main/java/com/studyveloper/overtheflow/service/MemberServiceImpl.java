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
	
}
