package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Medico;
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
    
    @RequestMapping(value = "/admin/selezionaMedico", method = RequestMethod.GET)
	public String selezionaMedico(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("accountCorrente", credentials);
		}
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("medici", this.medicoService.findAll());
			return "admin/selezionaMedico";
		}
		return "index";
    }
    
    @RequestMapping(value = "/admin/visualizzaEsamiMedico", method = RequestMethod.POST)
	public String visualizzaEsamiMedico(@Validated @ModelAttribute("medico") Medico medico, Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {	
			model.addAttribute("medico", this.medicoService.findById(medico.getId()));
	    	return "admin/medico";
		}
		return "admin/selezionaMedico";    	
    }
}
