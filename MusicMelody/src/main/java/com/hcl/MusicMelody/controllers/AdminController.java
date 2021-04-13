package com.hcl.MusicMelody.controllers;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        
        // List<Song> listSongs = songService.listAll(null); - old code
        //Added Stream to make the admin song list alphabetized - Alex G
        List<Song> song = songService.listAll(null);
        List<Song> listSongs = song.stream()
        .sorted(Comparator.comparing(Song::getTitle))
        .collect(Collectors.toList());
        
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
        
        // List<Song> listSongs = songService.listAll(keyword);- old code
        //Added Stream to make the admin searched song list alphabetized - Alex G
        
        List<Song> song = songService.listAll(keyword);
        List<Song> listSongs = song.stream()
        .sorted(Comparator.comparing(Song::getTitle))
        .collect(Collectors.toList());
        
        modelAndView.addObject("listSongs", listSongs);
        modelAndView.setViewName("admin/song-inventory");
        return modelAndView;
    }

    @PostMapping("/admin/home/song-inventory/add-song")
    public ModelAndView addSong(@RequestParam String title, 
					    		@RequestParam String artistFirst, 
					    		@RequestParam String artistLast, 
					    		@RequestParam int duration,
					            @RequestParam BigDecimal cost) {
        System.out.println("=================== Collection: " + title + " " + artistFirst + " " + artistLast + " " + duration + " " + cost);
        ModelAndView modelAndView = new ModelAndView();
        if (artistService.GetArtistByFName(artistFirst) == null) {
            Artist artistNew = new Artist(artistFirst, artistLast);
            artistService.addArtist(artistNew);
        }
        Song addSong = new Song(title, songService.convertDuration(duration), cost,
                artistService.GetArtistByFName(artistFirst).get());
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
    
    @PostMapping("/admin/home/song-inventory/update")
    public ModelAndView updateSong(@RequestParam Integer songId,
    								@RequestParam String title,
    								@RequestParam String artistFirst,
    								@RequestParam String artistLast,
    								@RequestParam BigDecimal cost,
    								@RequestParam int duration
    								) {
        
        //songService.convertDuration(duration)
        ModelAndView modelAndView = new ModelAndView();
        Song song = songService.getSongById(songId).get();

        Artist artist = new Artist();
        try{
        	artist = artistService.GetArtistByFName(artistFirst).get();
        }catch(Exception e) {
        	Set<Song> songs = new HashSet<Song>();;
        	songs.add(song);
        	artist.setSongs(songs);
            Artist newArtist = artistService.addArtist(artist);
            song.setArtist(newArtist);
        }
        
        artist.setFname(artistFirst);
        artist.setLname(artistLast);

        song.setTitle(title);
        song.setCost(cost);
        song.setDuration(songService.convertDuration(duration));
      
       songService.saveSong(song);

        modelAndView.setViewName("redirect:/admin/home/song-inventory");
        return modelAndView;
    }
}
