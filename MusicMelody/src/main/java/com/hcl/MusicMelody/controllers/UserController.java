package com.hcl.MusicMelody.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.MusicMelody.models.BillingInformation;
import com.hcl.MusicMelody.models.UserCred;
import com.hcl.MusicMelody.services.BillingInformationService;
import com.hcl.MusicMelody.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BillingInformationService billingInformationService;
	
	 @GetMapping("/user/profile")
	 public ModelAndView getProfileView(Principal principal) {
		 ModelAndView modelAndView = new ModelAndView();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 System.out.println("================================== " + auth.getName() + " ==================================");
			
		 UserCred user = userService.findUserByUserName(auth.getName());
		 
		 System.out.println("================================== " + user.toString() + " ==================================");

		 modelAndView.setViewName("user/profile");
		 modelAndView.addObject("user", user);
		 
		 return modelAndView;
	 }
	 
	 //Need to add expiration date
	 @PostMapping("/user/billing")
	 public ModelAndView updateBilling(Principal principal, @RequestParam("name") String name,
										 @RequestParam("email") String email,
										 @RequestParam("cardNumber") String cardNumber,
										 @RequestParam("cvv") String cvv,
										 @RequestParam("phone") String phone,
										 @RequestParam("street") String street,
										 @RequestParam("apt") String apt,
										 @RequestParam("city") String city,
										 @RequestParam("state") String state,
										 @RequestParam("zip") String zip,
										 @RequestParam("expDate")  @DateTimeFormat(pattern="yyyy-MM-dd") Date expDate							 
			 ) {
		 ModelAndView modelAndView = new ModelAndView();
		 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		 UserCred user = userService.findUserByUserName(auth.getName());
		 System.out.println("===============================" + user.toString());
		 BillingInformation userBilling = user.getBilling(); 
		 if(userBilling != null) {
			 //Update User billing existing information
			 BillingInformation billingExists = billingInformationService.getBillingInformationById(userBilling.getId()).get();
			 billingExists.setCardNumber(cardNumber);
			 billingExists.setCvv(cvv);
//			 billingInformation.setExpDate(state);
			 billingExists.setStreet(street);
			 billingExists.setCity(city);
			 billingExists.setApt(apt);
			 billingExists.setZip(zip);
			 billingExists.setState(state);
			 billingExists.setExpDate(expDate);
			 
			 billingInformationService.saveOrUpdate(billingExists);
			 
		 }else {
			 //create a new billing information
			 BillingInformation billingInformation = new BillingInformation();
			 billingInformation.setCardNumber(cardNumber);
			 billingInformation.setCvv(cvv);
//			 billingInformation.setExpDate(state);
			 billingInformation.setCity(city);
			 billingInformation.setApt(apt);
			 billingInformation.setZip(zip);
			 billingInformation.setState(state);
			 billingInformation.setExpDate(expDate);
			 billingInformation.setStreet(street);

			 BillingInformation newBilling = billingInformationService.saveOrUpdate(billingInformation);
			 
			 user.setBilling(newBilling);
			 
			 //Save Billing Id reference to user
			 userService.saveUser(user);
		 }
		 
		 
		 modelAndView.addObject("user", user);
		 modelAndView.setViewName("user/profile");
		 
		 return modelAndView;
	 }
}
