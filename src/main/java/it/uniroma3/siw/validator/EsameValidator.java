package it.uniroma3.siw.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Esame;
@Component
public class EsameValidator implements Validator{

	private static final Logger logger = LoggerFactory.getLogger(EsameValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paziente", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medico", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Esame.class.equals(aClass);
	}
}
