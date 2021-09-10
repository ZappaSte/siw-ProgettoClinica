package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Paziente;
import it.uniroma3.siw.service.PazienteService;

@Component
public class PazienteValidator implements Validator{
	
	@Autowired
	private PazienteService pazienteService;
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Paziente.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome","required");
		if(this.pazienteService.alreadyExists((Paziente)target)){
			errors.reject("duplicato");
		}
		
	}

}
