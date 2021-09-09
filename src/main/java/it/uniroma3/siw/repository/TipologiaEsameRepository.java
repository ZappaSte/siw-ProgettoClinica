package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.TipologiaEsame;

public interface TipologiaEsameRepository extends CrudRepository<TipologiaEsame, Long>{
	
	public List<TipologiaEsame> findByNome(String nome);

}
