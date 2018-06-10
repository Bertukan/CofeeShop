package edu.mum.coffee.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.mum.coffee.api.coffeeshopRestController;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;

@Controller
public class HomeController {
	
	@Resource
	private coffeeshopRestController rest;
	
	@GetMapping({"/", "/", "/home"})
//@GetMapping({"/home"})
	public String homePage() {
		
		return "home";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
	
	@GetMapping({"/index"})
	public String consume(Model model) {
		List<Product> products = rest.getProductsByType(ProductType.BREAKFAST);
		model.addAttribute("productsByType", products);
		return "index1";
	}
}
