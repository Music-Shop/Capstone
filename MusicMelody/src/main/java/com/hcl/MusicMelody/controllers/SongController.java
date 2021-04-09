package com.hcl.MusicMelody.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.services.SongService;

@Controller
public class SongController {

//     @PostMapping("")
//     public ModelAndView s
	@Autowired
    SongService songService;
	
//	@GetMapping
//    public ResponseEntity<List<Song>> getAllSongs(
//                        @RequestParam(defaultValue = "0") Integer pageNo, 
//                        @RequestParam(defaultValue = "10") Integer pageSize,
//                        @RequestParam(defaultValue = "id") String sortBy) 
//    {
//        List<Song> list = service.getPaging(pageNo, pageSize, sortBy);
// 
//        return new ResponseEntity<List<Song>>(list, new HttpHeaders(), HttpStatus.OK); 
//    }
	
	//add page size
//	@GetMapping("page/{pageNo}")
//	public ModelAndView findPaginated(@PathVariable (value = "pageNo") int pageNo) {
//		ModelAndView modelAndView = new ModelAndView();
//		
//		int pageSize = 5;
//		
//		Page<Song> page = service.findPaginated(pageNo, pageSize);
//		List<Song> listSongs = page.getContent();
//		
//		modelAndView.addObject("currnentPage", pageNo);
//		modelAndView.addObject("totalPages", page.getTotalPages());
//		modelAndView.addObject("totalItems", page.getTotalElements());
//		modelAndView.addObject("listSongs", listSongs);
//		
//		modelAndView.setViewName("user/home");
//		
//		return modelAndView;
//
//	}
	
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
