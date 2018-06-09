package edu.mum.coffee.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@Controller

public class RegisterController {
	@Resource
	private PersonService personService;

	//retrieve
	
	/*@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getAllPersons(Model model) {
		model.addAttribute("persons", personService.getAllPersons());
		return "Register";
	} */

	
	//add
	@GetMapping("/register")
	public String showaddPersonForm(@ModelAttribute("person")  Person person, Model model) {
		return "register";
	}

	@PostMapping("/signup")
	public String savePerson(@Valid Person person, BindingResult result) {
		String view = "redirect:/persons";
		if (!result.hasErrors()) {
			personService.savePerson(person);
		}

		else {
			view = "register";
		}
		return view;

	}}