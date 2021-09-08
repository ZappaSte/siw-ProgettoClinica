package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.TipologiaEsame;
import it.uniroma3.siw.service.TipologiaEsameService;
import it.uniroma3.siw.validator.TipologiaEsameValidator;

@Controller
public class TipologiaEsameController {
	
	@Autowired
	private TipologiaEsameService tipologiaEsameService;
	
    @Autowired
    private TipologiaEsameValidator tipologiaEsameValidator;
        
    @RequestMapping(value="/admin/tipologiaEsame", method = RequestMethod.GET)
    public String addTipologiaEsame(Model model) {
    	model.addAttribute("tipologiaEsame", new TipologiaEsame());
        return "tipologiaEsameForm";
    }

   @RequestMapping(value = "/tipologiaEsame/{id}", method = RequestMethod.GET)
    public String getTipologiaEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("tipologiaEsame", this.tipologiaEsameService.tipologiaEsameById(id));
    	model.addAttribute("role", this.tipologiaEsameService.getCredentialsService().getRoleAuthenticated());
    	return "tipologiaEsame";
    }

    @RequestMapping(value = "/tipologiaEsame", method = RequestMethod.GET)
    public String getTipologiaEsame(Model model) {
    		model.addAttribute("getTtipologiaEsame", this.tipologiaEsameService.allTipologiaEsame());
        	model.addAttribute("role", this.tipologiaEsameService.getCredentialsService().getRoleAuthenticated());
    		return "tipologiaEsami";
    }
    
    @RequestMapping(value = "/admin/tipologiaEsame", method = RequestMethod.POST)
    public String newTipologiaEsame(@ModelAttribute("tipologiaEsame") TipologiaEsame tipologiaEsame, 
    									Model model, BindingResult bindingResult) {
    	this.tipologiaEsameValidator.validate(tipologiaEsame, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.tipologiaEsameService.insert(tipologiaEsame);
            model.addAttribute("tipologiaEsamI", this.tipologiaEsameService.allTipologiaEsame());
            return "tipologiaEsami";
        }
        return "tipologiaEsameS";
    }

}
