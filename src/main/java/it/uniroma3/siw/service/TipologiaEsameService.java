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
	
	@Transactional
	public TipologiaEsame findById(Long id) {
		Optional<TipologiaEsame> optional = tipologiaEsameRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public TipologiaEsame inserisci(TipologiaEsame tipologiaEsame) {
		return tipologiaEsameRepository.save(tipologiaEsame);
	}
	
	public void save(TipologiaEsame tipologiaEsame) {
		tipologiaEsameRepository.save(tipologiaEsame);
	}
	
	public Object findAll() {
		return tipologiaEsameRepository.findAll();
	}
	
	@Transactional
	public boolean alreadyExists(TipologiaEsame tipologiaEsame) {
		List<TipologiaEsame> tipologiaEsami = this.tipologiaEsameRepository.findByNome(tipologiaEsame.getNome());
		if (tipologiaEsami.size() > 0)
			return true;
		else 
			return false;
	}
	
}
