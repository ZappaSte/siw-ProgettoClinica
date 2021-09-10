package it.uniroma3.siw.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Esame;
import it.uniroma3.siw.repository.EsameRepository;

@Service
public class EsameService {

	@Autowired
	private EsameRepository esameRepository;
	
	@Transactional
	public Esame findById(Long id) {
		Optional<Esame> optional = esameRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	public Object findAll() {
		return esameRepository.findAll();
	}
}