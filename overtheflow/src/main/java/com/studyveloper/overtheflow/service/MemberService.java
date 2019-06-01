package com.studyveloper.overtheflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studyveloper.overtheflow.bean.MemberBean;

@Service
public interface MemberService {
	public MemberBean register(MemberBean memberBean);
	
}
