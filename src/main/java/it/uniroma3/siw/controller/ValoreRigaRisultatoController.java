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
import it.uniroma3.siw.model.Esame;
import it.uniroma3.siw.model.ValoreRigaRisultato;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.EsameService;
import it.uniroma3.siw.service.TipologiaEsameService;
import it.uniroma3.siw.service.ValoreRigaRisultatoService;

@Controller
@SessionAttributes("accountCorrente")
public class ValoreRigaRisultatoController {

	@Autowired
	private ValoreRigaRisultatoService valoreRigaRisultatoService;
	
	@Autowired
	private TipologiaEsameService tipologiaEsameService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private EsameService esameService;
	
	@RequestMapping(value = "/valoriRigheRisultati", method = RequestMethod.GET)
    public String getValoriRigheRisultati(Model model) {
		model.addAttribute("valoriRigheRisultati", this.valoreRigaRisultatoService.findAll());
    	return "valoriRigheRisultati";
    }
	
	@RequestMapping(value = "/valoreRigaRisultato/{id}", method = RequestMethod.GET)
    public String getValoreRigaRisultato(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("valoreRigaRisultato", this.valoreRigaRisultatoService.findById(id));
    	model.addAttribute("esame", this.valoreRigaRisultatoService.findById(id).getEsame());
    	model.addAttribute("rigaRisultato", this.valoreRigaRisultatoService.findById(id).getRigaRisultato());
    	return "admin/valoreRigaRisultato";
    }
	
	@RequestMapping(value = "/admin/selezionaEsame", method = RequestMethod.GET)
	public String adminSelzionaEsame(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("accountCorrente", credentials);
		}
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("esami", this.esameService.findAll());
			return "admin/selezionaEsame";
		}
		return "index";
	}
	
	@RequestMapping(value = "/admin/inserimentoValoriEsame", method = RequestMethod.GET)
	public String aggiungiValoriRigheRisultato(@Validated @ModelAttribute("esame") Esame esame, 
			@Validated @ModelAttribute("valoreRigaRisultato") ValoreRigaRisultato valoreRigaRisultato, Model model){
		
		model.addAttribute("valoreRigaRisultato", new ValoreRigaRisultato());
		Long idTipologiaEsame = esame.getTipologiaEsame().getId();
		model.addAttribute("tipologiaEsame", this.tipologiaEsameService.findById(idTipologiaEsame));
		return "admin/valoriRisultatiEsameForm";
	}
	
	
	@RequestMapping(value = "/esame/{id}/admin/valoriRisultatiEsameForm", method = RequestMethod.POST)
	public String registerValoriRigheRisultato(@PathVariable("id") Long idEsame,
			@Validated @ModelAttribute("valoreRigaRisultato") ValoreRigaRisultato valoreRigaRisultato,
			BindingResult bindingResult, Model model) throws Exception{
		Esame esameCorrente = this.esameService.findById(idEsame);
		
		if(!bindingResult.hasErrors()) {
			String stringaValori = valoreRigaRisultato.getValore();
			String[] newString = stringaValori.split(",");	
			int i = 0;
			while (i<esameCorrente.getTipologiaEsame().getRigheRisultati().size()) {
				for (String string : newString) {
					ValoreRigaRisultato nuovoValoreRigaRisultato = new ValoreRigaRisultato();
					nuovoValoreRigaRisultato.setValore(string);
					nuovoValoreRigaRisultato.setEsame(esameCorrente);
					nuovoValoreRigaRisultato.setRigaRisultato(esameCorrente.getTipologiaEsame().getRigheRisultati().get(i));
					valoreRigaRisultatoService.save(nuovoValoreRigaRisultato);
					i++;
				}
			}						
			esameCorrente.setInserimento(true);
			esameService.update(esameCorrente);
			return "redirect:/amministrazioneOpzioni";
		}		
		return "admin/valoriRisultatiEsameForm";
	}
}
