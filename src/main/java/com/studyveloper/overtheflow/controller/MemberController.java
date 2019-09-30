package com.studyveloper.overtheflow.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studyveloper.overtheflow.bean.MemberBean;
import com.studyveloper.overtheflow.exception.MemberException;
import com.studyveloper.overtheflow.service.FollowService;
import com.studyveloper.overtheflow.service.MemberService;
import com.studyveloper.overtheflow.service.MusicLikeService;
import com.studyveloper.overtheflow.service.PlaylistLikeService;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.FollowVO;
import com.studyveloper.overtheflow.vo.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	@Autowired
	FollowService followService;
	@Autowired
	MusicLikeService musicLikeService;
	@Autowired
	PlaylistLikeService playlistLikeService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
		logger.info("메인 페이지로 이동");
		return "home";
	}
	
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
		searchInfo.setPerPageCount(10);
		searchInfo.setMaxCount(1);
		try{
			List<MemberVO> member = memberService.getMembersByEmail(searchInfo);
			
			
			if(session.getAttribute("loginId") != null){
				logger.error("이미 로그인되어 있습니다.");
			}else if(member.size() == 0){
				logger.error("로그인 실패");
				logger.error("입력한 이메일과 일치하는 회원이 없습니다.");
			}else if(!member.get(0).getPassword().equals(memberBean.getPassword())){
				logger.error("로그인 실패");
				logger.error("비밀번호가 일치하지 않습니다.");
			}
			else{
				session.setAttribute("loginId", member.get(0).getId());
				searchInfo.setKeyword(member.get(0).getId());
				Map<String, MemberVO> followingList = new HashMap<String, MemberVO>();
				List<MemberVO> following = followService.getFollows(searchInfo);
				for(int i=0; i<following.size(); i++){
					followingList.put(following.get(i).getId(), following.get(i));
				}
				session.setAttribute("followingList", followingList);
				
				/*searchInfo.setSortionOption("ID");
				Map<String, MusicVO> likeMusicList = new HashMap<String, MusicVO>();
				List<MusicVO> musics = musicLikeService.getLikeMusics(searchInfo);
				for(int i=0; i<musics.size(); i++){
					likeMusicList.put(musics.get(i).getId(), musics.get(i));
				}
				session.setAttribute("likeMusicList", likeMusicList);
				
				Map<String, PlaylistVO> likePlaylistList = new HashMap<String, PlaylistVO>();
				List<PlaylistVO> playlists = playlistLikeService.getMyLikedPlaylists(searchInfo, (String)session.getAttribute("logindId"));
				for(int i=0; i<playlists.size(); i++){
					likePlaylistList.put(playlists.get(i).getId(), playlists.get(i));
				}
				session.setAttribute("likePlaylistList", likePlaylistList);*/
				
				logger.info(session.getAttribute("loginId")+" - 로그인 성공");
			}
		}catch(MemberException e){
			logger.error(e.getMessage());
		}catch(Exception e){
			session.removeAttribute("loginId");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "home";
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		logger.info("로그아웃 요청");
		if(session.getAttribute("loginId") == null){
			logger.info("로그인을 해야합니다.");
		}else{
			session.removeAttribute("loginId");
			logger.info("로그아웃 성공!");
		}
		return "login";
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
		try{
			memberVO.setRegisterDate(new Date());
			memberVO.setTypeId("1");
			memberVO = memberService.register(memberVO);
		}catch(MemberException e){
			logger.error(e.getMessage());
			return "error";
		}
		return "home";
	}
	
	@RequestMapping(value="/unregister", method=RequestMethod.GET)
	public String displayUnregister(){
		logger.info("회원탈퇴 화면으로 이동.");
		return "unregister";
	}
	
	@RequestMapping(value="/unregister", method=RequestMethod.POST)
	public String unregister(HttpSession session, String password, Model model) throws Exception{
		logger.info("회원탈퇴 요청");
		String memberId = (String) session.getAttribute("loginId");
		if(memberId == null){
			model.addAttribute("message", "로그인을 먼저 하셔야합니다.");
			return "error";
		}
		logger.info("탈퇴 요청 아이디 = "+memberId);
		try{
			boolean result = memberService.unRegister(memberId, password);
			if(result){
				logger.info("회원탈퇴 성공");
			}else{
				logger.info("회원 탈퇴 실패.");
				return "error";
			}
		}catch(MemberException e){
			logger.error(e.getMessage());
		}
		
		return "home";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String displayModify(HttpSession session, Model model) throws Exception{
		logger.info("회원정보 수정 페이지로 이동.");
		if(session.getAttribute("loginId") == null){
			logger.info("로그인을 안했습니다.");
			model.addAttribute("message", "로그인을 먼저 해주세요.");
			return "error";
		}else{
			try{
				MemberVO memberVO = memberService.getMember((String)session.getAttribute("loginId"));
				MemberBean memberBean = new MemberBean(memberVO);
				model.addAttribute("memberBean", memberBean);
				
			}catch(MemberException e){
				logger.error(e.getMessage());
			}
			return "modify";
		}
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(HttpSession session, MemberBean memberBean, String oldPassword) throws Exception{
		logger.info("회원정보 수정 페이지로 요청");
		String id = (String)session.getAttribute("loginId");
		if(id == null){
			logger.info("로그인을 안했습니다.");
			return "error";
		}else{
			
			MemberVO memberVO = memberBean.toVO();
			memberVO.setId(id);
			memberVO = memberService.modifyMember(memberVO, oldPassword);
			if(memberVO == null){
				logger.info("회원정보 수정 실패");
				return "error";
			}else{
				logger.info("회원정보 수정 성공");
			}
			return "modify";
		}
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
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String displayMembers(HttpSession session, SearchInfo searchInfo, Model model) throws Exception{
		//전체 조회 관리자용? 권한 조회를 해야하나
		List<MemberVO> memberVOs = memberService.getAllMembers(searchInfo);
		List<MemberBean> memberBeans = new ArrayList<MemberBean>();
		
		for(int i=0; i<memberVOs.size(); i++){
			memberBeans.add(new MemberBean(memberVOs.get(i)));
			logger.info(memberVOs.get(i).toString());
		}
		model.addAttribute("members", memberBeans);
		return "members";
	}
	
	@RequestMapping(value="/search/member/{searchType}", method=RequestMethod.GET)
	public String searchMembers(HttpSession session,@PathVariable("searchType")String searchType, SearchInfo searchInfo, Model model) throws Exception{
		logger.info("welcome searchmember");
		String searchOption = searchInfo.getSearchOption();
		List<MemberVO> memberVOs = new ArrayList<MemberVO>();
		List<MemberBean> members = new ArrayList<MemberBean>();
		System.out.println(searchInfo.getKeyword());
		if(searchType.equals("NICKNAME")){
			memberVOs = memberService.getMembersByNickName(searchInfo);
		}else if(searchType.equals("EMAIL")){
			memberVOs = memberService.getMembersByEmail(searchInfo);
		}
		MemberBean memberBean = null;
		Map<String, MemberVO> followingList = (Map<String, MemberVO>) session.getAttribute("followingList");
		
		for(int i=0; i<memberVOs.size(); i++){
			memberBean = new MemberBean(memberVOs.get(i));
			if(followingList.containsKey(memberBean.getId())){
				memberBean.setFollow(true);
			}
			members.add(memberBean);
		}
		model.addAttribute("members", members);
		return "members";
	}
	
	@RequestMapping(value="/follow")
	public String toggleFollow(HttpSession session, String memberId, boolean isFollowed, Model model)throws Exception{
		FollowVO followVO = new FollowVO();
		followVO.setFollowerId((String)session.getAttribute("loginId"));
		followVO.setFollowingId(memberId);
		boolean result = false;
		if(isFollowed){
			result = followService.unFollow(followVO);
			if(result){
				((Map<String, MemberVO>) session.getAttribute("followingList")).remove(memberId);
				model.addAttribute("message","unfollow성공");
			}else{
				model.addAttribute("message","unfollow실패");
			}
		}else{
			result = followService.follow(followVO);
			if(result){
				MemberVO memberVO = memberService.getMember(memberId);
				((Map<String, MemberVO>) session.getAttribute("followingList")).put(memberId, memberVO);
				model.addAttribute("message","follow성공");
			}else{
				model.addAttribute("message","follow실패");
			}
		}
		return "follow";
	}
	
	@RequestMapping(value="/following/list")
	public String displayFollowings(HttpSession session,SearchInfo searchInfo, Model model)throws Exception{
		List<MemberVO> memberVOs = followService.getFollows(searchInfo);
		List<MemberBean> members = new ArrayList<MemberBean>();
		for(int i=0; i<memberVOs.size(); i++){
			members.add(new MemberBean(memberVOs.get(i)));
		}
		model.addAttribute("message", "followingList");
		model.addAttribute("members", members);
		return "followList";
	}
	@RequestMapping(value="/follower/list")
	public String displayFollowers(HttpSession session, Integer memberNO,SearchInfo searchInfo, Model model)throws Exception{
		List<MemberVO> memberVOs = followService.getFollowers(searchInfo);
		List<MemberBean> members = new ArrayList<MemberBean>();
		for(int i=0; i<memberVOs.size(); i++){
			members.add(new MemberBean(memberVOs.get(i)));
		}
		model.addAttribute("message", "followerList");
		model.addAttribute("members", members);
		return "followList";
	}
}