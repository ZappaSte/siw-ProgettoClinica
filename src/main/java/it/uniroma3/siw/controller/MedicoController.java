package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Medico;
import it.uniroma3.siw.service.MedicoService;
import it.uniroma3.siw.validator.MedicoValidator;

@Controller
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private MedicoValidator medicoValidator;
	
	@RequestMapping(value="/admin/medico", method = RequestMethod.GET)
    public String addDoctor(Model model) {
    	model.addAttribute("medico", new Medico());
        return "medicoForm";
    }

    @RequestMapping(value = "/medico/{id}", method = RequestMethod.GET)
    public String getDoctor(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("medico", this.medicoService.medicoById(id));
    	model.addAttribute("role", this.medicoService.getCredentialsService().getRoleAuthenticated());

    	return "medico";
    }

    @RequestMapping(value = "/medico", method = RequestMethod.GET)
    public String getDoctors(Model model) {
    		model.addAttribute("medici", this.medicoService.allMedici());
        	model.addAttribute("role", this.medicoService.getCredentialsService().getRoleAuthenticated());
    		return "medici";
    }
    
    @RequestMapping(value = "/admin/medico", method = RequestMethod.POST)
    public String addDoctor(@ModelAttribute("medico") Medico medico, 
    									Model model, BindingResult bindingResult) {
    	this.medicoValidator.validate(medico, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.medicoService.insert(medico);
            model.addAttribute("medici", this.medicoService.allMedici());
            return "medici";
        }
        return "medicoForm";
    }

}
