package edu.mum.coffee.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.ProductType;
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
	public String getAllProducts(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("productTypes", ProductType.values());
		return "orderList";

	}

	@RequestMapping(value = "/productDetails/{id}", method = RequestMethod.GET)
	public String getProduct(@PathVariable int id, Model model) {
		System.out.println(id);
		Orderline orderLine = new Orderline();
		orderLine.setProduct(productService.getProduct(id));
		System.out.println(orderLine.getProduct().getProductName());
		model.addAttribute("orderline", orderLine);
		return "customerOrderProduct";
	}

	@RequestMapping(value = "/orderDetails")
	public String checkOrder(HttpSession session, Model model) {
		Object orderObj = session.getAttribute("shoppingcart");
		if (orderObj == null) {
			return "redirect:/orders";
		}
		Order order = (Order) orderObj;
		model.addAttribute("order", order);
		return "orderDetails";
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public String addOrderline(HttpSession session, @ModelAttribute("orderline") Orderline orderline,
			RedirectAttributes redirectAttr) {
		Object orderObj = session.getAttribute("shoppingcart");
		if (orderObj == null) {
			orderObj = new Order();
			session.setAttribute("shoppingcart", orderObj);
		}
		Order order = (Order) orderObj;
		System.out.println(">>>>>>>>>>"+orderline.getProduct());
		orderline.setOrder(order);
		order.addOrderLine(orderline);
		redirectAttr.addFlashAttribute("message", "You have " + order.getOrderLines().size() + " item(s)");
		return "redirect:/orders";

	}
	
	@RequestMapping(value = "/payOrder")
	public String validateOrder(HttpSession session, Model model, RedirectAttributes redirectAttr,
			Authentication authentication) {
		Object orderObj = session.getAttribute("shoppingcart");
		Order order = (Order) orderObj;
		System.out.println(order.getOrderLines().size());
		order.setOrderDate(new Date());
		Person person = personService.findById(new Long(2));
		order.setPerson(person);
		orderService.save(order);
		String message = "Payment successfully made. Details: " + order.getQuantity()
				+ " / Total amount: $" + order.getTotalAmount();
		session.removeAttribute("shoppingcart");
		redirectAttr.addFlashAttribute("message", message);
		return "redirect:/orders";
	}
	
}