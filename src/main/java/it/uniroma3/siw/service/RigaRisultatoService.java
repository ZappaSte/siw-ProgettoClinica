package it.uniroma3.siw.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.RigaRisultato;
import it.uniroma3.siw.repository.RigaRisultatoRepository;

@Service
public class RigaRisultatoService {

	@Autowired
	private RigaRisultatoRepository rigaRisultatoRepository;
	
	@Transactional
	public RigaRisultato findById(Long id) {
		Optional<RigaRisultato> optional = rigaRisultatoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public RigaRisultato inserisci(RigaRisultato rigaRisultato) {
		return rigaRisultatoRepository.save(rigaRisultato);
	}
	
	public void save(RigaRisultato rigaRisultato) {
		rigaRisultatoRepository.save(rigaRisultato);
	}
	
	public Object findAll() {
		return rigaRisultatoRepository.findAll();
	}
}
