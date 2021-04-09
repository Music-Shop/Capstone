
package com.hcl.MusicMelody.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.models.UserCred;
import com.hcl.MusicMelody.services.SongService;
import com.hcl.MusicMelody.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private SongService songService;
	
	@GetMapping(value ="/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	
	@GetMapping(value =  "/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		
		UserCred user = new UserCred();
		modelAndView.addObject("userCred", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping(value= "/registration")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		UserCred user = new UserCred();
		modelAndView.addObject("userCred", user);
		modelAndView.addObject("successMessage", "User Error");

		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@PostMapping(value = "/registration")
	public ModelAndView createNewUser(@Valid UserCred user, BindingResult bindingResult) {
//		In parameter it should be @Valid UserCred user,
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("============================================ Users " + user.toString());

		UserCred userExists = userService.findUserByUserName(user.getUserName());

		if(userExists != null) {
			bindingResult.rejectValue("userName", "There is already a user w/ that name. Sorry...try again");
			modelAndView.addObject("successMessage", "User Exists");

		}
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "User Error");

			modelAndView.setViewName("login");
		}else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new UserCred());
			modelAndView.setViewName("login");
		}
		
		return modelAndView;
	}
	 
	@GetMapping(value = "/admin/home")
	public ModelAndView home(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserCred user = userService.findUserByUserName(auth.getName());
		System.out.println("=================================== User for Tasks" + user.toString());
		
		
		modelAndView.addObject("userName", user.getName());
		modelAndView.addObject("adminMessage", "Content Available only for users with admin role");
		modelAndView.setViewName("admin/home");
 
		return modelAndView;
	}
	
	@PostMapping(value="/admin/home")
	public ModelAndView addTask(Principal principle) {
		ModelAndView modelAndView = new ModelAndView();

		UserCred user = userService.findUserByUserName(principle.getName());
		
		modelAndView.addObject("userName", user.getName());
		modelAndView.addObject("adminMessage", "Content Available only for users with admin role");
		
		modelAndView.setViewName("redirect:/admin/home");
		return modelAndView;
	}
	
 
	/**
	 * ================================================
	 * ===  USER CONTROLLER
	 * ================================================
	 */

	 /**
	  * Gets the model for user home and displays the page for the new user.
	  * @return - ModelAndView
	  */
	 @PostMapping("/user/home")
	 public ModelAndView addSong(Principal principle, @RequestParam String songTitle, @RequestParam String duration, @RequestParam BigDecimal cost) {
		ModelAndView modelAndView = new ModelAndView();
 
		int secs = Integer.valueOf(duration);
		String time = songService.convertDuration(secs); 
		// logger.info("==================== Collected params: " + songTitle + " " + duration + " " + cost);
		UserCred user = userService.findUserByUserName(principle.getName());
		// Song song = new Song(songTitle, time, cost);
		// songService.addUpdateSong(song);
		
		List<Song> songs = songService.getAllSongs(); 
		
		// logger.info("====================================== List contents");
		// for (Song s : songs) {
		// 	logger.info(s.toString());
		// }
		modelAndView.setViewName("redirect:/user/home");
		
		return modelAndView;
	}
	 
	 @GetMapping("/user/home")
	 public String showUserHome(Model model, Principal principle) {
//		ModelAndView modelAndView = new ModelAndView();
//		List<Song> songs = songService.getAllSongs();
//		UserCred user = userService.findUserByUserName(principle.getName());
//
//
//		logger.info("====================================== List contents");
//		modelAndView.addObject("songs", songs);
//		modelAndView.addObject("userName", user.getName());
//		// modelAndView.addObject("adminMessage", "Content Available only for users with admin role");
//		modelAndView.setViewName("user/home");

		return findPaginated(1, "title", "asc", model);
		
//		List<Song> songs = songService.GetAllSongs();
//		System.out.println("================ songs ");
//		for (Song song : songs) {
//			System.out.println("================" + song.getTitle());
//		}
//		UserCred user = userService.findUserByUserName(principle.getName());
		

	 }
	 
	 @GetMapping("/page/{pageNo}")
		public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model) {
			
			int pageSize = 5;
			
			Page<Song> page = songService.findPaginated(pageNo, pageSize, sortField, sortDir);
			List<Song> listSongs = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			model.addAttribute("listSongs", listSongs);
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			
//			modelAndView.setViewName("user/home");
			
			return "user/home";

		}   
 
}
