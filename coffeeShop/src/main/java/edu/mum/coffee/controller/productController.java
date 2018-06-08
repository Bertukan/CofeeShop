package edu.mum.coffee.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;

@Controller
public class productController {
	
	
	
	@Resource 
	private ProductService productService;
	
	@RequestMapping(value="/products", method= RequestMethod.GET)
	
	public String getAllProducts(Model model) {
		 model.addAttribute("products", productService.getAllProduct());
		 model.addAttribute("productTypes", ProductType.values());
		  return "productList";
		
	} 
	
	// add product 
	
	@GetMapping("/add")
	public String showaddProductForm(@ModelAttribute("product") Product product, Model model) {
	     model.addAttribute("productTypes", ProductType.values());
		return "addProduct";
	}
	
	@PostMapping("/add")
	public String saveProduct(@Valid Product product, BindingResult result) {
		String view="redirect:/products";
		if(!result.hasErrors()) {
			productService.save(product);
		}
		
		else {
			view="add";
		}
		return view;
		
	}
	
	//get Product
	
	@GetMapping("products/{id}")
	
	public String getproductEditForm(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.getProduct(id));
		return "pruductDatail";
		
	}
	
	/*@PostMapping("/updateProduct")
	
	public String updateProduct(@Valid Product product, BindingResult result) {
		String view="redirect:/products";
		if(!result.hasErrors()) {
			productService.save(product);
		}
		else {
			view= "productDetail";
		}
		return view;
	} */
	
	//edit
	
	// deleting
	
	@GetMapping("delete/{id}")
	public String deleteProduct(@PathVariable int id, Product product) {
		productService.delete(product);
		return "redirect:/products";
	}
	

}
