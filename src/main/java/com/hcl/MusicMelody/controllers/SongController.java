package com.hcl.MusicMelody.controllers;

import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.services.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SongController {

	@Autowired
    SongService songService;
	
	@GetMapping("/song")
	public ModelAndView showSong(@RequestParam int songId) {
		return findSong(songId);
	}

	@GetMapping("/song/{sId}")
	public ModelAndView findSong(@PathVariable (value = "sId") int sId) {
		System.out.println("======================= " + sId + " =======================");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/song");
		Song song = songService.getSongById(sId).get();
		modelAndView.addObject("song", song);

		return modelAndView;
	}
}
