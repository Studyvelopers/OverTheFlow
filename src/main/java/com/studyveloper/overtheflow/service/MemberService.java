package com.studyveloper.overtheflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.util.PageInfo;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.MemberVO;

public interface MemberService {
	
	/**
	 * 
	 * @param MemberVO
	 *            회원 가입을 하기 위한 회원의 정보를 담고있습니다.
	 * @return MemberVO 회원가입에 성공한 회원의 정보를 반환합니다.
	 * @throws Exception
	 *             미정
	 */
	public MemberVO register(MemberVO memberVO)throws Exception;
	
	/**
	 * 
	 * @param memberId,
	 *            password 회원 탈퇴를 위한 회원의 식별키와 패스워드입니다.
	 * @return Boolean 회원 탈퇴를 실패하면 false, 성공하면 true를 반환합니다.
	 * @throws Exception
	 *             미정
	 */
	public Boolean unRegister(String memberId, String password)throws Exception;
	
	/**
	 * 
	 * @param memberVO, oldPassword 수정할 회원의 정보들을 담은 MemberVO와 수정할 회원의 기존 패스워드를 전달받습니다.
	 * @return MemberVO 회원정보 수정에 성공하면 수정한 회원의 정보를 반환하고, 실패하면 null을 반환합니다.
	 * @throws Exception
	 *             미정
	 */
	public MemberVO modifyMember(MemberVO memberVO, String oldPassword)throws Exception;
	
	/**
	 * 
	 * @param memberId 회원 정보를 검색하기 위한 회원의 식별키를 전달받습니다.
	 * @return MemberVO 전달받은 식별키와 일치하는 회원정보가 있다면 검색한 회원의 정보를 반환하고, 실패하면 null을 반환합니다.
	 * @throws Exception
	 *             미정
	 */
	public MemberVO getMember(String memberId)throws Exception;
	
	/**
	 * 
	 * @param PageInfo 요청한 페이지에 대한 정보를 담고있습니다. 
	 * @return List<MemberVO> 페이지 정보에 부합하는 전체 회원의 정보를 List로 반환합니다. List의 사이즈는 PageInfo의 perpageCount와 같습니다.
	 * @throws Exception 미정
	 */
	public List<MemberVO> getAllMembers(PageInfo pageInfo)throws Exception;
	
	/**
	 * 
	 * @param SerachInfo 닉네임으로 회원을 검색하기위한 keyword와 조건, page정보들을 담고있습니다.
	 * @return List<MemberVO> 검색된 회원의 객체를 List로 반환합니다.
	 * @throws Exception 미정
	 */
	public List<MemberVO> getMembersByNickName(SearchInfo searchInfo)throws Exception;
	
	/**
	 * 
	 * @param SerachInfo 이메일로 회원을 검색하기위한 keyword와 조건, page정보들을 담고있습니다.
	 * @return List<MemberVO> 검색된 회원의 객체를 List로 반환합니다.
	 * @throws Exception 미정
	 */
	public List<MemberVO> getMembersByEmail(SearchInfo searchInfo)throws Exception;
	
	/**
	 * 
	 * @param memberIds 검색하고자 하는 회원의 식별키들을 담고있습니다.
	 * @return List<MemberVO> 검색된 회원의 객체들을 List로 반환합니다.
	 * @throws Exception 미정
	 */
	public List<MemberVO> getMembers(List<String> memberIds)throws Exception;
}

