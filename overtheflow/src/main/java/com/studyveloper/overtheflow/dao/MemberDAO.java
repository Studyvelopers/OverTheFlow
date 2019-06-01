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
	
	public boolean modifyMember(MemberVO memberVO){
		logger.info("회원정보 수정 요청");
		if(memberVO == null){
			logger.error("수정하려는 회원의 정보가 NULL입니다.");
		}else{
			logger.info("수정하려는 회원의 정보 : " + memberVO.toString());
		}
		boolean result = false;
		
		int res = memberMapper.modifyMember(memberVO);
		if(res == 1){
			logger.info("회원정보 수정 성공");
			result = true;
		}else{
			logger.error("회원정보 수정 실패");
		}
		
		logger.info("회원정보 수정 요청에 대한 응답");
		return result;
	}
	
	
	public MemberVO getMember(String memberId){
		logger.info("회원정보 조회 요청");
		
		MemberVO memberVO = null;
		
		memberVO = memberMapper.getMember(memberId);
		
		if(memberVO == null){
			logger.error("검색된 회원이 없습니다.");
		}else{
			logger.info("검색된 회원의 정보 : " + memberVO.toString());
		}
		
		logger.info("회원정보 조회 요청에 대한 응답");
		
		return memberVO;
	}
	
}
