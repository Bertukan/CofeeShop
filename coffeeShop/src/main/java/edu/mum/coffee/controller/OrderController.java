package edu.mum.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService; 
@Controller
public class OrderController {
	
    private final ProductService productService;
   // private final OrderService orderService;
   // private final PersonService personService;
    
    @Autowired
    public OrderController(ProductService productService, OrderService orderService, PersonService personService) {
        this.productService = productService;
      //  this.orderService = orderService;
       // this.personService = personService;
    }
   
    @GetMapping("/orders")
    public String getAllProducts(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("productTypes", ProductType.values());
		return "orderList";

	}
    
    @RequestMapping(value = "/productDetails/{id}", method=RequestMethod.GET)
	public String getProduct(@PathVariable int id, Model model) {
    	System.out.println(id);
		Orderline orderLine=new Orderline();
		orderLine.setProduct(productService.getProduct(id));
		System.out.println(orderLine.getProduct().getProductName());
		model.addAttribute("orderline", orderLine);
		return "customerOrderProduct";
	}


}