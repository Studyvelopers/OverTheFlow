package com.studyveloper.overtheflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.util.PageInfo;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.MemberVO;

@Service
public interface MemberService {
	public MemberVO register(MemberVO memberVO)throws Exception;
	public Boolean unRegister(String memberId, String password)throws Exception;
	public MemberVO modifyMember(MemberVO memberVO, String oldPassword)throws Exception;
	public MemberVO getMember(String memberId)throws Exception;
	public List<MemberVO> getAllMembers(PageInfo pageInfo)throws Exception;
	public List<MemberVO> getMembersByNickName(SearchInfo searchInfo)throws Exception;
	public List<MemberVO> getMembersByEmail(SearchInfo searchInfo)throws Exception;
	public List<MemberVO> getMembers(List<String> memberIds)throws Exception;
}
