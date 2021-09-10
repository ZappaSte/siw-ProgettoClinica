package it.uniroma3.siw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.service.TipologiaEsameService;

@Controller
@SessionAttributes("accountCorrente")
public class TipologiaEsameController {
	
	@Autowired
	private TipologiaEsameService tipologiaEsameService;
	
	@RequestMapping(value = "/tipologiaEsami", method = RequestMethod.GET)
    public String getTipologiaEsami(Model model) {
		model.addAttribute("tipologiaEsami", this.tipologiaEsameService.findAll());
    	return "tipologiaEsami";
    }
	
    @RequestMapping(value = "/tipologiaEsame/{id}", method = RequestMethod.GET)
    public String getTipologiaEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("tipologiaEsame", this.tipologiaEsameService.findById(id));
    	return "tipologiaEsame";
    }

    

}
