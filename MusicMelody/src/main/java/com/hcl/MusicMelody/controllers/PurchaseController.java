package com.hcl.MusicMelody.controllers;

import com.hcl.MusicMelody.services.SongService;
import com.hcl.MusicMelody.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PurchaseController {

    // Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private UserService userService;
 
    @Autowired
    private SongService songService;


    /**
	 * ================================================
	 * ===  SHOPPING CART CONTROLLERS
	 * ================================================
	 */

    @GetMapping("/user/home/cart")
    public ModelAndView showCart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/shoppingCart"); 
        return modelAndView;
    }

    /**
     * ================================================ 
	 * ===  CUSTOMER DETAILS CONTROLLERS
	 * ================================================
	 */

	 @GetMapping("/user/home/cart/customer-details")
	 public ModelAndView showCustomerDetailsPage() {
		 ModelAndView modelAndView = new ModelAndView();

		 modelAndView.setViewName("/user/customerDetails");
		 return modelAndView;
	 }
}
