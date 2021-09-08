package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Esame;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.EsameService;
import it.uniroma3.siw.validator.EsameValidator;

public class EsameController {
	
	@Autowired
	private EsameService esameService;
	
	@Autowired
	private EsameValidator esameValidator;
	
	@RequestMapping(value="/admin/esame", method = RequestMethod.GET)
    public String addEsame(Model model) {
    	model.addAttribute("esame", new Esame());
    	model.addAttribute("paziente", this.esameService.getUserService().getAllUsers());
    	model.addAttribute("medico", this.esameService.getDoctorService().allMedici());
    	model.addAttribute("tipologiaEsame", this.esameService.getTypeOfExaminationService().allTipologiaEsame());
        return "esameForm";
    }

    @RequestMapping(value = "/esame/{id}", method = RequestMethod.GET)
    public String getEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("esame", this.esameService.esameById(id));
    	model.addAttribute("role", this.esameService.getCredentialsService().getRoleAuthenticated());

    	return "esame";
    }

    @RequestMapping(value = "/admin/modEsame/{id}", method = RequestMethod.GET)
    public String modExam(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("esame", this.esameService.esameById(id));
    	model.addAttribute("role", this.esameService.getCredentialsService().getRoleAuthenticated());

    	return "esameFormMod";
    }
    @RequestMapping(value ="/admin/esameUpdate")
    public String updateEsame(@ModelAttribute("esame") Esame esame,
    		Model model, BindingResult bindingResult){
    	this.esameService.insert(esame);
    	
    	return "esami";
    	

}
  /*  @RequestMapping(value = "/exam", method = RequestMethod.GET)
    public String getExams(Model model) {
    		model.addAttribute("exams", this.examService.allExams());
    		return "exams";
    }*/
    @RequestMapping(value = "/exam", method = RequestMethod.GET)
    public String getExamsByPatient(@ModelAttribute("patient") User paziente,Model model) {
    		model.addAttribute("exams", this.esameService.esameByPaziente(paziente));
    		return "exams";
    }
    
    @RequestMapping(value = "/admin/exam", method = RequestMethod.POST)
    public String newExam(@ModelAttribute("exam") Esame esame, 
    									Model model, BindingResult bindingResult) {
    	this.esameValidator.validate(esame, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.esameService.insert(esame);
            model.addAttribute("esami", this.esameService.allExams());
            return "esami";
        }
        return "esamiForm";
    }

}
