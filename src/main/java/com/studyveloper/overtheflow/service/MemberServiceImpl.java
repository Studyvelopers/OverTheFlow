package com.studyveloper.overtheflow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.mapper.MemberMapper;
import com.studyveloper.overtheflow.util.IdentifierGenerator;
import com.studyveloper.overtheflow.util.PageInfo;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	/**
	 * 
	 * @param MemberVO 회원 가입을 하기 위한 회원의 정보를 담고있습니다.
	 * @return MemberVO 회원가입에 성공한 회원의 정보를 반환합니다. 
	 * @throws Exception 미정
	 */
	public MemberVO register(MemberVO memberVO)throws Exception {
		boolean idCheck = false; 
		if(memberVO == null){
			return null;
		}else if(memberVO.getEmail() == null || memberVO.getEmail().trim().length() < 8){
			return null;
		}else if(memberVO.getPassword() == null || memberVO.getPassword().trim().length() < 8){
			return null;
		}else if(memberVO.getNickname() == null || memberVO.getNickname().trim().length() <8){
			return null;
		}//Exception 재정의 후 Exception 처리할 것. 일단 임의로 null return
		else{
			String id = IdentifierGenerator.generateId(""+memberVO.getEmail());
			id = "8a738f7521318301d8bdb8040758f78d63ad42d6";
			
			while(!idCheck){
				if(memberMapper.searchMember(id) != null){
					//식별키가 중복된 경우 식별키를 재생성 하기 위한 조건문
					logger.error("식별키 중복으로 재생성.");
					id = IdentifierGenerator.generateId(""+memberVO.getEmail());
				}else{
					idCheck = true;
				}
			}
			
			memberVO.setId(id);
			int addMemberResult = memberMapper.addMember(memberVO);
			if(addMemberResult > 0){
				return memberVO;
			}else{
				//이메일 중복이나 닉네임 중복 등등의 가입 오류가 생기는 이유를 어떻게 알지? 오류 처리 
			}
		}
		return null;
	}

	
    /**
	 * 
	 * @param memberId, password 회원 탈퇴를 위한 회원의 식별키와 패스워드입니다.
	 * @return Boolean 회원 탈퇴를 실패하면 false, 성공하면 true를 반환합니다. 
	 * @throws Exception 미정
	 */
	public Boolean unRegister(String memberId, String password) throws Exception {
		boolean result = false;
		MemberVO memberVO = memberMapper.searchMember(memberId);
		if(memberVO == null){
			logger.error("회원 탈퇴 실패! - 회원 식별키와 일치하는 회원이 없습니다.");
		}else{
			if(memberVO.getPassword().equals(password)){
				memberMapper.deleteMember(memberId);
				result = true;
			}else{
				logger.error("회원 탈퇴 실패! - 패스워드가 일치하지 않습니다.");
				
			}
		}
		
		
		return result;
	}

	public MemberVO modifyMember(MemberVO memberVO, String oldPassword) {
		
		return null;
	}

	public MemberVO getMember(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MemberVO> getAllMembers(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MemberVO> getMembersByNickName(SearchInfo searchInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MemberVO> getMembersByEmail(SearchInfo searchInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MemberVO> getMembers(List<String> memberIds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
