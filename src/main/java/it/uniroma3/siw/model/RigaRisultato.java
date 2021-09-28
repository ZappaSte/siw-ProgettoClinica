package it.uniroma3.siw.model;

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
public class RigaRisultato {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	private String unitaDiMisura;
	
	@ManyToOne
	private TipologiaEsame tipologiaEsame;
	
	@OneToMany(mappedBy = "rigaRisultato", cascade = CascadeType.ALL)
	private List<ValoreRigaRisultato> valoriRigheRisultati;

	
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

	public String getUnitaDiMisura() {
		return unitaDiMisura;
	}

	public void setUnitaDiMisura(String unitaDiMisura) {
		this.unitaDiMisura = unitaDiMisura;
	}

	public TipologiaEsame getTipologiaEsame() {
		return tipologiaEsame;
	}

	public void setTipologiaEsame(TipologiaEsame tipologiaEsame) {
		this.tipologiaEsame = tipologiaEsame;
	}

	public List<ValoreRigaRisultato> getValoriRigheRisultati() {
		return valoriRigheRisultati;
	}

	public void setValoriRigheRisultati(List<ValoreRigaRisultato> valoriRigheRisultati) {
		this.valoriRigheRisultati = valoriRigheRisultati;
	}
	
	
	
	
	
	
	
	
	
	
}
