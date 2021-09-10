package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Paziente;


public interface PazienteRepository extends CrudRepository<Paziente,Long>{
	
	public List<Paziente> findByNome(String nome);
	
	public List<Paziente> findByNomeOrCognome(String nome, String cognome);
	
	public Optional<Paziente> findById(Long id);

}
