package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Esame;
import it.uniroma3.siw.model.Medico;
import it.uniroma3.siw.model.User;

public interface EsameRepository extends CrudRepository<Esame, Long>{

	public Optional<Esame> findByPaziente(User Paziente);
	
	public Optional<Esame> findByMedico(Medico Medico);
	
}
