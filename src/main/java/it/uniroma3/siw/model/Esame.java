package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Esame {
	
	/*DEFINIZIONE VARIABILI*/

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime dataPrenotazione;
	
	@Column(nullable = false)
	private LocalDate dataEsame;
	
	
	@ManyToOne
	private User paziente;
	
	@ManyToOne
	private Medico medico;
	
	@ManyToOne
	private TipologiaEsame tipologiaEsame;
	
	@OneToMany(mappedBy = "esame", cascade = CascadeType.ALL)
	private List<Risultato> risultati;

	public User getPaziente() {
		// TODO Auto-generated method stub
		return null;
	}

}
