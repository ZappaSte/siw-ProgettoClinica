package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import it.uniroma3.siw.model.Medico;
import it.uniroma3.siw.model.Paziente;
import it.uniroma3.siw.model.TipologiaEsame;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.EsameService;
import it.uniroma3.siw.service.MedicoService;
import it.uniroma3.siw.service.PazienteService;
import it.uniroma3.siw.service.TipologiaEsameService;
import it.uniroma3.siw.validator.EsameValidator;


@Controller
@SessionAttributes("accountCorrente")
public class EsameController {
	
	@Autowired
	private EsameService esameService;
	
	@Autowired
	private EsameValidator esameValidator;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private PazienteService pazienteService;
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private TipologiaEsameService tipologiaEsameService;
		
	@RequestMapping(value = "/esami", method = RequestMethod.GET)
    public String getEsami(Model model) {
		model.addAttribute("esami", this.esameService.findAll());
    	return "esami";
    }
	
	@RequestMapping(value = "/esame/{id}", method = RequestMethod.GET)
    public String getEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("esame", this.esameService.findById(id));
    	model.addAttribute("paziente", this.esameService.findById(id).getPaziente());
    	model.addAttribute("medico", this.esameService.findById(id).getMedico());
    	model.addAttribute("tipologiaEsame", this.esameService.findById(id).getTipologiaEsame());
    	return "admin/esame";
    }

	
	@RequestMapping(value = "/admin/esameForm", method = RequestMethod.GET)
	public String adminAggiungeEsame(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("accountCorrente", credentials);
		}
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("esame", new Esame());
			model.addAttribute("pazienti", this.pazienteService.findAll());
			model.addAttribute("medici", this.medicoService.findAll());
			model.addAttribute("tipologiaEsami", this.tipologiaEsameService.findAll());
			return "admin/esameForm";
		}
		return "index";
	}

	@RequestMapping(value = { "/admin/esameForm" }, method = RequestMethod.POST)
	public String registerEsame(@Validated @ModelAttribute("esame") Esame esame,
			@RequestParam(value = "paziente") Paziente paziente, 
			@RequestParam(value = "medico") Medico medico, 
			@RequestParam(value = "tipologiaEsame") TipologiaEsame tipologiaEsame, 
			BindingResult bindingResult,Model model) throws Exception{

		this.esameValidator.validate(esame, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			esame.setPaziente(paziente);
			esame.setMedico(medico);
			esame.setTipologiaEsame(tipologiaEsame);
			esame.setDataPrenotazione(LocalDateTime.now());
			esameService.save(esame);
			model.addAttribute("esami", this.esameService.findAll());
			return "redirect:/amministrazioneOpzioni";
		}
		return "admin/esameForm";
	}
}
