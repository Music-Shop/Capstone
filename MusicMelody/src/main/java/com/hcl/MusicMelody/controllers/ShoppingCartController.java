package com.hcl.MusicMelody.controllers;

import com.hcl.MusicMelody.services.ShoppingCartService;
import com.hcl.MusicMelody.services.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {

	// @Autowired
	// private ShoppingCartService shoppingCartService;
	// @Autowired
	// private SongService songService;

	// @Autowired
	// public ShoppingCartController(ShoppingCartService shoppingCartService, SongService songService) {
	// 	this.shoppingCartService = shoppingCartService;
	// 	this.songService = songService;

	// }

	// @GetMapping("/user/home/cart")
	// public ModelAndView shoppingCart() {
	// 	ModelAndView modelAndView = new ModelAndView("/shoppingCart");
	// 	modelAndView.addObject("products", shoppingCartService.getSongsInCart());
	// 	modelAndView.addObject("total", shoppingCartService.getTotal().toString());
	// 	return modelAndView;
	// }

	// @GetMapping("/shoppingCart/addSong/{title}")
	// public ModelAndView addSongToCart(@PathVariable("title") String title) {
	// 	songService.GetByTitle(title);
	// 	shoppingCartService.addSong(null); // fix
	// 	return shoppingCart();

	// }
	// // fix

	// @GetMapping("/shoppingCart/removeSong/{title}")
	// public ModelAndView removeProductFromCart(@PathVariable("title") String title) {
	// 	songService.GetByTitle(title);
	// 	shoppingCartService.removeSong(null);
	// 	return shoppingCart();
	// }

	// @GetMapping("/shoppingCart/checkout")
	// public ModelAndView checkout() {
	// 	shoppingCartService.checkout();
	// 	return shoppingCart();
	// }
}
