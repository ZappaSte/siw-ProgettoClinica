package it.uniroma3.siw;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.model.TipologiaEsame;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Esame;
import it.uniroma3.siw.model.Medico;
import it.uniroma3.siw.model.Paziente;
import it.uniroma3.siw.model.Requisito;
import it.uniroma3.siw.model.Risultato;
import it.uniroma3.siw.repository.EsameRepository;
import it.uniroma3.siw.repository.MedicoRepository;
import it.uniroma3.siw.repository.PazienteRepository;
import it.uniroma3.siw.repository.RequisitoRepository;
import it.uniroma3.siw.repository.RisultatoRepository;
import it.uniroma3.siw.repository.TipologiaEsameRepository;
import it.uniroma3.siw.service.CredentialsService;
@Component
public class DBpopulationClinica implements ApplicationRunner{
	
	@Autowired
	private TipologiaEsameRepository tipologiaEsameRepository;
	
	@Autowired
	private PazienteRepository pazienteRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private EsameRepository esameRepository;
	
	@Autowired
	private RisultatoRepository risultatoRepository;
	
	@Autowired
	private RequisitoRepository requisitoRepository;
	
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
		
		/*CARICAMENTO PRE-REQUISITI DI UNA TIPOLOGIA DI ESAME*/
		Requisito rq1 = new Requisito();
		rq1.setNome("digiuno 12");
		rq1.setDescrizione("Il paziente deve essere a digiuno da 12 ore prima dell'esame");
		rq1.setTipologiaEsame(tp3);
		
		Requisito rq2 = new Requisito();
		rq2.setNome("no alcol");
		rq2.setDescrizione("Il paziente non deve bere alcolici 24 ore prima dell'esame");
		rq2.setTipologiaEsame(tp3);
		
		Requisito rq3 = new Requisito();
		rq3.setNome("urina");
		rq3.setDescrizione("L'urina da analizzare deve essere quella appena sveglio");
		rq3.setTipologiaEsame(tp4);
				
		/*CARICAMENTO MEDICI*/
		Medico m1 = new Medico();
		m1.setNome("Gregory");
		m1.setCognome("House");
		m1.setSpecializzazione("Primario di medicina");
		
		Medico m2 = new Medico();
		m2.setNome("Elliot");
		m2.setCognome("Reid");
		m2.setSpecializzazione("Medico privato");
		
		Medico m3 = new Medico();
		m3.setNome("John Michael");
		m3.setCognome("Dorian");
		m3.setSpecializzazione("Assistente di medicina");
		
		Medico m4 = new Medico();
		m4.setNome("Stephen");
		m4.setCognome("Strange");
		m4.setSpecializzazione("Medico Neurochirurgo e Stregone Supremo");
		
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
		c1.setUsername(p1.getEmail());
		c1.setPassword(p1.getCodiceFiscale());
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
		c2.setUsername(p2.getEmail());
		c2.setPassword(p2.getCodiceFiscale());
		c2.setRole("PAZIENTE");
		c2.setPaziente(p2);
		
		/*CARICAMENTO ESAMI PRENOTATI*/
		Esame e1 = new Esame();
		e1.setDataPrenotazione(LocalDateTime.now());
		LocalDate dataE1 = LocalDate.of(2022,Month.APRIL,02);
		e1.setDataEsame(dataE1);
		e1.setPaziente(p1);
		e1.setMedico(m4);
		e1.setTipologiaEsame(tp5);
		
		Esame e2 = new Esame();
		e2.setDataPrenotazione(LocalDateTime.now());
		LocalDate dataE2 = LocalDate.of(2022,Month.APRIL,15);
		e2.setDataEsame(dataE2);
		e2.setPaziente(p1);
		e2.setMedico(m3);
		e2.setTipologiaEsame(tp4);
		
		Esame e3 = new Esame();
		e3.setDataPrenotazione(LocalDateTime.now());
		LocalDate dataE3 = LocalDate.of(2022,Month.DECEMBER,02);
		e3.setDataEsame(dataE3);
		e3.setPaziente(p1);
		e3.setMedico(m1);
		e3.setTipologiaEsame(tp1);
		
		Esame e4 = new Esame();
		e4.setDataPrenotazione(LocalDateTime.now());
		LocalDate dataE4 = LocalDate.of(2022,Month.FEBRUARY,02);
		e4.setDataEsame(dataE4);
		e4.setPaziente(p2);
		e4.setMedico(m4);
		e4.setTipologiaEsame(tp4);
		
		Esame e5 = new Esame();
		e5.setDataPrenotazione(LocalDateTime.now());
		LocalDate dataE5 = LocalDate.of(2022,Month.JANUARY,15);
		e5.setDataEsame(dataE5);
		e5.setPaziente(p2);
		e5.setMedico(m3);
		e5.setTipologiaEsame(tp1);
		
		Esame e6 = new Esame();
		e6.setDataPrenotazione(LocalDateTime.now());
		LocalDate dataE6 = LocalDate.of(2022,Month.NOVEMBER,02);
		e6.setDataEsame(dataE6);
		e6.setPaziente(p2);
		e6.setMedico(m2);
		e6.setTipologiaEsame(tp2);
		
