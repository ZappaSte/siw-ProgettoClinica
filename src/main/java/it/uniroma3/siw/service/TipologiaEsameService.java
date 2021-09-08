package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.TipologiaEsame;
import it.uniroma3.siw.repository.TipologiaEsameRepository;

@Service
public class TipologiaEsameService {

	@Autowired
	private TipologiaEsameRepository tipologiaEsameRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public TipologiaEsame insert(TipologiaEsame tipologiaEsame) {
		return tipologiaEsameRepository.save(tipologiaEsame);
	}
	
	@Transactional
	public List<TipologiaEsame> allTipologiaEsame() {
		return (List<TipologiaEsame>) tipologiaEsameRepository.findAll();
	}
	
	@Transactional
	public boolean alreadyExists(TipologiaEsame tipologiaEsame) {
		List<TipologiaEsame> tipologiaEsami = this.tipologiaEsameRepository.findByNome(tipologiaEsame.getNome());
		if (tipologiaEsami.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	@Transactional
		public TipologiaEsame tipologiaEsameById(Long id) {
			Optional<TipologiaEsame> optional = tipologiaEsameRepository.findById(id);
			if (optional.isPresent())
				return optional.get();
			else 
				return null;
		}
	
}
