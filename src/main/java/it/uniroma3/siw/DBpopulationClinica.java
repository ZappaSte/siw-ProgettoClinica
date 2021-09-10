package it.uniroma3.siw;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.model.TipologiaEsame;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Paziente;
import it.uniroma3.siw.repository.PazienteRepository;
import it.uniroma3.siw.repository.TipologiaEsameRepository;
import it.uniroma3.siw.service.CredentialsService;
@Component
public class DBpopulationClinica implements ApplicationRunner{
	
	@Autowired
	private TipologiaEsameRepository tipologiaEsameRepository;
	
	@Autowired
	private PazienteRepository pazienteRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.addAll();
		
	}

	
	private void addAll() {
		
		System.out.print("Popolazione DB");
		
		/*CARICAMENTO TIPOLOGIA ESAMI*/		
		TipologiaEsame tp1 = new TipologiaEsame();
		tp1.setNome("Tampone Molecolare");
		tp1.setDescrizione("Test molecolare (o PCR), che evidenzia la presenza di materiale genetico (RNA) del virus. Viene eseguito su tampone rino-faringeo");
		tp1.setPrezzo(new BigDecimal("60.0"));
		
		TipologiaEsame tp2 = new TipologiaEsame();
		tp2.setNome("Tampone Rapido");
		tp2.setDescrizione("Test antigenico (rapido), che evidenzia la presenza di componenti del virus. Viene eseguito su tampone rino-faringeo");
		tp2.setPrezzo(new BigDecimal("18.0"));
		
		TipologiaEsame tp3 = new TipologiaEsame();
		tp3.setNome("Analisi del Sangue");
		tp3.setDescrizione("Controllo generico analisi del sangue(colesterolo,transaminasi,...)");
		tp3.setPrezzo(new BigDecimal("50.00"));
		
		TipologiaEsame tp4 = new TipologiaEsame();
		tp4.setNome("Esame urine");
		tp4.setDescrizione("Controllo generico analisi urine");
		tp4.setPrezzo(new BigDecimal("20.00"));
		
		TipologiaEsame tp5 = new TipologiaEsame();
		tp5.setNome("Esame malattie venerie");
		tp5.setDescrizione("Controllo generico malattie venerie(epatite,HIV,...)");
		tp5.setPrezzo(new BigDecimal("30.00"));
		
		/*CARICAMENTO PAZIENTI*/
		Paziente p1 = new Paziente();
		p1.setNome("Stefano");
		p1.setCognome("Zappasodi");
		LocalDate nascitaP1 = LocalDate.of(1997,Month.NOVEMBER,20);
		p1.setDataN(nascitaP1);
		p1.setLuogoN("San Benedetto del Tronto");
		p1.setCodiceFiscale("ZPPSFN97S20H769X");
		p1.setEmail("ste.zappasodi@gmail.com");
		p1.setTelefono("3661759738");		
		Credentials c1 = new Credentials();
		c1.setUsername("ZPPSFN");
		c1.setPassword("20111997");
		c1.setRole("PAZIENTE");
		c1.setPaziente(p1);
		
		Paziente p2 = new Paziente();
		p2.setNome("Pippo");
		p2.setCognome("Pluto");
		LocalDate nascitaP2 = LocalDate.of(1950,Month.NOVEMBER,2);
		p2.setDataN(nascitaP2);
		p2.setLuogoN("Roma");
		p2.setCodiceFiscale("PLTPPP50S02H769Y");
		p2.setEmail("pippo.pluto@gmail.com");
		p2.setTelefono("3661752798");		
		Credentials c2 = new Credentials();
		c2.setUsername("Pippo");
		c2.setPassword("pippopluto");
		c2.setRole("PAZIENTE");
		c2.setPaziente(p2);
		
		/*CARICAMENTO AMMINISTRAZIONE*/
		Credentials c = new Credentials();
		c.setUsername("admin");
		c.setPassword("admin");
		c.setRole("AMMINISTRAZIONE");
		
		
		/*SALVATAGGIO DATI*/
		tipologiaEsameRepository.save(tp1);
		tipologiaEsameRepository.save(tp2);
		tipologiaEsameRepository.save(tp3);
		tipologiaEsameRepository.save(tp4);
		tipologiaEsameRepository.save(tp5);
		credentialsService.saveCredentials(c);
		pazienteRepository.save(p1);
		credentialsService.saveCredentials(c1);
		pazienteRepository.save(p2);
		credentialsService.saveCredentials(c2);
		
		
		
	}

}
