package edu.mum.coffee.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService; 
@Controller
public class OrderController {
	
    private final ProductService productService;
    private final OrderService orderService;
    private final PersonService personService;
    
    @Autowired
    public OrderController(ProductService productService, OrderService orderService, PersonService personService) {
        this.productService = productService;
        this.orderService = orderService;
        this.personService = personService;
    }
    
	  @ModelAttribute("products")
	    public List<Product> populateProducts() {
	        return productService.getAllProduct();
	    }
	  

}