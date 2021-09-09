package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.service.MedicoService;


@Controller
public class MedicoController {
	
	
	@Autowired
	private MedicoService medicoService;	
	
	@RequestMapping(value = "/medici", method = RequestMethod.GET)
    public String getMedici(Model model) {
    		model.addAttribute("medici", this.medicoService.findAll());
    		return "medici";
    }
    	
    @RequestMapping(value = "/medico/{id}", method = RequestMethod.GET)
    public String getMedico(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("medico", this.medicoService.findById(id));
    	return "medico";
    }

}
