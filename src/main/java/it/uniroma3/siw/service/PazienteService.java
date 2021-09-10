package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Paziente;
import it.uniroma3.siw.repository.PazienteRepository;


@Service
public class PazienteService {

	@Autowired
	private PazienteRepository pazienteRepository;
	
	@Transactional
	public Paziente inserisci(Paziente paziente) {
		return pazienteRepository.save(paziente);
	}
	
	public List<Paziente> search(String nome, String cognome){
		return pazienteRepository.findByNomeOrCognome(nome,cognome);
	}

	
	@Transactional
	public Paziente findById(Long id) {
		Optional<Paziente> optional = pazienteRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Paziente paziente) {
		List<Paziente> pazienti = this.pazienteRepository.findByNomeOrCognome(paziente.getNome(), paziente.getCognome());
		if (pazienti.size() > 0)
			return true;
		else 
			return false;
	}

	public void save(Paziente paziente) {
		pazienteRepository.save(paziente);
	}

	public Object findAll() {
		return pazienteRepository.findAll();
	}
}
