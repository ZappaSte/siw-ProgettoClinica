package it.uniroma3.siw.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.ValoreRigaRisultato;
import it.uniroma3.siw.repository.ValoreRigaRisultatoRepository;

@Service
public class ValoreRigaRisultatoService {
	
	@Autowired
	private ValoreRigaRisultatoRepository valoreRigaRisultatoRepository;
	
	@Transactional
	public ValoreRigaRisultato findById(Long id) {
		Optional<ValoreRigaRisultato> optional = valoreRigaRisultatoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public ValoreRigaRisultato inserisci(ValoreRigaRisultato valoreRigaRisultato) {
		return valoreRigaRisultatoRepository.save(valoreRigaRisultato);
	}
	
	public void save(ValoreRigaRisultato valoreRigaRisultato) {
		valoreRigaRisultatoRepository.save(valoreRigaRisultato);
	}
	
	@Transactional
	public void update(ValoreRigaRisultato valoreRigaRisultato, Long id) {
		valoreRigaRisultatoRepository.update(valoreRigaRisultato.getValore(),valoreRigaRisultato.getRigaRisultato(),valoreRigaRisultato.getEsame(),id);
	}
		
	public Object findAll() {
		return valoreRigaRisultatoRepository.findAll();
	}

}
