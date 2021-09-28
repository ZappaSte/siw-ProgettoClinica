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
import it.uniroma3.siw.model.RigaRisultato;
import it.uniroma3.siw.model.TipologiaEsame;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.RigaRisultatoService;
import it.uniroma3.siw.service.TipologiaEsameService;

@Controller
@SessionAttributes("accountCorrente")
public class RigaRisultatoController {

	@Autowired
	private RigaRisultatoService rigaRisultatoService;
	
	@Autowired
	private TipologiaEsameService tipologiaEsameService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@RequestMapping(value = "/righeRisultati", method = RequestMethod.GET)
    public String getRigheRisultati(Model model) {
		model.addAttribute("righeRisultati", this.rigaRisultatoService.findAll());
    	return "righeRisultati";
    }
	
	@RequestMapping(value = "/rigaRisultato/{id}", method = RequestMethod.GET)
    public String getRigaRisultato(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("rigaRisultato", this.rigaRisultatoService.findById(id));
    	model.addAttribute("tipologiaEsame", this.rigaRisultatoService.findById(id).getTipologiaEsame());
    	return "admin/rigaRisultato";
    }
	
	
	
	@RequestMapping(value = "/admin/rigaRisultatoForm", method = RequestMethod.GET)
	public String adminAggiungeRigaRisultato(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("accountCorrente", credentials);
		}
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("rigaRisultato", new RigaRisultato());
			model.addAttribute("tipologieEsami", this.tipologiaEsameService.findAll());
			return "admin/rigaRisultatoForm";
		}
		return "index";
	}

	@RequestMapping(value = { "/admin/rigaRisultatoForm" }, method = RequestMethod.POST)
	public String registerRigaRisultato(@Validated @ModelAttribute("rigaRisultato") RigaRisultato rigaRisultato,
			@RequestParam(value = "tipologiaEsame") TipologiaEsame tipologiaEsame,
			BindingResult bindingResult,Model model) throws Exception{		
		if(!bindingResult.hasErrors()) {
			rigaRisultato.setTipologiaEsame(tipologiaEsame);
			rigaRisultatoService.save(rigaRisultato);
			model.addAttribute("righeRisultati", this.rigaRisultatoService.findAll());
			return "redirect:/amministrazioneOpzioni";
		}
		return "admin/rigaRisultatoForm";
	}
}
