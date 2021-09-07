package it.uniroma3.siw.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Esame {
	
	/*DEFINIZIONE VARIABILI*/

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime dataPrenotazione;
	
	@Column(nullable = false)
	private LocalDateTime dataEsame;

}
