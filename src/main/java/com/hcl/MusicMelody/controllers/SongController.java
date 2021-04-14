package com.hcl.MusicMelody.controllers;

import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.models.UserCred;
import com.hcl.MusicMelody.services.SongService;
import com.hcl.MusicMelody.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class SongController {

    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;


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

    /**
     * ================================================ === USER CONTROLLER
     * ================================================
     */

    @GetMapping("/user/home/search")
    public ModelAndView showSearchResults() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/search");
        return modelAndView;
    }

    /**
     * Gets the model for user home and displays the page for the new user.
     *
     * @return - ModelAndView
     */

    @PostMapping("/user/home/search")
    public ModelAndView Search(Principal principle, @RequestParam String keyword) {
        // keyword = "Go";
        System.out.println("================== keyword: " + keyword);
        List<Song> listSongs = songService.searchSongs(keyword);
        ModelAndView modelAndView = new ModelAndView();
        UserCred user = userService.findUserByUserName(principle.getName());
        modelAndView.addObject("listSongs", listSongs);
        modelAndView.setViewName("/user/search");
        return modelAndView;
    }

    @GetMapping("/user/home")
    public String showUserHome(Model model) {

        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir, Model model) {

        int pageSize = 10;

        Page<Song> page = songService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Song> listSongs = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listSongs", listSongs);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        // modelAndView.setViewName("user/home");

        return "user/home";

    }
}
