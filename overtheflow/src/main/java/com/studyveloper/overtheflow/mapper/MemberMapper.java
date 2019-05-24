package com.studyveloper.overtheflow.mapper;

import com.studyveloper.overtheflow.vo.MemberVO;

public interface MemberMapper {
	public void registerMember(MemberVO memberVO);
	public int unregisterMember(MemberVO memberVO);
}
