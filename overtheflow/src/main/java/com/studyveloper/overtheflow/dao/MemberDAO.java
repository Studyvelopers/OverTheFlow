package com.studyveloper.overtheflow.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

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
	
	public boolean follow(String loginId, String memberId){
		logger.info(loginId + "가 " + memberId + " 회원을 팔로우 요청했습니다. ");
		boolean res = true;
		Map<String, String> map = new HashMap<>();
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
			logger.info(memberId + "가 존재하지 않는 회원입니다.");
			res = false;
		}
		
		if(res){
			logger.info("팔로우 성공했습니다.");
		}else{
			logger.error("팔로우 실패했습니다.");
		}
		logger.info(loginId + "가 " + memberId + " 회원을 팔로우 요청에 대한 응답. ");
		
		return res;
	}
	
	public boolean unFollow(String loginId, String memberId){
		logger.info(loginId + "가 " + memberId + " 회원을 언팔로우 요청했습니다.");
		
		boolean res = true;
		Map<String, String> map = new HashMap<>();
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
			logger.info(memberId + "가 존재하지 않는 회원입니다.");
			res = false;
		}
		
		if(res){
			logger.info("언팔로우 성공했습니다.");
		}else{
			logger.error("언팔로우 실패했습니다.");
		}
		
		logger.info(loginId + "가 " + memberId + " 회원 언팔로우 요청에 대한 응답.");
		return res;
	}
	
	public List<MemberVO> getFollows(String memberId){
		logger.info(memberId + " 회원이 팔로우한 회원 목록 조회 요청");
		List<String> followList = memberMapper.getFollows(memberId);
		logger.trace(memberId+"가 팔로우한 회원 수 : " + followList.size());
		List<MemberVO> memberList = new ArrayList<>();
		MemberVO memberVO = null;
		logger.trace(memberId + "가 팔로우한 회원 정보");
		for(int i=0; i<followList.size(); i++){
			memberVO = getMember(followList.get(i));
			memberList.add(memberVO);
			logger.trace(memberVO.toString());
		}
		
		logger.info(memberId + " 회원이 팔로우한 회원 목록 조회 요청에 대한 응답");
		return memberList;
	}
	
}
