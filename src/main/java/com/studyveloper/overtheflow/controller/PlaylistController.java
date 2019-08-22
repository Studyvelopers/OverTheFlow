package com.studyveloper.overtheflow.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studyveloper.overtheflow.bean.PlaylistBean;
import com.studyveloper.overtheflow.service.PlaylistService;
import com.studyveloper.overtheflow.vo.PlaylistVO;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	private static final String ERROR_PAGE;
	private static final String TEST_LOGIN_ID;
	static {
		ERROR_PAGE = "playlist/error";
		TEST_LOGIN_ID = "1";
	}
	
	@GetMapping("/create")
	public String displayCreatePlaylist() {
		return "playlist/create";
	}
	
	@PostMapping("/create")
	public String createPlaylist(HttpSession session, String imageName, PlaylistBean playlistBean) {
		String loginId = TEST_LOGIN_ID;
		
		PlaylistVO playlist = playlistBean.toVO();
		try {
			playlist = playlistService.createPlaylist(playlist);
		} catch (Exception e) {
			e.printStackTrace();
			playlist = null;
		}
		
		if (playlist == null) {
			return ERROR_PAGE;
		}
		
		return "playlist/detail/" + playlist.getId();
	}
	
	@GetMapping("/modify/{id}")
	public String displayModifyPlaylist(@PathVariable("id")String playlistId, Model model) {
		if (playlistId == null || playlistId.isEmpty()) {
			return ERROR_PAGE;
		}
		
		PlaylistVO playlist = null;
		try {
			playlist = playlistService.getPlaylist(playlistId, TEST_LOGIN_ID);
		} catch(Exception e) {
			e.printStackTrace();
			playlist = null;
		}
		
		if (playlist == null) {
			return ERROR_PAGE;
		}
		
		model.addAttribute("playlist", playlist);
		return "playlist/modify/" + playlist.getId();
	}
}