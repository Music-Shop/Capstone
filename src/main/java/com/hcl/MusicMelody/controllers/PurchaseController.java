package com.hcl.MusicMelody.controllers;

import java.security.Principal;

import com.hcl.MusicMelody.models.ConfirmedPurchase;
import com.hcl.MusicMelody.models.Song;
import com.hcl.MusicMelody.models.UserCred;
import com.hcl.MusicMelody.services.ConfirmedPurchaseService;
import com.hcl.MusicMelody.services.SongService;
import com.hcl.MusicMelody.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PurchaseController {

    // Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;

    @Autowired
    private ConfirmedPurchaseService purchaseService;
    /**
     * ================================================ 
     * === SHOPPING CART CONTROLLERS 
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
     * === CUSTOMER DETAILS CONTROLLERS
     *  ================================================
     */

    @GetMapping("/user/home/cart/customer-details")
    public ModelAndView showCustomerDetailsPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/user/customerDetails");
        return modelAndView;
    }

    @PostMapping("/user/home/cart/customer-details/confirm")
    public ModelAndView addShippingetails(Principal principal,
    		@RequestParam String name, @RequestParam String email,
            @RequestParam String phone, @RequestParam String street, @RequestParam String apt,
            @RequestParam String city, @RequestParam String state, @RequestParam String zip
            ) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserCred user = userService.findUserByUserName(auth.getName()); 
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("========================= " + "Collected items from details: " + name + " " + email + " "
                + phone + " " + street + " " + apt + " " + city + " " + state + " " + zip);
        String address1 = street + " " + apt;
        String address2 = city + " " + state + " " + zip;

        modelAndView.addObject("name", name); 
        modelAndView.addObject("address1", address1);
        modelAndView.addObject("address2", address2);
        modelAndView.addObject("user", user);
        System.out.println("================= billing info: " + user.getBilling().getStreet());
        modelAndView.setViewName("user/orderConfirm");
        return modelAndView;
    }

    /**
     * ================================================ 
     * === ORDER CONFIRMATION CONTROLLERS 
     * ================================================
     */

    @GetMapping("/user/home/cart/customer-details/confirm")
    public ModelAndView showConfirmationPage() {
        ModelAndView modelAndView = new ModelAndView();
        // modelAndView.addObject();
        modelAndView.setViewName("/user/orderConfirm");
        return modelAndView;
    }

    /**
     * ================================================ 
     * === ORDER PLACED CONTROLLERS 
     * ================================================
     */

    @GetMapping("/user/home/cart/customer-details/confirm/placed")
    public ModelAndView showOrderPlacedPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/confirmed");
        return modelAndView;
    }
    
    @PostMapping("/user/home/cart/receipt")
    public ModelAndView placeOrder(Principal principal, @RequestParam String idLst) {
        ModelAndView modelAndView = new ModelAndView();
        
        String[] arrSong = idLst.split(",");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserCred user = userService.findUserByUserName(auth.getName());

		for(String songId: arrSong) {
        	System.out.println("=========================== IDs: " + songId);
        	Song song = songService.getSongById(Integer.parseInt(songId)).get();
        	ConfirmedPurchase confirmPurchase = new ConfirmedPurchase();
        	confirmPurchase.setSong(song);
        	confirmPurchase.setUser(user);
        	purchaseService.saveOrUpdate(confirmPurchase);
        }
        modelAndView.setViewName("/user/confirmed");
        return modelAndView;
    }

}
