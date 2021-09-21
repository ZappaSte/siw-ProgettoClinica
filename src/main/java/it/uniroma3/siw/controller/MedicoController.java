package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MedicoService;


@Controller
@SessionAttributes("accountCorrente")
public class MedicoController {
	
	
	@Autowired
	private MedicoService medicoService;	
	
	@Autowired
	private CredentialsService credentialsService;
	
	@RequestMapping(value = "/admin/medici", method = RequestMethod.GET)
    public String getMedici(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {	
    		model.addAttribute("medici", this.medicoService.findAll());
    		return "admin/medici";
		}
		return "amministrazioneOpzioni";
    		
   
    }
    	
    @RequestMapping(value = "/medico/{id}", method = RequestMethod.GET)
    public String getMedico(@PathVariable("id") Long id, Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {	
			model.addAttribute("medico", this.medicoService.findById(id));
	    	return "admin/medico";
		}
		return "admin/medici";
    }

    
    
    @RequestMapping(value = "/admin/selezionaMedico/{id}", method = RequestMethod.POST)
	public String visualizzaEsamiMedico(@PathVariable("id") Long id,Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {			
			model.addAttribute("medico", this.medicoService.findById(id));
			model.addAttribute("esame", this.medicoService.findById(id).getEsami());
			return "admin/listaEsamiMedico";
		}
		return "admin/selezionaMedico";
    }
    
}
