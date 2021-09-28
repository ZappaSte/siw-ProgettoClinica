package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.time.Month;

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

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Paziente;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PazienteService;
import it.uniroma3.siw.validator.PazienteValidator;

@Controller
public class PazienteController {

	@Autowired
	private PazienteService pazienteService;
	
	@Autowired
	private PazienteValidator pazienteValidator;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@RequestMapping(value = "/pazienti", method = RequestMethod.GET)
    public String getPazienti(Model model) {
		model.addAttribute("pazienti", this.pazienteService.findAll());
    	return "pazienti";
    }
	
	@RequestMapping(value="/paziente/{id}", method = RequestMethod.GET)
	public String getPaziente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("paziente", this.pazienteService.findById(id));
		return "paziente.html";
	}
	
	
	@RequestMapping(value = "/admin/pazienteForm", method = RequestMethod.GET)
	public String adminAggiungePaziente(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("accountCorrente", credentials);
		}
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("paziente", new Paziente());
			return "admin/pazienteForm";
		}
		return "index";
	}

	public int calcolaAnno(String cF) {		
		int anno = Integer.parseInt(cF.substring(6, 8));
		if (anno > 30) 
			return (1900+anno);
		return (2000+anno);
	}
	
	public Month calcolaMese(String cF) {
		switch ((String) cF.subSequence(8, 9)) {
		case "A" : return Month.JANUARY;
		case "B" : return Month.FEBRUARY;
		case "C" : return Month.MARCH; 
		case "D" : return Month.APRIL; 
		case "E" : return Month.MAY; 
		case "H" : return Month.JUNE;
		case "L" : return Month.JULY;
		case "M" : return Month.AUGUST;
		case "P" : return Month.SEPTEMBER;
		case "R" : return Month.OCTOBER; 
		case "S" : return Month.NOVEMBER;
		case "T" : return Month.DECEMBER;
		}
		return Month.JANUARY;
	}
	
	public int calcoloGiorno(String cF) {
		int giorno = Integer.parseInt(cF.substring(9, 11));
		if(giorno > 40) {
			giorno -= 40;
			return giorno = 0 + giorno;
		}
		return giorno;
	}
	
	@RequestMapping(value = { "/admin/pazienteForm" }, method = RequestMethod.POST)
	public String registerPaziente(@Validated @ModelAttribute("paziente") Paziente paziente,
			BindingResult bindingResult,Model model) throws Exception{

		this.pazienteValidator.validate(paziente, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			Credentials c = new Credentials();
			c.setUsername(paziente.getCodiceFiscale());
			c.setPassword(paziente.getCodiceFiscale());
			c.setPaziente(paziente);
			c.setRole(Credentials.PAZIENTE_ROLE);
			String cf = paziente.getCodiceFiscale();
			LocalDate nascitaP = LocalDate.of(calcolaAnno(cf),calcolaMese(cf),calcoloGiorno(cf));
			paziente.setDataN(nascitaP);
			pazienteService.save(paziente);
			credentialsService.saveCredentials(c);
			model.addAttribute("pazienti", this.pazienteService.findAll());
			return "redirect:/amministrazioneOpzioni";
		}
		return "admin/pazienteForm";
	}
}
