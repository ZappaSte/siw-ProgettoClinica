package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.service.EsameService;


@Controller
public class EsameController {
	
	@Autowired
	private EsameService esameService;
		
	@RequestMapping(value = "/esami", method = RequestMethod.GET)
    public String getEsami(Model model) {
    		model.addAttribute("esami", this.esameService.findAll());
    		return "esami";
    }
	
	@RequestMapping(value = "/esame/{id}", method = RequestMethod.GET)
    public String getEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("esame", this.esameService.findById(id));
    	return "esame";
    }

}
