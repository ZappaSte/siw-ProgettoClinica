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
import it.uniroma3.siw.model.ValoreRigaRisultato;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Esame;
import it.uniroma3.siw.model.Medico;
import it.uniroma3.siw.model.Paziente;
import it.uniroma3.siw.model.Requisito;
import it.uniroma3.siw.model.RigaRisultato;
import it.uniroma3.siw.model.Risultato;
import it.uniroma3.siw.repository.EsameRepository;
import it.uniroma3.siw.repository.MedicoRepository;
import it.uniroma3.siw.repository.PazienteRepository;
import it.uniroma3.siw.repository.RequisitoRepository;
import it.uniroma3.siw.repository.RigaRisultatoRepository;
import it.uniroma3.siw.repository.RisultatoRepository;
import it.uniroma3.siw.repository.TipologiaEsameRepository;
import it.uniroma3.siw.repository.ValoreRigaRisultatoRepository;
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
	private RigaRisultatoRepository rigaRisultatoRepository;
	
	@Autowired
	private ValoreRigaRisultatoRepository valoreRigaRisultatoRepository;
	
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
		
		Esame e7 = new Esame();
		e7.setDataPrenotazione(LocalDateTime.now());
		LocalDate dataE7 = LocalDate.of(2021,Month.SEPTEMBER,24);
		e7.setDataEsame(dataE7);
		e7.setPaziente(p1);
		e7.setMedico(m2);
		e7.setTipologiaEsame(tp3);
		
		/*CARICAMENTO RIGARISULTATO DELLA TIPOLOGIA TP1*/
		RigaRisultato rr1 = new RigaRisultato();
		rr1.setNome("SARS-CoV-2 RNA molecolare");
		rr1.setTipologiaEsame(tp1);
		
		/*CARICAMENTO RIGARISULTATO DELLA TIPOLOGIA TP2*/
		RigaRisultato rr2 = new RigaRisultato();
		rr2.setNome("SARS-CoV-2 RNA rapido");
		rr2.setTipologiaEsame(tp2);
		
		/*CARICAMENTO RIGARISULTATO DELLA TIPOLOGIA TP3*/
		RigaRisultato rr16 = new RigaRisultato();
		rr16.setNome("Colesterolo HDL");
		rr16.setUnitaDiMisura("mg/dL");
		rr16.setTipologiaEsame(tp3);
		RigaRisultato rr17 = new RigaRisultato();
		rr17.setNome("Protidemia");
		rr17.setUnitaDiMisura("g %");
		rr17.setTipologiaEsame(tp3);
		RigaRisultato rr18 = new RigaRisultato();
		rr18.setNome("Globuli Rossi");
		rr18.setUnitaDiMisura("/uL");
		rr18.setTipologiaEsame(tp3);
		RigaRisultato rr19 = new RigaRisultato();
		rr19.setNome("Globuli Bianchi");
		rr19.setUnitaDiMisura("/uL");
		rr19.setTipologiaEsame(tp3);
		RigaRisultato rr20 = new RigaRisultato();
		rr20.setNome("Piastrine");
		rr20.setUnitaDiMisura("/uL");
		rr20.setTipologiaEsame(tp3);
		RigaRisultato rr21 = new RigaRisultato();
		rr21.setNome("Ferritina");
		rr21.setUnitaDiMisura("ng/mL");
		rr21.setTipologiaEsame(tp3);
		RigaRisultato rr22 = new RigaRisultato();
		rr22.setNome("Potassio Serico");
		rr22.setUnitaDiMisura("mEq/L");
		rr22.setTipologiaEsame(tp3);
		
		/*CARICAMENTO RIGARISULTATO DELLA TIPOLOGIA TP4*/
		RigaRisultato rr3 = new RigaRisultato();
		rr3.setNome("Colore");
		rr3.setTipologiaEsame(tp4);
		RigaRisultato rr4 = new RigaRisultato();
		rr4.setNome("Aspetto");
		rr4.setTipologiaEsame(tp4);
		RigaRisultato rr5 = new RigaRisultato();
		rr5.setNome("Peso Specifico");
		rr5.setTipologiaEsame(tp4);
		RigaRisultato rr6 = new RigaRisultato();
		rr6.setNome("Proteine");
		rr6.setUnitaDiMisura("mg/dL");
		rr6.setTipologiaEsame(tp4);
		RigaRisultato rr7 = new RigaRisultato();
		rr7.setNome("Glucosio");
		rr7.setUnitaDiMisura("mg/dL");
		rr7.setTipologiaEsame(tp4);
		RigaRisultato rr8 = new RigaRisultato();
		rr8.setNome("Emoglobina");
		rr8.setUnitaDiMisura("mg/dL");
		rr8.setTipologiaEsame(tp4);
		
		/*CARICAMENTO RIGARISULTATO DELLA TIPOLOGIA TP5*/
		RigaRisultato rr9 = new RigaRisultato();
		rr9.setNome("HIV");
		rr9.setTipologiaEsame(tp5);
		RigaRisultato rr10 = new RigaRisultato();
		rr10.setNome("Epatite B");
		rr10.setTipologiaEsame(tp5);
		RigaRisultato rr11 = new RigaRisultato();
		rr11.setNome("Epatite C");
		rr11.setTipologiaEsame(tp5);
		RigaRisultato rr12 = new RigaRisultato();
		rr12.setNome("Clamidia");
		rr12.setTipologiaEsame(tp5);
		RigaRisultato rr13 = new RigaRisultato();
		rr13.setNome("Sifiride");
		rr13.setTipologiaEsame(tp5);
		RigaRisultato rr14 = new RigaRisultato();
		rr14.setNome("Herpes Genitale");
		rr14.setTipologiaEsame(tp5);
		RigaRisultato rr15 = new RigaRisultato();
		rr15.setNome("Papillomavirus (HPV)");
		rr15.setTipologiaEsame(tp5);
		
		/*CARICAMENTO VALORIRIGARISULTATO DELL'ESAME E1 DELLA TIPOLOGIA TP5*/
		ValoreRigaRisultato vrr1 = new ValoreRigaRisultato();
		vrr1.setValore("Negativo");
		vrr1.setRigaRisultato(rr9);
		vrr1.setEsame(e1);
		ValoreRigaRisultato vrr2 = new ValoreRigaRisultato();
		vrr2.setValore("Negativo");
		vrr2.setRigaRisultato(rr10);
		vrr2.setEsame(e1);
		ValoreRigaRisultato vrr3 = new ValoreRigaRisultato();
		vrr3.setValore("Negativo");
		vrr3.setRigaRisultato(rr11);
		vrr3.setEsame(e1);
		ValoreRigaRisultato vrr4 = new ValoreRigaRisultato();
		vrr4.setValore("Negativo");
		vrr4.setRigaRisultato(rr12);
		vrr4.setEsame(e1);
		ValoreRigaRisultato vrr5 = new ValoreRigaRisultato();
		vrr5.setValore("Negativo");
		vrr5.setRigaRisultato(rr13);
		vrr5.setEsame(e1);
		ValoreRigaRisultato vrr6 = new ValoreRigaRisultato();
		vrr6.setValore("Negativo");
		vrr6.setRigaRisultato(rr14);
		vrr6.setEsame(e1);
		ValoreRigaRisultato vrr7 = new ValoreRigaRisultato();
		vrr7.setValore("Negativo");
		vrr7.setRigaRisultato(rr15);
		vrr7.setEsame(e1);
		
		e1.setInserimento(true);
		
		/*CARICAMENTO VALORIRIGARISULTATO DELL'ESAME E2 DELLA TIPOLOGIA TP4*/
		ValoreRigaRisultato vrr8 = new ValoreRigaRisultato();
		vrr8.setValore("Giallo");
		vrr8.setRigaRisultato(rr3);
		vrr8.setEsame(e2);
		ValoreRigaRisultato vrr9 = new ValoreRigaRisultato();
		vrr9.setValore("Limpido");
		vrr9.setRigaRisultato(rr4);
		vrr9.setEsame(e2);
		ValoreRigaRisultato vrr10 = new ValoreRigaRisultato();
		vrr10.setValore("1.031");
		vrr10.setRigaRisultato(rr5);
		vrr10.setEsame(e2);
		ValoreRigaRisultato vrr11 = new ValoreRigaRisultato();
		vrr11.setValore("10");
		vrr11.setRigaRisultato(rr6);
		vrr11.setEsame(e2);
		ValoreRigaRisultato vrr12 = new ValoreRigaRisultato();
		vrr12.setValore("0,00");
		vrr12.setRigaRisultato(rr7);
		vrr12.setEsame(e2);
		ValoreRigaRisultato vrr13 = new ValoreRigaRisultato();
		vrr13.setValore("0,00");
		vrr13.setRigaRisultato(rr8);
		vrr13.setEsame(e2);
		
		e2.setInserimento(true);
		
		/*CARICAMENTO VALORIRIGARISULTATO DELL'ESAME E3 DELLA TIPOLOGIA TP1*/
		ValoreRigaRisultato vrr14 = new ValoreRigaRisultato();
		vrr14.setValore("Negativo");
		vrr14.setRigaRisultato(rr1);
		vrr14.setEsame(e3);
		
		e3.setInserimento(true);
		
		/*CARICAMENTO VALORIRIGARISULTATO DELL'ESAME E4 DELLA TIPOLOGIA TP4*/
		ValoreRigaRisultato vrr15 = new ValoreRigaRisultato();
		vrr15.setValore("Giallo");
		vrr15.setRigaRisultato(rr3);
		vrr15.setEsame(e4);
		ValoreRigaRisultato vrr16 = new ValoreRigaRisultato();
		vrr16.setValore("Limpido");
		vrr16.setRigaRisultato(rr4);
		vrr16.setEsame(e4);
		ValoreRigaRisultato vrr17 = new ValoreRigaRisultato();
		vrr17.setValore("1.020");
		vrr17.setRigaRisultato(rr5);
		vrr17.setEsame(e4);
		ValoreRigaRisultato vrr18 = new ValoreRigaRisultato();
		vrr18.setValore("15");
		vrr18.setRigaRisultato(rr6);
		vrr18.setEsame(e4);
		ValoreRigaRisultato vrr19 = new ValoreRigaRisultato();
		vrr19.setValore("0,00");
		vrr19.setRigaRisultato(rr7);
		vrr19.setEsame(e4);
		ValoreRigaRisultato vrr20 = new ValoreRigaRisultato();
		vrr20.setValore("0,00");
		vrr20.setRigaRisultato(rr8);
		vrr20.setEsame(e4);
		
		e4.setInserimento(true);
		
		/*CARICAMENTO VALORIRIGARISULTATO DELL'ESAME E5 DELLA TIPOLOGIA TP1*/
		ValoreRigaRisultato vrr21 = new ValoreRigaRisultato();
		vrr21.setValore("Negativo");
		vrr21.setRigaRisultato(rr1);
		vrr21.setEsame(e5);
		
		e5.setInserimento(true);
		
		/*CARICAMENTO VALORIRIGARISULTATO DELL'ESAME E6 DELLA TIPOLOGIA TP2*/
		ValoreRigaRisultato vrr22 = new ValoreRigaRisultato();
		vrr22.setValore("Positivo");
		vrr22.setRigaRisultato(rr2);
		vrr22.setEsame(e6);
		
		e6.setInserimento(true);
		
		/*CARICAMENTO RISULTATI ESAME e1*/
		Risultato r1 = new Risultato();
		r1.setNome("HIV");
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
		esameRepository.save(e7);
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
		rigaRisultatoRepository.save(rr1);
		rigaRisultatoRepository.save(rr2);
		rigaRisultatoRepository.save(rr3);
		rigaRisultatoRepository.save(rr4);
		rigaRisultatoRepository.save(rr5);
		rigaRisultatoRepository.save(rr6);
		rigaRisultatoRepository.save(rr7);
		rigaRisultatoRepository.save(rr8);
		rigaRisultatoRepository.save(rr9);
		rigaRisultatoRepository.save(rr10);
		rigaRisultatoRepository.save(rr11);
		rigaRisultatoRepository.save(rr12);
		rigaRisultatoRepository.save(rr13);
		rigaRisultatoRepository.save(rr14);
		rigaRisultatoRepository.save(rr15);
		rigaRisultatoRepository.save(rr16);
		rigaRisultatoRepository.save(rr17);
		rigaRisultatoRepository.save(rr18);
		rigaRisultatoRepository.save(rr19);
		rigaRisultatoRepository.save(rr20);
		rigaRisultatoRepository.save(rr21);
		rigaRisultatoRepository.save(rr22);
		valoreRigaRisultatoRepository.save(vrr1);
		valoreRigaRisultatoRepository.save(vrr2);
		valoreRigaRisultatoRepository.save(vrr3);
		valoreRigaRisultatoRepository.save(vrr4);
		valoreRigaRisultatoRepository.save(vrr5);
		valoreRigaRisultatoRepository.save(vrr6);
		valoreRigaRisultatoRepository.save(vrr7);
		valoreRigaRisultatoRepository.save(vrr8);
		valoreRigaRisultatoRepository.save(vrr9);
		valoreRigaRisultatoRepository.save(vrr10);
		valoreRigaRisultatoRepository.save(vrr11);
		valoreRigaRisultatoRepository.save(vrr12);
		valoreRigaRisultatoRepository.save(vrr13);
		valoreRigaRisultatoRepository.save(vrr14);
		valoreRigaRisultatoRepository.save(vrr15);
		valoreRigaRisultatoRepository.save(vrr16);
		valoreRigaRisultatoRepository.save(vrr17);
		valoreRigaRisultatoRepository.save(vrr18);
		valoreRigaRisultatoRepository.save(vrr19);
		valoreRigaRisultatoRepository.save(vrr20);
		valoreRigaRisultatoRepository.save(vrr21);
		valoreRigaRisultatoRepository.save(vrr22);
		
		
	}

}
