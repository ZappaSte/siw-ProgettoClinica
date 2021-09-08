package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Requisito;

public interface RequisitoRepository extends CrudRepository<RequisitoRepository, Long>{
	
	List<Requisito> findByNome(String nome);

}
