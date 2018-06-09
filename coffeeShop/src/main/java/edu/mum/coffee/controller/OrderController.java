package edu.mum.coffee.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
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
   
    @GetMapping("/orders")
	public String getAllOrders(Model model) {
    	model.addAttribute("order", orderService.findAll());
		return "orderList";
    	
    } 
	  @GetMapping("/addOrder")
	  public String showOrderForm(Model model) {
		  Order order= new Order();
		  order.setOrderDate(new Date());
		  order.addOrderLine(new Orderline());
		  
		  model.addAttribute("order",order);
		  
		  return "addOrder";
	  }
	  
	  @PostMapping(value = "/addOrder")
	    public String addOrderLine(Order order, BindingResult result) {
	        Orderline orderLine = new Orderline();
	        order.addOrderLine(orderLine);

	        return "addOrder";
	    }


}