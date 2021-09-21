package it.uniroma3.siw.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Esame;

public interface EsameRepository extends CrudRepository<Esame, Long>{
	
	public Optional<Esame> findById(Long id);
}
