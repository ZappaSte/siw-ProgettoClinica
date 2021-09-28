package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Esame;
import it.uniroma3.siw.model.RigaRisultato;
import it.uniroma3.siw.model.ValoreRigaRisultato;

public interface ValoreRigaRisultatoRepository extends CrudRepository<ValoreRigaRisultato, Long>{
	
	public Optional<ValoreRigaRisultato> findById(Long id);

	@Query("UPDATE ValoreRigaRisultato SET valore = ?1, rigaRisultato = ?2, esame = ?3 WHERE id = ?4")
	@Modifying
	public void update(String valore, RigaRisultato rigaRisultato, Esame esame, Long id);
	
}
