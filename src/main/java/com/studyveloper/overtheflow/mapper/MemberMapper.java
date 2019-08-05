package com.studyveloper.overtheflow.mapper;

import java.util.List;

import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.vo.MemberVO;

public interface MemberMapper {
	
	/**
	 * 회원 정보를 추가합니다.
	 * @param memberVO 회원 정보를 가지고 있습니다.
	 * @return 성공적으로 추가했다면 1을, 실패했다면 0을 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer addMember(MemberVO memberVO) throws Exception;
	
	/**
	 * 회원 아이디로 해당 회원 정보를 삭제합니다.
	 * @param memberId 삭제할 회원의 아이디입니다.
	 * @return 성공적으로 삭제했다면 1을, 삭제할 대상이 없다면 0을 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer deleteMember(String memberId) throws Exception;
	
	/**
	 * 조건에 맞는 회원들을 삭제합니다.
	 * @param optionIntent 삭제할 회원의 조건을 설정합니다. (SearchOption만 기능합니다.)
	 * @return 삭제된 회원의 수를 반환합니다.
	 * @throws Exception 미정
	 */
	public Integer deleteMembers(OptionIntent optionIntent) throws Exception;
	
	/**
	 * 회원 정보를 수정합니다.
	 * 회원 아이디와 일치하는 정보를 전달받은 값으로 변경합니다.
	 * <br>
	 * 변한 내용이 없더라도 변경은 진행됩니다.
	 * @param memberVO 변경할 회원 정보입니다. (단 아이디 값은 변경할 수 없습니다.)
	 * @throws Exception 미정
	 */
	public Integer modifyMember(MemberVO memberVO) throws Exception;
	
	/**
	 * 회원 아이디로 회원 정보를 가져옵니다.
	 * @param memberId 검색할 회원 아이디입니다.
	 * @return 일치하는 회원 정보가 있다면 회원 정보를 반환하고, 없다면 null을 반환합니다.
	 * @throws Exception 미정
	 */
	public MemberVO searchMember(String memberId) throws Exception;
	
	/**
	 * 검색 조건들을 설정하여 회원 정보를 가져옵니다.
	 * @param optionIntent 검색할 조건입니다.
	 * @return 조건에 맞는 회원 목록을 반환합니다. 검색된 회원이 없을 경우 크기가 0인 목록이 반환됩니다.
	 * @throws Exception 미정
	 */
	public List<MemberVO> searchMembers(OptionIntent optionIntent) throws Exception;
	
	/**
	 * 테이블에 저장되어 있는 총 회원 수를 가져옵니다.
	 * @param optionIntent 검색할 조건입니다.
	 * @return 총 회원 수
	 * @throws Exception 미정
	 */
	public Integer getMemberSize(OptionIntent optionIntent) throws Exception;
}
