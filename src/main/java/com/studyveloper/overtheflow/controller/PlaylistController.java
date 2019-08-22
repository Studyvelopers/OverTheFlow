package com.studyveloper.overtheflow.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.studyveloper.overtheflow.util.PageInfo;
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
	public String createPlaylist(HttpSession session, String imageName, PlaylistBean playlistBean, Model model) {
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
		
		model.addAttribute("playlist", new PlaylistBean(playlist));
		
		return "playlist/detail/";
	}
	
	@GetMapping("/modify/{id}")
	public String displayModifyPlaylist(HttpSession session, @PathVariable("id")String playlistId, Model model) {
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
		
		model.addAttribute("playlist", new PlaylistBean(playlist));
		return "playlist/modify";
	}
	
	@PostMapping("/modify")
	public String modifyPlaylist(HttpSession session, String imageName, PlaylistBean playlistBean, Model model) {
		// 전달인자 확인
		if (session == null || imageName == null || imageName.isEmpty() || playlistBean == null) {
			return ERROR_PAGE;
		}
		
		PlaylistVO playlist = playlistBean.toVO();
		
		try {
			playlist = playlistService.modifyPlaylist(playlist);
		} catch (Exception e) {
			e.printStackTrace();
			playlist = null;
		}
		
		if (playlist == null) {
			return ERROR_PAGE;
		}
		
		model.addAttribute("playlist", new PlaylistBean(playlist));
		return "playlist/detail";
	}
	
	@PostMapping("/delete")
	public String deletePlaylist(HttpSession session, String playlistId) {
		String loginId = TEST_LOGIN_ID;
		
		boolean result = false;
		try {
			result = playlistService.deletePlaylist(playlistId, loginId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result) {
			return ERROR_PAGE;
		}
		
		return "redirect:/playlist/list";
	}
	
	@GetMapping("/detail/{id}")
	public String displayPlaylistDetial(HttpSession session, @PathVariable("id") String playlistId, Model model) {
		if (playlistId == null || playlistId.isEmpty()) {
			return ERROR_PAGE;
		}
		
		String loginId = TEST_LOGIN_ID;
		
		PlaylistVO playlist = null;
		
		try {
			playlist = playlistService.getPlaylist(playlistId, loginId);
		} catch (Exception e) {
			e.printStackTrace();
			playlist = null;
		}
		
		if (playlist == null) {
			return ERROR_PAGE;
		}
		
		model.addAttribute("playlist", new PlaylistBean(playlist));
		return "playlist/detail";
	}
	
	@GetMapping("/list")
	public String displayPlaylists(HttpSession session, PageInfo pageInfo, Model model) {
		List<PlaylistVO> playlists = null;
		try {
			playlists = playlistService.getAllPlaylists(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			playlists = null;
		}
		
		if (playlists == null) {
			return ERROR_PAGE;
		}
	
		List<PlaylistBean> playlistBeans = new ArrayList<PlaylistBean>();
		
		Iterator<PlaylistVO> iterator = playlists.iterator();
		while (iterator.hasNext()) {
			playlistBeans.add(new PlaylistBean(iterator.next()));
		}
		
		model.addAttribute("playlists", playlistBeans);
		model.addAttribute("pageInfo", pageInfo);
		
		return "playlist/list";
	}
}