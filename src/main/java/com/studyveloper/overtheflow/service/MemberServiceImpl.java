package com.studyveloper.overtheflow.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.exception.MemberException;
import com.studyveloper.overtheflow.mapper.MemberMapper;
import com.studyveloper.overtheflow.util.IdentifierGenerator;
import com.studyveloper.overtheflow.util.PageInfo;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.util.option.MemberUnit;
import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	
	public MemberVO register(MemberVO memberVO) throws Exception {
		boolean idCheck = false;
		if (memberVO == null) {
			logger.error("회원가입 실패! - 회원가입을 위한 MemberVO가 NULL입니다.");
			throw new MemberException("회원가입 실패! - 회원가입을 위한 MemberVO가 NULL입니다.",MemberException.ErrorCode.NULLPARAMETER);
		} else if (memberVO.getEmail() == null || memberVO.getEmail().trim().equals("")) {
			logger.error("회원가입 실패! - 회원가입을 위한 email이 NULL입니다.");
			throw new MemberException("회원가입 실패! - 회원가입을 위한 email이 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		} else if (memberVO.getPassword() == null || memberVO.getPassword().trim().equals("")) {
			logger.error("회원가입 실패! - 회원가입을 위한 password가 NULL입니다.");
			throw new MemberException("회원가입 실패! - 회원가입을 위한 password가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		} else if (memberVO.getNickname() == null || memberVO.getNickname().trim().equals("")) {
			logger.error("회원가입 실패! - 회원가입을 위한 nickname이 NULL입니다.");
			throw new MemberException("회원가입 실패! - 회원가입을 위한 nickname이 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}
		else {
			SearchInfo searchInfo = new SearchInfo();
			searchInfo.setConjunction("AND");
			searchInfo.setCurrentPageNumber(1);
			searchInfo.setOrdering(false);
			searchInfo.setPerPageCount(1);
			searchInfo.setSearchOption("EQUAL");
			searchInfo.setSortionOption("EMAIL");
			searchInfo.setKeyword(memberVO.getEmail());
			List<MemberVO> list = this.getMembersByEmail(searchInfo);
			if(list.size() >0){
				throw new MemberException("email이 중복되었습니다.", MemberException.ErrorCode.EXISTS);
			}
			searchInfo.setKeyword(memberVO.getNickname());
			list = this.getMembersByNickName(searchInfo);
			if(list.size() >0){
				throw new MemberException("nickname이 중복되었습니다.", MemberException.ErrorCode.EXISTS);
			}
			
			String id = IdentifierGenerator.generateId("" + memberVO.getEmail());
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

	
	public Boolean unRegister(String memberId, String password) throws Exception {
		boolean result = false;
		if(memberId == null || memberId.trim().equals("")){
			logger.error("회원 탈퇴 실패! - 회원 식별키가 NULL입니다.");
			throw new MemberException("회원 탈퇴 실패! - 회원 식별키가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}else if(password == null || password.trim().equals("")){
			logger.error("회원 탈퇴 실패! - 비밀번호가 NULL입니다.");
			throw new MemberException("회원 탈퇴 실패! - 비밀번호가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}
		MemberVO memberVO = memberMapper.searchMember(memberId);
		if (memberVO == null) {
			logger.error("회원 탈퇴 실패! - 회원 식별키와 일치하는 회원이 없습니다.");
			throw new MemberException("회원 탈퇴 실패! - 회원 식별키와 일치하는 회원이 없습니다.", MemberException.ErrorCode.NOT_EXISTS);
		} else {
			if (memberVO.getPassword().equals(password)) {
				memberMapper.deleteMember(memberId);
				result = true;
			} else {
				logger.error("회원 탈퇴 실패! - 패스워드가 일치하지 않습니다.");
				throw new MemberException("회원 탈퇴 실패! - 패스워드가 일치하지 않습니다.", MemberException.ErrorCode.NOT_EQAUL_PASSWORD);
			}
		}

		return result;
	}

	
	public MemberVO modifyMember(MemberVO memberVO, String oldPassword) throws Exception {
		if (memberVO == null) {
			logger.error("회원정보 수정 실패! - 회원정보를 수정하기위한 MemberVO가 NULL입니다.");
			throw new MemberException("회원정보 수정 실패! - 회원정보를 수정하기위한 MemberVO가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		} else if (memberVO.getEmail() == null || memberVO.getEmail().trim().equals("")) {
			logger.error("회원정보 수정 실패! - 회원 정보를 수정하기 위한 email이 NULL입니다.");
			throw new MemberException("회원정보 수정 실패! - 회원 정보를 수정하기 위한 email이 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		} else if (memberVO.getPassword() == null || memberVO.getPassword().trim().equals("")) {
			logger.error("회원정보 수정 실패! - 회원 정보를 수정하기 위한 password가 NULL입니다.");
			throw new MemberException("",MemberException.ErrorCode.NULLPARAMETER);
		} else if (memberVO.getNickname() == null || memberVO.getNickname().trim().equals("")) {
			logger.error("회원정보 수정 실패! - 회원 정보를 수정하기 위한 nickname이 NULL입니다.");
			throw new MemberException("회원정보 수정 실패! - 회원 정보를 수정하기 위한 nickname이 NULL입니다.",MemberException.ErrorCode.NULLPARAMETER);
		}else if(oldPassword == null || oldPassword.trim().equals("")){
			logger.error("회원정보 수정 실패! - 회원 정보를 수정하기 위한 oldPassword가 NULL입니다.");
			throw new MemberException("회원정보 수정 실패! - 회원 정보를 수정하기 위한 oldPassword가 NULL입니다.",MemberException.ErrorCode.NULLPARAMETER);
		}
		MemberVO memberVO2 = memberMapper.searchMember(memberVO.getId());
		if (memberVO2 == null) {
			logger.error("회원정보 수정 실패! - 회원 식별키와 일치하는 회원정보가 없습니다.");
			throw new MemberException("회원정보 수정 실패! - 회원 식별키와 일치하는 회원정보가 없습니다.", MemberException.ErrorCode.NOT_EXISTS);
		} else {
			if (memberVO2.getPassword().equals(oldPassword)) {
				memberMapper.modifyMember(memberVO);
				logger.info("회원정보 수정 성공!");
				return memberVO;
			} else {
				logger.error("회원정보 수정 실패! - 비밀번호가 일치하지 않습니다.");
				throw new MemberException("회원정보 수정 실패! - 비밀번호가 일치하지 않습니다.", MemberException.ErrorCode.NOT_EQAUL_PASSWORD);
			}
		}
	}

	
	public MemberVO getMember(String memberId)throws Exception {
		if(memberId == null || memberId.trim().equals("")){
			logger.error("회원 검색 실패! - 회원 검색을 위한 memberId가 NULL입니다.");
			throw new MemberException("회원 검색 실패! - 회원 검색을 위한 memberId가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}
		
		MemberVO memberVO = memberMapper.searchMember(memberId);
		
		if(memberVO != null){
			logger.info("회원 검색 성공! - " + memberVO.toString());
		}else{
			logger.error("회원 검색 실패! - memberId와 일치하는 회원의 정보가 없습니다.");
			throw new MemberException("회원 검색 실패! - memberId와 일치하는 회원의 정보가 없습니다.", MemberException.ErrorCode.NOT_EXISTS);
		}
		
		return memberVO;
	}

	
	public List<MemberVO> getAllMembers(PageInfo pageInfo)throws Exception {
		if(pageInfo == null){
			throw new MemberException("PageInfo가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
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
			builder.appendSortingOption(MemberUnit.valueOf(pageInfo.getSortionOption()), pageInfo.getOrdering());
			
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

	
	public List<MemberVO> getMembersByNickName(SearchInfo searchInfo) throws Exception {
		if(searchInfo == null){
			throw new MemberException("SearchInfo가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}else if(searchInfo.getKeyword() == null){
			throw new MemberException("keyword가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
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
		}else if(searchOption.equals("LIKE")){
			builder.appendLikeSearchOption(MemberUnit.NICKNAME, keyword, isAnd);
		}else{
			builder.appendEqualSearchOption(MemberUnit.NICKNAME, keyword, isAnd);
		}
		
		//정렬
		builder.appendSortingOption(MemberUnit.valueOf(searchInfo.getSortionOption()), searchInfo.getOrdering());
		
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

	
	public List<MemberVO> getMembersByEmail(SearchInfo searchInfo) throws Exception {
		if(searchInfo == null){
			throw new MemberException("SearchInfo가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}else if(searchInfo.getKeyword() == null){
			throw new MemberException("keyword가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
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
		builder.appendSortingOption(MemberUnit.valueOf(searchInfo.getSortionOption()), searchInfo.getOrdering());
		
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
		if(memberIds == null){
			throw new MemberException("memberIds가 NULL입니다.", MemberException.ErrorCode.NULLPARAMETER);
		}
		OptionIntent.Builder builder = new OptionIntent.Builder();
		builder.appendInSearchOption(MemberUnit.ID, memberIds.toArray(), true);
		List<MemberVO> members = memberMapper.searchMembers(builder.build());
		return members;
	}

}
