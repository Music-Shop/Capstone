package com.hcl.MusicMelody.controllers;

import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import com.hcl.MusicMelody.models.Task;
import com.hcl.MusicMelody.models.UserCred;
import com.hcl.MusicMelody.services.TaskService;
import com.hcl.MusicMelody.services.UserService;
 

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping(value ="/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	
	@GetMapping(value =  "/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		
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
			
		}
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
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
		
		Iterable<Task> tasks = taskService.GetTasksByUser(user); 
		
		System.out.println("=================================== Tasks" + tasks.toString());
		modelAndView.addObject("userTasks", tasks);
		
		modelAndView.addObject("userName", user.getName());
		modelAndView.addObject("adminMessage", "Content Available only for users with admin role");
		modelAndView.setViewName("admin/home");

		return modelAndView;
	}
	
	@PostMapping(value="/admin/home")
	public ModelAndView addTask(Principal principle, @RequestParam(value="fname", required=false, defaultValue="") String fname, 
								@RequestParam(value="lname", required=false, defaultValue="") String lname, 
								@RequestParam(value="sdate")     @DateTimeFormat(pattern="yyyy-MM-dd") Date sdate, 
								@RequestParam(value="edate")     @DateTimeFormat(pattern="yyyy-MM-dd") Date edate,
								@RequestParam(value="desc") String desc,
								@RequestParam(value="sev") String sev
								
								) {
		ModelAndView modelAndView = new ModelAndView();

		UserCred user = userService.findUserByUserName(principle.getName());
		Task task = new Task(fname + " " + lname, sdate, edate, sev, desc, user);
		taskService.save(task);
		
		Iterable<Task> tasks = taskService.GetTasksByUser(user); 
		
		System.out.println("=================================== Tasks" + tasks.toString());
		modelAndView.addObject("userTasks", tasks);
		
		modelAndView.addObject("userName", user.getName());
		modelAndView.addObject("adminMessage", "Content Available only for users with admin role");
		
		modelAndView.setViewName("redirect:/admin/home");
		return modelAndView;
	}
	
	@PostMapping(value = "/admin/home/updateTask")
	public ModelAndView updateTask(Principal principle, @RequestParam(value="tId", required=true, defaultValue="") String id,
			@RequestParam(value="tName", required=false, defaultValue="") String name, 
			@RequestParam(value="tSdate")     @DateTimeFormat(pattern="yyyy-MM-dd") Date sdate, 
			@RequestParam(value="tEdate")     @DateTimeFormat(pattern="yyyy-MM-dd") Date edate,
			@RequestParam(value="tDesc") String desc,
			@RequestParam(value="tSev") String sev
			) {
		ModelAndView modelAndView = new ModelAndView();

//		UserCred user = userService.findUserByUserName(principle.getName());
		Optional<Task> updateTaskOption = taskService.GetTaskById(Integer.parseInt(id));
		Task updateTask = updateTaskOption.get();
		updateTask.setName(name);
		updateTask.setStartDate(sdate);
		updateTask.setEndDate(edate);
		updateTask.setDescription(desc);
		updateTask.setSeverity(sev);
		taskService.save(updateTask);
		
		modelAndView.setViewName("redirect:/admin/home");
		return modelAndView;
	}

	@PostMapping(value = "/admin/home/deleteTask")
	public ModelAndView deleteTask(Principal principle, @RequestParam(value="tId", required=true, defaultValue="") String id
			) {
		ModelAndView modelAndView = new ModelAndView();

//		UserCred user = userService.findUserByUserName(principle.getName());
		Optional<Task> deleteTaskOption = taskService.GetTaskById(Integer.parseInt(id));
		Task deleteTask = deleteTaskOption.get();
		taskService.delete(deleteTask);
		
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
