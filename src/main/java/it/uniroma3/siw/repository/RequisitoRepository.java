package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Requisito;

public interface RequisitoRepository extends CrudRepository<Requisito, Long>{
	
	public Optional<Requisito> findById(Long id);
	
}
