package it.uniroma3.siw.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Requisito;
import it.uniroma3.siw.repository.RequisitoRepository;

@Service
public class RequisitoService {
	
	@Autowired
	private RequisitoRepository requisitoRepository;
	
	@Autowired
	private CredentialsService credentialsService;

	
	/*
	@Transactional
	public Requisito insert(Requisito requisito) {
		return requisitoRepository.save(requisito);
	}

	@Transactional
	public List<Requisito> allRequisito() {
		return (List<Requisito>) requisitoRepository.findAll();
	}*/

	@Transactional
	public boolean alreadyExists(Requisito requisito) {
		List<Requisito> requisiti = this.requisitoRepository.findByNome(requisito.getNome());
		if (requisiti.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

}
