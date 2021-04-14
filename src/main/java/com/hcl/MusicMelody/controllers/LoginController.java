package com.hcl.MusicMelody.controllers;

import javax.validation.Valid;

import com.hcl.MusicMelody.models.UserCred;
import com.hcl.MusicMelody.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();

		UserCred user = new UserCred();
		modelAndView.addObject("userCred", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping(value = "/registration")
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
		// In parameter it should be @Valid UserCred user,
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("============================================ Users " + user.toString());

		UserCred userExists = userService.findUserByUserName(user.getUserName());

		if (userExists != null) {
			bindingResult.rejectValue("userName", "There is already a user w/ that name. Sorry...try again");
			modelAndView.addObject("successMessage", "User Exists");

		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "User Error");

			modelAndView.setViewName("login");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new UserCred());
			modelAndView.setViewName("login");
		}

		return modelAndView;
	}
}
