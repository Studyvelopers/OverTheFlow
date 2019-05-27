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
		memberVO.setId(""+memberVO.hashCode());
		try{
			memberMapper.registerMember(memberVO);
		}catch(DuplicateKeyException e){
			e.printStackTrace();
		}
		return memberVO;
	}
	
	public boolean unregister(MemberVO memberVO){
		boolean result = false;
		
		int res = memberMapper.unregisterMember(memberVO);
		if(res == 1){
			result = true;
		}
		return result;
	}
	
	public boolean modifyMember(MemberVO memberVO){
		boolean result = false;
		
		int res = memberMapper.modifyMember(memberVO);
		if(res == 1){
			result = true;
		}
		return result;
	}
	
	public MemberVO getMember(String memberId){
		MemberVO memberVO = null;
		
		memberVO = memberMapper.getMember(memberId);
		
		return memberVO;
	}
	
	public List<MemberVO> getMemebersByNickName(String nickname){
		logger.info("닉네임으로 회원정보 검색 요청");
		List<MemberVO> memberList = memberMapper.getMembersByNickName(nickname);
		logger.info("닉네임으로 회원정보 검색 결과 응답");
		return memberList;
	}
	
	public List<MemberVO> getMembersByEmail(String email){
		logger.info("이메일로 회원정보 검색 요청");
		List<MemberVO> memberVOList = memberMapper.getMembersByEmail(email);
		logger.info("이메일로 회원정보 검색 응답");
		return memberVOList;
	}
	
}
