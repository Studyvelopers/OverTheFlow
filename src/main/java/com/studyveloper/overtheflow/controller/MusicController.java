package com.studyveloper.overtheflow.controller;

import java.util.Date;
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
import com.studyveloper.overtheflow.vo.LikeVO;
import com.studyveloper.overtheflow.vo.MusicVO;

@Controller
@RequestMapping("/music")
public class MusicController {
	@Autowired
	private MusicService musicService;
	@Autowired
	private MusicLikeService musicLikeService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String displayCreateMusic(HttpSession session) {
	
		return "createmusic";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createMusic(HttpSession session, MusicVO music, Model model) {
		
		MusicVO result = null;
		
		System.out.println(music);
		//date 부분 musicserviceImpl에서 nullcheck 빼주고 등록시에 new Date해주어야 함
		music.setRegisterDate(new Date());
		try {
			result = this.musicService.createMusic(music);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("music", result);		
		
		return "musicdetail";
	}
	
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
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public boolean deleteMusic(HttpSession session, MusicVO music){
		String loginId = (String)session.getAttribute("loginId");
		
		if(!loginId.equals(music.getMemberId())) return false;
		
		try{
			if(this.musicService.deleteMusic(music.getId(), loginId)) return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value="/like", method=RequestMethod.POST)
	public boolean toggleLikeMusic(HttpSession session, String musicNo, boolean isLike) {
		String loginId = (String)session.getAttribute("loginId");
		
		boolean result = false;
		
		if(isLike){
			LikeVO likeVO = new LikeVO();
			likeVO.setid(musicNo);
			likeVO.setMemberId(loginId);
			try {
				if(this.musicLikeService.likeMusic(likeVO)) return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		} 
		
		LikeVO likeVO = new LikeVO();
		likeVO.setid(musicNo);
		likeVO.setMemberId(loginId);
		
		try {
			this.musicLikeService.cancelLikeMuisc(likeVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
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
		
		return "likemusic";
	}
	
	@RequestMapping(value="/detail/{musicNo}", method=RequestMethod.GET)
	public String getMusicInfo(HttpSession session, 
			@PathVariable("musicNo") String musicNo, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		
		MusicVO result = null;
		
		try{
			result = this.musicService.getMusic(musicNo, "1234");
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
