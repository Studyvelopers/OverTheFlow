package com.studyveloper.overtheflow.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.mapper.MemberMapper;
import com.studyveloper.overtheflow.util.IdentifierGenerator;
import com.studyveloper.overtheflow.util.PageInfo;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.util.option.MemberUnit;
import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.util.option.OptionUnit;
import com.studyveloper.overtheflow.util.option.SearchOption;
import com.studyveloper.overtheflow.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	/**
	 * 
	 * @param MemberVO
	 *            회원 가입을 하기 위한 회원의 정보를 담고있습니다.
	 * @return MemberVO 회원가입에 성공한 회원의 정보를 반환합니다.
	 * @throws Exception
	 *             미정
	 */
	public MemberVO register(MemberVO memberVO) throws Exception {
		boolean idCheck = false;
		if (memberVO == null) {
			logger.error("회원가입 실패! - 회원가입을 위한 MemberVO가 NULL입니다.");
			return null;
		} else if (memberVO.getEmail() == null || memberVO.getEmail().trim().equals("")) {
			logger.error("회원가입 실패! - 회원가입을 위한 email이 NULL입니다.");
			return null;
		} else if (memberVO.getPassword() == null || memberVO.getPassword().trim().equals("")) {
			logger.error("회원가입 실패! - 회원가입을 위한 password가 NULL입니다.");
			return null;
		} else if (memberVO.getNickname() == null || memberVO.getNickname().trim().equals("")) {
			logger.error("회원가입 실패! - 회원가입을 위한 nickname이 NULL입니다.");
			return null;
		} // Exception 재정의 후 Exception 처리할 것. 일단 임의로 null return
		else {
			String id = IdentifierGenerator.generateId("" + memberVO.getEmail());
			id = "8a738f7521318301d8bdb8040758f78d63ad42d6";

			while (!idCheck) {
				if (memberMapper.searchMember(id) != null) {
					// 식별키가 중복된 경우 식별키를 재생성 하기 위한 조건문
					logger.error("식별키 중복으로 재생성.");
					id = IdentifierGenerator.generateId("" + memberVO.getEmail());
				} else {
					idCheck = true;
				}
			}

			memberVO.setId(id);
			int addMemberResult = memberMapper.addMember(memberVO);
			if (addMemberResult > 0) {
				return memberVO;
			} else {
				// 이메일 중복이나 닉네임 중복 등등의 가입 오류가 생기는 이유를 어떻게 알지? 오류 처리
			}
		}
		return null;
	}

	/**
	 * 
	 * @param memberId,
	 *            password 회원 탈퇴를 위한 회원의 식별키와 패스워드입니다.
	 * @return Boolean 회원 탈퇴를 실패하면 false, 성공하면 true를 반환합니다.
	 * @throws Exception
	 *             미정
	 */
	public Boolean unRegister(String memberId, String password) throws Exception {
		boolean result = false;
		if(memberId == null || memberId.trim().equals("")){
			logger.error("회원 탈퇴 실패! - 회원 식별키가 NULL입니다.");
			return result;
		}else if(password == null || password.trim().equals("")){
			logger.error("회원 탈퇴 실패! - 비밀번호가 NULL입니다.");
			return result;
		}
		MemberVO memberVO = memberMapper.searchMember(memberId);
		if (memberVO == null) {
			logger.error("회원 탈퇴 실패! - 회원 식별키와 일치하는 회원이 없습니다.");
		} else {
			if (memberVO.getPassword().equals(password)) {
				memberMapper.deleteMember(memberId);
				result = true;
			} else {
				logger.error("회원 탈퇴 실패! - 패스워드가 일치하지 않습니다.");

			}
		}

		return result;
	}

	/**
	 * 
	 * @param memberVO, oldPassword 수정할 회원의 정보들을 담은 MemberVO와 수정할 회원의 기존 패스워드를 전달받습니다.
	 * @return MemberVO 회원정보 수정에 성공하면 수정한 회원의 정보를 반환하고, 실패하면 null을 반환합니다.
	 * @throws Exception
	 *             미정
	 */
	public MemberVO modifyMember(MemberVO memberVO, String oldPassword) throws Exception {
		if (memberVO == null) {
			logger.error("회원정보 수정 실패! - 회원정보를 수정하기위한 MemberVO가 NULL입니다.");
			return null;
		} else if (memberVO.getEmail() == null || memberVO.getEmail().trim().equals("")) {
			logger.error("회원정보 수정 실패! - 회원 정보를 수정하기 위한 email이 NULL입니다.");
			return null;
		} else if (memberVO.getPassword() == null || memberVO.getPassword().trim().equals("")) {
			logger.error("회원정보 수정 실패! - 회원 정보를 수정하기 위한 password가 NULL입니다.");
			return null;
		} else if (memberVO.getNickname() == null || memberVO.getNickname().trim().equals("")) {
			logger.error("회원정보 수정 실패! - 회원 정보를 수정하기 위한 nickname이 NULL입니다.");
			return null;
		}else if(oldPassword == null || oldPassword.trim().equals("")){
			logger.error("회원정보 수정 실패! - 회원 정보를 수정하기 위한 oldPassword가 NULL입니다.");
			return null;
		}
		MemberVO memberVO2 = memberMapper.searchMember(memberVO.getId());
		if (memberVO2 == null) {
			logger.error("회원정보 수정 실패! - 회원 식별키와 일치하는 회원정보가 없습니다.");
		} else {
			if (memberVO2.getPassword().equals(oldPassword)) {
				memberMapper.modifyMember(memberVO);
				logger.info("회원정보 수정 성공!");
				return memberVO;
			} else {
				logger.error("회원정보 수정 실패! - 비밀번호가 일치하지 않습니다.");
			}
		}
		return null;
	}

	/**
	 * 
	 * @param memberId 회원 정보를 검색하기 위한 회원의 식별키를 전달받습니다.
	 * @return MemberVO 전달받은 식별키와 일치하는 회원정보가 있다면 검색한 회원의 정보를 반환하고, 실패하면 null을 반환합니다.
	 * @throws Exception
	 *             미정
	 */
	public MemberVO getMember(String memberId)throws Exception {
		if(memberId == null || memberId.trim().equals("")){
			logger.error("회원 검색 실패! - 회원 검색을 위한 memberId가 NULL입니다.");
			return null;
		}
		
		MemberVO memberVO = memberMapper.searchMember(memberId);
		
		if(memberVO != null){
			logger.info("회원 검색 성공! - " + memberVO.toString());
		}else{
			logger.error("회원 검색 실패! - memberId와 일치하는 회원의 정보가 없습니다.");
			return null;
		}
		
		return memberVO;
	}

	/**
	 * 
	 * @param PageInfo 요청한 페이지에 대한 정보를 담고있습니다. 
	 * @return List<MemberVO> 페이지 정보에 부합하는 전체 회원의 정보를 List로 반환합니다. List의 사이즈는 PageInfo의 perpageCount와 같습니다.
	 * @throws Exception 미정
	 */
	public List<MemberVO> getAllMembers(PageInfo pageInfo)throws Exception {
		if(pageInfo == null){
			
		}else{
			OptionIntent.Builder builder = new OptionIntent.Builder();
			OptionIntent optionIntent= builder.build();
			int maxCount = memberMapper.getMemberSize(optionIntent);
			pageInfo.setMaxCount(maxCount);
			//페이징
			int offset = (pageInfo.getCurrentPageNumber()-1) * pageInfo.getPerPageCount();
			int size = pageInfo.getPerPageCount();
			builder.setOffset(offset);
			builder.setSize(size);
			
			//정렬
			builder.appendSortingOption(MemberUnit.valueOf(pageInfo.getOrderRule()), pageInfo.getSort());
			
			List<MemberVO> members = memberMapper.searchMembers(optionIntent);
			if(members.size() == 0){
				logger.info("검색된 회원이 없습니다.");
			}else{
				logger.info("모든 회원 검색 성공!");
				for(int i=0; i<members.size(); i++){
					logger.info((i+1)+" - " + members.get(i).toString());
				}
				return members;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param SerachInfo 닉네임으로 회원을 검색하기위한 keyword와 조건, page정보들을 담고있습니다.
	 * @return List<MemberVO> 검색된 회원의 객체를 List로 반환합니다.
	 * @throws Exception 미정
	 */
	public List<MemberVO> getMembersByNickName(SearchInfo searchInfo) throws Exception {
		if(searchInfo == null){
			return null;
		}
		String keyword = searchInfo.getKeyword();
		String searchOption = searchInfo.getSearchOption();
		String conjunction = searchInfo.getConjunction();
		boolean isAnd = false;
		if(conjunction.equals("AND")){
			isAnd = true;
		}
		OptionIntent.Builder builder = new OptionIntent.Builder();
		OptionIntent optionIntent= builder.build();
		
		//검색
		if(searchOption.equals("IN")){
			//닉네임 검색인데 LIKE면 될거같은데?
		}else if(searchOption.equals("LIKE")){
			builder.appendLikeSearchOption(MemberUnit.NICKNAME, keyword, isAnd);
		}else{
			
		}
		
		//정렬
		builder.appendSortingOption(MemberUnit.valueOf(searchInfo.getOrderRule()), searchInfo.getSort());
		
		//페이징
		int offset = (searchInfo.getCurrentPageNumber()-1) * searchInfo.getPerPageCount();
		int size = searchInfo.getPerPageCount();
		builder.setOffset(offset);
		builder.setSize(size);
		int maxCount = memberMapper.getMemberSize(optionIntent);
		searchInfo.setMaxCount(maxCount);
		
		List<MemberVO> searchMembers = memberMapper.searchMembers(optionIntent);
		
		return searchMembers;
	}

	/**
	 * 
	 * @param SerachInfo 이메일로 회원을 검색하기위한 keyword와 조건, page정보들을 담고있습니다.
	 * @return List<MemberVO> 검색된 회원의 객체를 List로 반환합니다.
	 * @throws Exception 미정
	 */
	public List<MemberVO> getMembersByEmail(SearchInfo searchInfo) throws Exception {
		if(searchInfo == null){
			return null;
		}
		String keyword = searchInfo.getKeyword();
		String searchOption = searchInfo.getSearchOption();
		String conjunction = searchInfo.getConjunction();
		boolean isAnd = false;
		if(conjunction.equals("AND")){
			isAnd = true;
		}
		OptionIntent.Builder builder = new OptionIntent.Builder();
		OptionIntent optionIntent= builder.build();
		
		//검색
		if(searchOption.equals("LIKE")){
			builder.appendLikeSearchOption(MemberUnit.EMAIL, keyword, isAnd);
		}else if(searchOption.equals("EQUAL")){
			builder.appendEqualSearchOption(MemberUnit.EMAIL, keyword, isAnd);
		}
		
		//정렬
		builder.appendSortingOption(MemberUnit.valueOf(searchInfo.getOrderRule()), searchInfo.getSort());
		
		//페이징
		int offset = (searchInfo.getCurrentPageNumber()-1) * searchInfo.getPerPageCount();
		int size = searchInfo.getPerPageCount();
		builder.setOffset(offset);
		builder.setSize(size);
		int maxCount = memberMapper.getMemberSize(optionIntent);
		searchInfo.setMaxCount(maxCount);
		
		List<MemberVO> searchMembers = memberMapper.searchMembers(optionIntent);
		
		return searchMembers;
	}

	public List<MemberVO> getMembers(List<String> memberIds) throws Exception {
		OptionIntent.Builder builder = new OptionIntent.Builder();
		builder.appendInSearchOption(MemberUnit.ID, memberIds.toArray(), true);
		List<MemberVO> members = memberMapper.searchMembers(builder.build());
		return members;
	}

}
