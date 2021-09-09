package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Medico;

public interface MedicoRepository extends CrudRepository<Medico, Long> {
	
	public List<Medico> findByNomeOrCognome(String nome, String cognome);

}
