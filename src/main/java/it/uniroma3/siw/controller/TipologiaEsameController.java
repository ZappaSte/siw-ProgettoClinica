package it.uniroma3.siw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.TipologiaEsame;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.TipologiaEsameService;
import it.uniroma3.siw.validator.TipologiaEsameValidator;

@Controller
@SessionAttributes("accountCorrente")
public class TipologiaEsameController {
	
	@Autowired
	private TipologiaEsameService tipologiaEsameService;
	
	@Autowired
	private TipologiaEsameValidator tipologiaEsameValidator;
	
	@Autowired
	private CredentialsService credentialsService;
	
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

    
    @RequestMapping(value = "/admin/tipologiaEsameForm", method = RequestMethod.GET)
	public String adminAggiungeTipologiaEsame(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("accountCorrente", credentials);
		}
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("tipologiaEsame", new TipologiaEsame());
			return "admin/tipologiaEsameForm";
		}
		return "tipologiaEsami";
	}

	@RequestMapping(value = { "/admin/tipologiaEsameForm" }, method = RequestMethod.POST)
	public String registerTipologiaEsame(@Validated @ModelAttribute("tipologiaEsame") TipologiaEsame tipologiaEsame,
			BindingResult bindingResult,Model model) throws Exception{

		this.tipologiaEsameValidator.validate(tipologiaEsame, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			tipologiaEsameService.save(tipologiaEsame);
			model.addAttribute("tipologiaEsami", this.tipologiaEsameService.findAll());
			return "redirect:/amministrazioneOpzioni";
		}
		return "admin/tipologiaEsameForm";
	}
    

}
