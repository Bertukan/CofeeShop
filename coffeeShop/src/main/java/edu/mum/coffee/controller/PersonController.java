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

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@Controller
public class PersonController {

	@Resource
	private PersonService personService;

	//retrieve
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String getAllPersons(Model model) {
		model.addAttribute("persons", personService.getAllPersons());
		return "personList";
	}

	
	//add
	@GetMapping("/addPerson")
	public String showaddPersonForm(@ModelAttribute("person")  Person person, Model model) {
		return "addPerson";
	}

	@PostMapping("/addPerson")
	public String savePerson(@Valid Person person, BindingResult result) {
		String view = "redirect:/persons";
		if (!result.hasErrors()) {
			personService.savePerson(person);
		}

		else {
			view = "addPerson";
		}
		return view;

	}


	@GetMapping("/persons/{id}")
	
	public String showEditForm(@PathVariable long id, Model model) {
		model.addAttribute("person", personService.findById(id));
		return "personDetail";	
	}
	
	@PostMapping("/updatePerson")
	public String updateProduct(@ModelAttribute("person") @Valid Person person, BindingResult result, Model model) {
		String view = "redirect:/persons";
		if (!result.hasErrors()) {
			personService.savePerson(person);
		}

		else {
			view = "personDetail";
			
		}
		return view;

	}

}
