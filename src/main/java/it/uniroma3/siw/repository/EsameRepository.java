package it.uniroma3.siw.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Esame;

public interface EsameRepository extends CrudRepository<Esame, Long>{
	
	public Optional<Esame> findById(Long id);
	
	@Query("UPDATE Esame SET inserimento = ?1 WHERE id = ?2")
	@Modifying
	public void updateEsame(Boolean inserimento, Long id);
}
