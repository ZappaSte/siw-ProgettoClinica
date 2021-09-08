package it.uniroma3.siw.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Risultato;
import it.uniroma3.siw.repository.RisultatoRepository;

@Service
public class RisultatoService {

	@Autowired
	private RisultatoRepository risultatoRepository;
	@Autowired
	private CredentialsService credentialsService;
	
	
	/*
	@Transactional
	public Risultato insert(Risultato risultato) {
		return risultatoRepository.save(risultato);
	}
	
	@Transactional
	public List<Risultato> allRisultati() {
		return (List<Risultato>) risultatoRepository.findAll();
	}*/
	
	@Transactional
	public boolean alreadyExists(Risultato risultato) {
		List<Risultato> results = this.risultatoRepository.findByNome(risultato.getNome());
		if (results.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
}