		/*CARICAMENTO RISULTATI ESAME e1*/
		Risultato r1 = new Risultato();
		r1.setNome("HIW");
		r1.setValore("Negativo");
		r1.setEsame(e1);
		
		Risultato r2 = new Risultato();
		r2.setNome("Epatite B");
		r2.setValore("Negativo");
		r2.setEsame(e1);
		
		Risultato r3 = new Risultato();
		r3.setNome("Epatite C");
		r3.setValore("Negativo");
		r3.setEsame(e1);
		
		Risultato r4 = new Risultato();
		r4.setNome("Sifilide");
		r4.setValore("Negativo");
		r4.setEsame(e1);
		
		/*CARICAMENTO RISULTATI ESAME e2*/
		Risultato r5 = new Risultato();
		r5.setNome("Colore");
		r5.setValore("Giallo");
		r5.setEsame(e2);
		
		Risultato r6 = new Risultato();
		r6.setNome("Aspetto");
		r6.setValore("Limpido");
		r6.setEsame(e2);
		
		Risultato r7 = new Risultato();
		r7.setNome("pH");
		r7.setValore("5,0");
		r7.setEsame(e2);
		
		Risultato r8 = new Risultato();
		r8.setNome("Proteine");
		r8.setValore("10");
		r8.setUnitaDiMisura("mg/dL");
		r8.setEsame(e2);
		
		Risultato r9 = new Risultato();
		r9.setNome("Emoglobina");
		r9.setValore("0");
		r9.setUnitaDiMisura("mg/dL");
		r9.setEsame(e2);
		
		Risultato r10 = new Risultato();
		r10.setNome("Glucosio");
		r10.setValore("0");
		r10.setUnitaDiMisura("mg/dL");
		r10.setEsame(e2);
		
		/*CARICAMENTO RISULTATI ESAME e3*/
		Risultato r11 = new Risultato();
		r11.setNome("SARS-CoV-2 RNA");
		r11.setValore("Negativo");
		r11.setEsame(e3);
		
		/*CARICAMENTO RISULTATI ESAME e4*/
		Risultato r12 = new Risultato();
		r12.setNome("Colore");
		r12.setValore("Giallo");
		r12.setEsame(e4);
		
		Risultato r13 = new Risultato();
		r13.setNome("Aspetto");
		r13.setValore("Limpido");
		r13.setEsame(e4);
		
		Risultato r14 = new Risultato();
		r14.setNome("pH");
		r14.setValore("5,0");
		r14.setEsame(e4);
		
		Risultato r15 = new Risultato();
		r15.setNome("Proteine");
		r15.setValore("10");
		r15.setUnitaDiMisura("mg/dL");
		r15.setEsame(e4);
		
		Risultato r16 = new Risultato();
		r16.setNome("Emoglobina");
		r16.setValore("0");
		r16.setUnitaDiMisura("mg/dL");
		r16.setEsame(e4);
		
		Risultato r17 = new Risultato();
		r17.setNome("Glucosio");
		r17.setValore("0");
		r17.setUnitaDiMisura("mg/dL");
		r17.setEsame(e4);
		
		/*CARICAMENTO RISULTATI ESAME e5*/
		Risultato r18 = new Risultato();
		r18.setNome("SARS-CoV-2 RNA");
		r18.setValore("Negativo");
		r18.setEsame(e5);
		
		/*CARICAMENTO RISULTATI ESAME e6*/
		Risultato r19 = new Risultato();
		r19.setNome("SARS-CoV-2 RNA");
		r19.setValore("Positivo");
		r19.setEsame(e6);
		
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
		requisitoRepository.save(rq1);
		requisitoRepository.save(rq2);
		requisitoRepository.save(rq3);
		medicoRepository.save(m1);
		medicoRepository.save(m2);
		medicoRepository.save(m3);
		medicoRepository.save(m4);		
		credentialsService.saveCredentials(c);
		pazienteRepository.save(p1);
		credentialsService.saveCredentials(c1);
		pazienteRepository.save(p2);
		credentialsService.saveCredentials(c2);
		esameRepository.save(e1);
		esameRepository.save(e2);
		esameRepository.save(e3);
		esameRepository.save(e4);
		esameRepository.save(e5);
		esameRepository.save(e6);
		risultatoRepository.save(r1);
		risultatoRepository.save(r2);
		risultatoRepository.save(r3);
		risultatoRepository.save(r4);
		risultatoRepository.save(r5);
		risultatoRepository.save(r6);
		risultatoRepository.save(r7);
		risultatoRepository.save(r8);
		risultatoRepository.save(r9);
		risultatoRepository.save(r10);
		risultatoRepository.save(r11);
		risultatoRepository.save(r12);
		risultatoRepository.save(r13);
		risultatoRepository.save(r14);
		risultatoRepository.save(r15);
		risultatoRepository.save(r16);
		risultatoRepository.save(r17);
		risultatoRepository.save(r18);
		risultatoRepository.save(r19);
		
		
		
	}

}
