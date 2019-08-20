package com.studyveloper.overtheflow.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studyveloper.overtheflow.bean.MemberBean;
import com.studyveloper.overtheflow.service.MemberService;
import com.studyveloper.overtheflow.vo.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String displayRegister(){
		logger.info("회원가입 화면으로 이동.");
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(MemberBean memberBean)throws Exception{
		logger.info("회원가입 화면으로 이동.");
		if(memberBean == null ){
			logger.error("memberBean이 NULL입니다.");
		}
		MemberVO memberVO = memberBean.toVO();
		memberVO = memberService.register(memberVO);
		return "register";
	}
	
	
}