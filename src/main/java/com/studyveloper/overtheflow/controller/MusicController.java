package com.studyveloper.overtheflow.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studyveloper.overtheflow.service.MusicService;
import com.studyveloper.overtheflow.vo.MusicVO;

@Controller
@RequestMapping("/music")
public class MusicController {
	@Autowired
	private MusicService musicService;
	
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
}
