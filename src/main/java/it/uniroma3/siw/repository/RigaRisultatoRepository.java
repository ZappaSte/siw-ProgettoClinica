package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.RigaRisultato;

public interface RigaRisultatoRepository extends CrudRepository<RigaRisultato, Long>{
	
	public Optional<RigaRisultato> findById(Long id);

}
