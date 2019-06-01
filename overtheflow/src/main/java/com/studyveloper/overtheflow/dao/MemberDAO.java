package com.studyveloper.overtheflow.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.studyveloper.overtheflow.controller.MemberController;
import com.studyveloper.overtheflow.mapper.MemberMapper;
import com.studyveloper.overtheflow.vo.MemberVO;

public class MemberDAO {
	
	@Autowired
	private MemberMapper memberMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	public MemberVO register(MemberVO memberVO){
		logger.info("회원가입 요청");
		logger.trace("요청받은 회원가입 정보 : "+memberVO.toString());
		memberVO.setId(""+memberVO.hashCode());
		try{
			memberMapper.registerMember(memberVO);
		}catch(DuplicateKeyException e){
			e.printStackTrace();
		}
		logger.info("회원가입 요청에 대한 응답");
		return memberVO;
	}
	
	public boolean unregister(MemberVO memberVO){
		logger.info("회원탈퇴 요청");
		logger.trace("회원탈퇴를 요청한 회원정보 : " + memberVO.toString());
		boolean result = false;
		
		int res = memberMapper.unregisterMember(memberVO);
		if(res == 1){
			result = true;
			logger.info("탈퇴 성공에 성공하였습니다.");
		}else{
			logger.error("탈퇴를 요청한 회원과 일치하는 정보가 없습니다.");
		}
		
		logger.info("회원 탈퇴요청에 대한 응답");
		return result;
	}
	
}
