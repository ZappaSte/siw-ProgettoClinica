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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Esame;
import it.uniroma3.siw.model.Risultato;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.EsameService;
import it.uniroma3.siw.service.RisulatoService;

@Controller
@SessionAttributes("accountCorrente")
public class RisultatoController {

	@Autowired
	private RisulatoService risultatoService;
	
	@Autowired
	private EsameService esameService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@RequestMapping(value = "/risultati", method = RequestMethod.GET)
    public String getRisultati(Model model) {
		model.addAttribute("risultati", this.risultatoService.findAll());
    	return "risultati";
    }
	
	@RequestMapping(value = "/risultato/{id}", method = RequestMethod.GET)
    public String getRisultato(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("risultato", this.risultatoService.findById(id));
    	model.addAttribute("esame", this.risultatoService.findById(id).getEsame());
    	return "admin/risultato";
    }
	
	@RequestMapping(value = "/admin/risultatoForm", method = RequestMethod.GET)
	public String adminAggiungeRisultatoEsame(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("accountCorrente", credentials);
		}
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("risultato", new Risultato());
			model.addAttribute("esami", this.esameService.findAll());
			return "admin/risultatoForm";
		}
		return "index";
	}

	@RequestMapping(value = { "/admin/risultatoForm" }, method = RequestMethod.POST)
	public String registerRisultatoEsame(@Validated @ModelAttribute("risultato") Risultato risultato,
			@RequestParam(value = "esame") Esame esame,
			BindingResult bindingResult,Model model) throws Exception{		
		if(!bindingResult.hasErrors()) {
			risultato.setEsame(esame);
			risultatoService.save(risultato);
			model.addAttribute("risultati", this.risultatoService.findAll());
			return "redirect:/amministrazioneOpzioni";
		}
		return "admin/risultatoForm";
	}
}
