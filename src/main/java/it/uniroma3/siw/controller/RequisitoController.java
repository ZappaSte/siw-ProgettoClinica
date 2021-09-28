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

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Requisito;
import it.uniroma3.siw.model.TipologiaEsame;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.RequisitoService;
import it.uniroma3.siw.service.TipologiaEsameService;

@Controller
public class RequisitoController {

	@Autowired
	private RequisitoService requisitoService;
	
	@Autowired
	private TipologiaEsameService tipologiaEsameService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@RequestMapping(value = "/requisiti", method = RequestMethod.GET)
    public String getRequisiti(Model model) {
		model.addAttribute("requisiti", this.requisitoService.findAll());
    	return "requisiti";
    }
	
	@RequestMapping(value = "/requisito/{id}", method = RequestMethod.GET)
    public String getEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("requisito", this.requisitoService.findById(id));
    	model.addAttribute("tipologiaEsame", this.requisitoService.findById(id).getTipologiaEsame());
    	return "admin/requisito";
    }
	
	
	@RequestMapping(value = "/admin/requisitoForm", method = RequestMethod.GET)
	public String adminCreaRequisito(Model model) {
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				model.addAttribute("accountCorrente", credentials);
			}
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				model.addAttribute("requisito", new Requisito());
				model.addAttribute("tipologieEsami", this.tipologiaEsameService.findAll());
				return "admin/requisitoForm";
			}
			return "index";
		}

		@RequestMapping(value = { "/admin/requisitoForm" }, method = RequestMethod.POST)
		public String registerRequisito(@Validated @ModelAttribute("requisito") Requisito requisito,
				@RequestParam(value = "tipologiaEsame") TipologiaEsame tipologiaEsame, 
				BindingResult bindingResult,Model model) throws Exception{

			//this.esameValidator.validate(esame, bindingResult);
			
			if(!bindingResult.hasErrors()) {
				requisito.setTipologiaEsame(tipologiaEsame);
				requisitoService.save(requisito);
				model.addAttribute("requisiti", this.requisitoService.findAll());
				return "redirect:/amministrazioneOpzioni";
			}
			return "admin/requisitoForm";
		}
}
