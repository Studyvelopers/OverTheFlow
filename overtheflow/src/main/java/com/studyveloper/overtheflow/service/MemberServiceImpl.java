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
	
	private boolean nullChk(MemberBean memberBean){
		boolean res = true;
		if(memberBean.getEmail() == null || memberBean.getEmail().trim().equals("")){
			System.out.println("email null or empty");
			res = false;
		}
		else if(memberBean.getPassword() == null || memberBean.getPassword().trim().equals("")){
			System.out.println("password null or empty");
			res = false;
		}
		else if(memberBean.getNickname() == null || memberBean.getNickname().trim().equals("")){
			System.out.println("nickname null or empty");
			res = false;
		}
		
		return res;
	}
	
}
