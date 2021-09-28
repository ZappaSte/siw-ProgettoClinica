package it.uniroma3.siw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ValoreRigaRisultato {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String valore;
	
	@ManyToOne
	private RigaRisultato rigaRisultato;
	
	@ManyToOne
	private Esame esame;
	
	/****************************************************************************************************/
	/******************************************METODI GET E SET******************************************/
	/****************************************************************************************************/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public RigaRisultato getRigaRisultato() {
		return rigaRisultato;
	}

	public void setRigaRisultato(RigaRisultato rigaRisultato) {
		this.rigaRisultato = rigaRisultato;
	}

	public Esame getEsame() {
		return esame;
	}

	public void setEsame(Esame esame) {
		this.esame = esame;
	}
	
	
	
}
