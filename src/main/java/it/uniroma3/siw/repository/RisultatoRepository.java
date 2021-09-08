package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Risultato;

public interface RisultatoRepository extends CrudRepository<RisultatoRepository, Long>{
	
	List<Risultato> findByNome(String nome);

}
