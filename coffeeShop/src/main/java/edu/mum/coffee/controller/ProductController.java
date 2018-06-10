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
public class ProductController {

	@Resource
	private ProductService productService;
	
	//retrieve

	@RequestMapping(value = "/products", method = RequestMethod.GET)
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
	public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {
		String view = "redirect:/products";
		if (!result.hasErrors()) {
			productService.save(product);
		}

		else {
			view = "addProduct";
			model.addAttribute("productTypes", ProductType.values());
		}
		return view;

	}
	
	//edit
	
	@GetMapping("products/{id}")

	public String getproductEditForm(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.getProduct(id));
		model.addAttribute("productTypes", ProductType.values());
		return "productDetail";

	}

	@PostMapping("/update")
	public String updateProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {
		String view = "redirect:/products";
		if (!result.hasErrors()) {
			productService.save(product);
		}

		else {
			view = "productDetail";
			model.addAttribute("productTypes", ProductType.values());
		}
		return view;

	}


	//delete

	

	@GetMapping("/product/delete/{productId}")
	public String deletePerson(Product product, @PathVariable("productId") int productId) {
		Product producttodelete = productService.getProduct(productId);
		productService.delete(producttodelete);
		return "redirect:/products";
	}

}
