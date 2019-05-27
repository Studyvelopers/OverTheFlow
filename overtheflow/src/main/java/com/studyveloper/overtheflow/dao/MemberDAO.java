package com.studyveloper.overtheflow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.studyveloper.overtheflow.mapper.MemberMapper;
import com.studyveloper.overtheflow.vo.MemberVO;

public class MemberDAO {
	
	@Autowired
	MemberMapper memberMapper;
	
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
	
}
