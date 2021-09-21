package it.uniroma3.siw.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Risultato;
import it.uniroma3.siw.repository.RisultatoRepository;

@Service
public class RisulatoService {

	@Autowired
	private RisultatoRepository risultatoRepository;
	
	@Transactional
	public Risultato findById(Long id) {
		Optional<Risultato> optional = risultatoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public Risultato inserisci(Risultato risultato) {
		return risultatoRepository.save(risultato);
	}
	
	public void save(Risultato risultato) {
		risultatoRepository.save(risultato);
	}
	
	public Object findAll() {
		return risultatoRepository.findAll();
	}
}
