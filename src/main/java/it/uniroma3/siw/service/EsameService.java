package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.model.Esame;
import it.uniroma3.siw.model.Medico;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.EsameRepository;

public class EsameService {

	@Autowired
	private EsameRepository esameRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private TipologiaEsameService tipologiaEsameService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public Esame insert(Esame esame) {
		return esameRepository.save(esame);
	}

	@Transactional
	public List<Esame> allExams() {
		return (List<Esame>) esameRepository.findAll();
	}

	@Transactional
	public Esame esameById(Long id) {
		Optional<Esame> optional = esameRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	@Transactional
	public Esame esameByPaziente(User paziente) {
		Optional<Esame> optional = esameRepository.findByPaziente(paziente);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	@Transactional
	public Esame esameByMedico(Medico medico) {
		Optional<Esame> optional = esameRepository.findByMedico(medico);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Esame esame) {
		Optional<Esame> esami = this.esameRepository.findByPaziente(esame.getPaziente());
		if (esami.isPresent())
			return true;
		else 
			return false;
	}

	public UserService getUserService() {
		return this.userService;
	}
	public MedicoService getDoctorService() {
		return this.medicoService;
	}
	public TipologiaEsameService getTypeOfExaminationService() {
		return this.tipologiaEsameService;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
	
}
