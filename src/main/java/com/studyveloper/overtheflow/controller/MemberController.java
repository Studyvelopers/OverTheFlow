package com.studyveloper.overtheflow.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studyveloper.overtheflow.bean.MemberBean;
import com.studyveloper.overtheflow.service.MemberService;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String displayLogin() {
		logger.info("로그인페이지로 이동");
		return "login";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, MemberBean memberBean) throws Exception{
		logger.info("로그인 요청");
		SearchInfo searchInfo = new SearchInfo(memberBean.getEmail(), "EQUAL", "AND");
		searchInfo.setOrdering(false);
		searchInfo.setSortionOption("EMAIL");
		searchInfo.setCurrentPageNumber(1);
		searchInfo.setPerPageCount(1);
		List<MemberVO> member = memberService.getMembersByEmail(searchInfo);
		
		if(member.size() == 0){
			logger.info("로그인 실패.");
			return "error";
		}else{
			logger.info("로그인 성공.");
			session.setAttribute("loginId", member.get(0).getId());
		}
		
		return "test";
	}
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout(HttpSession session) throws Exception{
		logger.info("로그아웃 요청");
		if(session.getAttribute("loginId") == null){
			logger.info("로그인을 해야합니다.");
			return "error";
		}else{
			session.removeAttribute("loginId");
			logger.info("로그아웃 성공!");
			return "login";
		}
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String displayRegister(){
		logger.info("회원가입 화면으로 이동.");
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(MemberBean memberBean)throws Exception{
		logger.info("회원가입 요청");
		if(memberBean == null ){
			logger.error("memberBean이 NULL입니다.");
		}
		MemberVO memberVO = memberBean.toVO();
		memberVO = memberService.register(memberVO);
		return "register";
	}
	
	@RequestMapping(value="/unregister", method=RequestMethod.GET)
	public String displayUnregister(){
		logger.info("회원탈퇴 화면으로 이동.");
		return "unregister";
	}
	
	@RequestMapping(value="/unregister", method=RequestMethod.POST)
	public String unregister(HttpSession session, String password) throws Exception{
		logger.info("회원탈퇴 요청");
		String memberId = (String) session.getAttribute("loginId");
		if(memberId == null){
			memberId = "";
		}
		logger.info("로그인 아이디 = "+memberId);
		
		boolean result = memberService.unRegister(memberId, password);
		if(result){
			logger.info("회원탈퇴 성공");
		}else{
			logger.info("회원 탈퇴 실패.");
			return "error";
		}
		return "test";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String displayMemberInfo(HttpSession session, Model model) throws Exception{
		logger.info("내정보 조회 요청");
		String loginId = (String) session.getAttribute("loginId");
		if(loginId == null){
			logger.info("로그인을 안했습니다.");
			return "error";
		}else{
			MemberVO memberVO = memberService.getMember(loginId);
			MemberBean memberBean = new MemberBean(memberVO);
			model.addAttribute("memberBean", memberBean);
			return "memberinfo";
		}
	}

}