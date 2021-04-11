package com.hcl.MusicMelody.controllers;

import java.util.List;

import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.services.SongService;
import com.hcl.MusicMelody.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    
    @Autowired
	private UserService userService;

	@Autowired
	private SongService songService;


    @GetMapping("/admin/home/song-inventory")
    public ModelAndView showSongInv() {
        ModelAndView modelAndView = new ModelAndView();
       List<Song> listSongs = songService.listAll(null);
        modelAndView.addObject("listSongs", listSongs);
        modelAndView.setViewName("admin/song-inventory");
        return modelAndView;
    }

    @GetMapping("/admin/home/user-management")
    public ModelAndView showUserManage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user-management");
        return modelAndView;
    }

@PostMapping("/admin/home/song-inventory")
public ModelAndView searchSongs(@RequestParam String keyword) {
    ModelAndView modelAndView = new ModelAndView();
    List<Song> listSongs = songService.listAll(keyword);
    modelAndView.addObject("listSongs", listSongs);
    modelAndView.setViewName("admin/song-inventory");
    return modelAndView;
}
}
