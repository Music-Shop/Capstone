package com.hcl.MusicMelody.controllers;

import java.math.BigDecimal;
import java.util.List;

import com.hcl.MusicMelody.models.Artist;
import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.services.ArtistService;
import com.hcl.MusicMelody.services.SongService;
import com.hcl.MusicMelody.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;

    @Autowired
    private ArtistService artistService;

    @GetMapping("/admin/home/song-inventory")
    public ModelAndView showSongInv() {
        ModelAndView modelAndView = new ModelAndView();
        List<Song> listSongs = songService.listAll(null);
        modelAndView.addObject("listSongs", listSongs);
        // for (Song song : listSongs) {
        //     System.out.println("======== song artist: " + song.getArtist().getFname() + " " + song.getArtist().getLname());
        // }
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

    @PostMapping("/admin/home/song-inventory/add-song")
    public ModelAndView addSong(@RequestParam String title, @RequestParam String artistFirst, @RequestParam String artistLast, @RequestParam int duration,
            @RequestParam BigDecimal cost) {
        System.out.println("=================== Collection: " + title + " " + artistFirst + " " + artistLast + " " + duration + " " + cost);
        ModelAndView modelAndView = new ModelAndView();
        if (artistService.GetArtistByFName(artistFirst) == null) {
            Artist artistNew = new Artist(artistFirst, artistLast);
            artistService.addArtist(artistNew);
        }
        Song addSong = new Song(title, songService.convertDuration(duration), cost,
                artistService.GetArtistByFName(artistFirst));
        songService.addUpdateSong(addSong);
        modelAndView.setViewName("redirect:/admin/home/song-inventory");
        return modelAndView;
    }

    @PostMapping("/admin/home/song-inventory/delete")
    public ModelAndView deleteSong(@RequestParam(name = "song-id") Integer songId) {
        System.out.println("==================== ID: " + songId);
        ModelAndView modelAndView = new ModelAndView();
        songService.deleteSongById(songId);
        modelAndView.setViewName("redirect:/admin/home/song-inventory");
        return modelAndView;
    }
}
