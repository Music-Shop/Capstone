package com.hcl.MusicMelody.controllers;

import com.hcl.MusicMelody.services.SongService;
import com.hcl.MusicMelody.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

public class AdminController {
    
    @Autowired
	private UserService userService;

	@Autowired
	private SongService songService;

    
}
