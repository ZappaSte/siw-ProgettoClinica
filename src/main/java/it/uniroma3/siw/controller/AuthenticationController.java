package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.CredentialsService;

@Controller
@SessionAttributes("accountCorrente")
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
		
	@RequestMapping(value = { "/", "/index" , "/index/**"}, method = RequestMethod.GET)
    public String index(Model model) {
			model.addAttribute("accountCorrente", model.getAttribute("accountCorrente"));
			return "index";
		
    }
	
	@RequestMapping(value = { "/pazienteOpzioni"}, method = RequestMethod.GET)
    public String pazienteOpzioni(Model model) {
			return "pazienteOpzioni";
		
    }
	
	@RequestMapping(value = { "/amministrazioneOpzioni"}, method = RequestMethod.GET)
    public String amministrazioneOpzioni(Model model) {
			return "amministrazioneOpzioni";
		
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "loginForm";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultAfterLogin(Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("accountCorrente", credentials);
		}
		else if(credentials.getRole().equals(Credentials.PAZIENTE_ROLE)) {
			model.addAttribute("accountCorrente", credentials);
		}
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "amministrazioneOpzioni";
		}
		else if (credentials.getRole().equals(Credentials.PAZIENTE_ROLE)) {
			return "pazienteOpzioni";
		}
		return "index";
	}
}
