package it.uniroma3.siw.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipologiaEsame {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String descrizione;
	
	@Column(nullable=false)
	private BigDecimal prezzo;
	
	
	@OneToMany(mappedBy = "tipologiaEsame", cascade = CascadeType.ALL)
	private List<Esame> esami;
	
	@OneToMany(mappedBy = "tipologiaEsame", cascade = CascadeType.ALL)
	private List<Requisito> requisiti;

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public List<Esame> getEsami() {
		return esami;
	}

	public void setEsami(List<Esame> esami) {
		this.esami = esami;
	}

	public List<Requisito> getRequisiti() {
		return requisiti;
	}

	public void setRequisiti(List<Requisito> requisiti) {
		this.requisiti = requisiti;
	}

	
}
