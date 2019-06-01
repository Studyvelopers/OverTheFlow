package com.studyveloper.overtheflow.service;

import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.bean.MemberBean;

@Service
public interface MemberService {
	public MemberBean register(MemberBean memberBean);
	public boolean unRegister(MemberBean memberBean);
	public MemberBean getMember(String memberId);
}
