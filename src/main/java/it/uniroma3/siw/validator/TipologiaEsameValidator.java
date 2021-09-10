package it.uniroma3.siw.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.TipologiaEsame;
import it.uniroma3.siw.service.TipologiaEsameService;

@Component
public class TipologiaEsameValidator implements Validator{

	@Autowired
	private TipologiaEsameService tipologiaEsameService;
	
    private static final Logger logger = LoggerFactory.getLogger(TipologiaEsameValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.tipologiaEsameService.alreadyExists((TipologiaEsame)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return TipologiaEsame.class.equals(aClass);
	}
	
}