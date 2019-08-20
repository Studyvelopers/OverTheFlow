package com.studyveloper.overtheflow.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyveloper.overtheflow.service.MusicLikeService;
import com.studyveloper.overtheflow.service.MusicService;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.MusicVO;

@Controller
@RequestMapping("/music")
public class MusicController {
	@Autowired
	private MusicService musicService;
	@Autowired
	private MusicLikeService musicLikeService;
	
	@RequestMapping(value="/modify/{musicNo}", method=RequestMethod.GET)
	public String displayModifyMusic(HttpSession session, 
			@PathVariable("musicNo") String musicNo, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		
		MusicVO music = null;
		
		try {
			music = this.musicService.getMusic(musicNo, loginId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("music", music);
		
		return "modifymusic";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyMusic(HttpSession session, 
			MusicVO music, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		
		MusicVO result = null;
		
		try {
			result = this.musicService.modifyMusic(loginId, music);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("music", music);
		
		return "musicdetail";
	}
	
	@ResponseBody
	@RequestMapping(value="/like", method=RequestMethod.POST)
	public boolean toggleLikeMusic(HttpSession session, String musicNo, boolean isLike) {
		String loginId = (String)session.getAttribute("loginId");
		
		boolean result = false;
		
		//좋아요한 목록 비교해서 캔슬 시킬지, 좋아요 등록할 지 결정해야함 
		//목록 어디있음?
		
		return true;
	}
	
	@RequestMapping(value="/like/list/{page}", method=RequestMethod.GET)
	public String getLikeMusics(HttpSession session, SearchInfo searchInfo, 
			@PathVariable("page") int page, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		
		searchInfo.setKeyword(loginId);
		searchInfo.setCurrentPageNumber(page);
		
		List<MusicVO> result = null;
		
		try {
			result = this.musicLikeService.getLikeMusics(searchInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("musicList", result);
		model.addAttribute("page", page + 1);
		
		return "likeMusic";
	}
	
	@RequestMapping(value="/detail/{musicNo}", method=RequestMethod.GET)
	public String getMusicInfo(HttpSession session, 
			@PathVariable("musicNo") String musicNo, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		
		MusicVO result = null;
		
		try{
			result = this.musicService.getMusic(musicNo, loginId);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		model.addAttribute("music", result);
		
		return "musicdetail";
	}
	
	@RequestMapping(value="/list/{page}", method=RequestMethod.GET)
	public String displayMusics(HttpSession session,
			@PathVariable("page") int page, SearchInfo searchInfo) {
		//얘 어떻게 분기처리 할거냐;
		return null;
	}
}
