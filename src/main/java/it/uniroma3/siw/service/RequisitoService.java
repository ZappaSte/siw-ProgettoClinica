package it.uniroma3.siw.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Requisito;
import it.uniroma3.siw.repository.RequisitoRepository;

@Service
public class RequisitoService {

	@Autowired
	private RequisitoRepository requisitoRepository;
	
	@Transactional
	public Requisito findById(Long id) {
		Optional<Requisito> optional = requisitoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public Requisito inserisci(Requisito requisito) {
		return requisitoRepository.save(requisito);
	}
	
	public void save(Requisito requisito) {
		requisitoRepository.save(requisito);
	}
	
	public Object findAll() {
		return requisitoRepository.findAll();
	}
}
