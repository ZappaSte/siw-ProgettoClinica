package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import it.uniroma3.siw.model.Risultato;

public interface RisultatoRepository extends CrudRepository<Risultato, Long>{
	
	public Optional<Risultato> findById(Long id);
	
}
