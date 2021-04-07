
package com.hcl.MusicMelody.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import com.hcl.MusicMelody.models.UserCred;
import com.hcl.MusicMelody.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
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
	 @GetMapping("/user/home")
	 public ModelAndView showUserHome() {
		 return new ModelAndView("/user/home");
	 }
}
