package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.service.PazienteService;

@Controller
public class PazienteController {

	@Autowired
	private PazienteService pazienteService;
	
	@RequestMapping(value="/paziente/{id}", method = RequestMethod.GET)
	public String getPaziente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("paziente", this.pazienteService.findById(id));
		return "paziente.html";
	}
}
