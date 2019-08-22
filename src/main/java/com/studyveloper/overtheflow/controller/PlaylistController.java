package com.studyveloper.overtheflow.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	public String displayCreatePlaylist() {
		return "playlist/create";
	}
	
	@PostMapping
	public String createPlaylist(HttpSession session, String imageName, PlaylistBean playlistBean) {
		String testId = "1"; // test용
		String loginId = testId;
		
		PlaylistVO playlist = playlistBean.toVO();
		try {
			playlist = playlistService.createPlaylist(playlist);
		} catch (Exception e) {
			e.printStackTrace();
			playlist = null;
		}
		
		String resultPage = "playlist/error";
		
		if (playlist != null) {
			resultPage = "playlist/detail/" + playlist.getId();
		}
		
		return resultPage;
	}
}