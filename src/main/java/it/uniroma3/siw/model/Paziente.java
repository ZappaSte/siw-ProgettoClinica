package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Paziente {
	
	//definizione variabili

		@Id
		@GeneratedValue(strategy =GenerationType.AUTO)
		private Long id;

		@Column(nullable = false)
		private String nome;

		@Column(nullable = false)
		private String cognome;

		@Column(nullable = false)
		private LocalDate dataN;

		@Column(nullable = false)
		private String luogoN;

		@Column(nullable = false)
		private String codiceFiscale;

		@Column(nullable = false)
		private String email;

		@Column(nullable = false)
		private String telefono;

		@OneToMany(mappedBy = "paziente")
		private List<Esame> esami;
		
		public Paziente() {
			this.esami = new ArrayList<>();
		}

		public Paziente(Long id, String nome, String cognome, LocalDate dataN, String luogoN, String codiceFiscale,
				String email, String telefono) {
			super();
			this.id = id;
			this.nome = nome;
			this.cognome = cognome;
			this.dataN = dataN;
			this.luogoN = luogoN;
			this.codiceFiscale = codiceFiscale;
			this.email = email;
			this.telefono = telefono;
		}

		/****************************************************************************************************/
		/******************************************METODI GET E SET******************************************/
		/****************************************************************************************************/
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCognome() {
			return cognome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
		}

		public LocalDate getDataN() {
			return dataN;
		}

		public void setDataN(LocalDate dataN) {
			this.dataN = dataN;
		}

		public String getLuogoN() {
			return luogoN;
		}

		public void setLuogoN(String luogoN) {
			this.luogoN = luogoN;
		}

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public void setCodiceFiscale(String codiceFiscale) {
			this.codiceFiscale = codiceFiscale;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public List<Esame> getEsami() {
			return esami;
		}

		public void setEsami(List<Esame> esami) {
			this.esami = esami;
		}
		
		
		
		
}
