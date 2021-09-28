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
	
	@Transactional
	public Esame inserisci(Esame esame) {
		return esameRepository.save(esame);
	}
	
	public void save(Esame esame) {
		esameRepository.save(esame);
	}
	
	@Transactional
	public void update(Esame esame) {
		esameRepository.updateEsame(esame.isInserimento(), esame.getId());
	}
	
	public Object findAll() {
		return esameRepository.findAll();
	}
	
}
