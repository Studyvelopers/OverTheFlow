package com.studyveloper.overtheflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {
	
	@GetMapping
	public String displayCreatePlaylist() {
		return "playlist/create";
	}
}