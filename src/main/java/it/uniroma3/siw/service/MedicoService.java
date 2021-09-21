package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Medico;
import it.uniroma3.siw.repository.MedicoRepository;

@Service
public class MedicoService {

	
	@Autowired
	private MedicoRepository medicoRepository; 
	
	@Transactional
	public Medico findById(Long id) {
		Optional<Medico> optional = medicoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public Medico inserisci(Medico medico) {
		return medicoRepository.save(medico);
	}
	
	public void save(Medico medico) {
		medicoRepository.save(medico);
	}
	
	public Object findAll() {
		return medicoRepository.findAll();
	}
	
	@Transactional
	public boolean alreadyExists(Medico medico) {
		List<Medico> medici = this.medicoRepository.findByNomeOrCognome(medico.getNome(), medico.getCognome());
		if (medici.size() > 0)
			return true;
		else 
			return false;
	}
}
