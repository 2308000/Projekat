package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.Ocena;
import web.projekat.ProjekatSpring.entity.Sala;
import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.entity.Trening;
import web.projekat.ProjekatSpring.entity.DTO.ClanDTO;
import web.projekat.ProjekatSpring.entity.DTO.TerminDTO;
import web.projekat.ProjekatSpring.service.ClanService;
import web.projekat.ProjekatSpring.service.FitnessCentarService;
import web.projekat.ProjekatSpring.service.OcenaService;
import web.projekat.ProjekatSpring.service.SalaService;
import web.projekat.ProjekatSpring.service.TerminService;
import web.projekat.ProjekatSpring.service.TrenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(value = "/api/clan")
public class ClanController {

	 private final TerminService terminService;
	 private final ClanService clanService;
	 private final OcenaService ocenaService;
	 private final TrenerService trenerService;
	// constructor-based dependency injection
	 @Autowired
	 public ClanController(TerminService terminService, ClanService clanService, OcenaService ocenaService, TrenerService trenerService) {
	    this.terminService = terminService;
	    this.clanService = clanService;	    
	    this.ocenaService = ocenaService;
	    this.trenerService = trenerService;
     }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/prikazTermina")
	 public ResponseEntity<List<TerminDTO>> getNeprijavljeniTermini(@RequestBody TerminDTO getDTO) throws Exception {
		 //System.out.println(id);
		 List<Termin> terminList = this.terminService.findAll();
		 List<TerminDTO> terminDTOS = new ArrayList<>();
		 if(getDTO.getClanID() == null) {
			 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
		 }
		 Clan clan = this.clanService.findById(getDTO.getClanID());

	     if(getDTO.getZastita() == null && !getDTO.getZastita().equals("clan")) {
	    	 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	     }
	     
	     Date compare = new Date();
	     for (Termin termin : terminList) {
	    	 Trener trener = this.trenerService.findOne(termin.getTrener().getId());
	    	 if(!clan.getOdradjeniTermini().contains(termin) && termin.getPocetakTermina().after(compare)
	    			 && trener.getActive() && !trener.getObrisan()) {
	    		 System.out.println("Trener: " + trener.getKorisnickoIme() + "; obrisan: " + trener.getObrisan());
	    	 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	    		termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
	    		termin.getTrening().getOpis(), termin.getTrening().getTip());
	    	 	terminDTOS.add(terminDTO);
	    	 }
	     }

	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/prijavaTermina")
	 public ResponseEntity<TerminDTO> prijavaNaTermin(@RequestBody TerminDTO getDTO) throws Exception {
		 Clan clan = this.clanService.findById(getDTO.getClanID()); //dobavim clana koji se prijavio
		 Termin termin = this.terminService.findOne(getDTO.getId()); //dobavim termin na koji se prijavio
		 Set<Termin> odradjeniT = clan.getOdradjeniTermini();
		 Set<Clan> clanoviOdr = termin.getClanoviOdradjenih();
		 
		 if(getDTO.getZastita() == null || !getDTO.getZastita().equals("clan")) {
			 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(), termin.getKrajTermina(), 
					 termin.getTrajanjeTermina(), termin.getCenaTermina(), "denied", clan.getId(), clan.getKorisnickoIme(),
					 clan.getIme(), clan.getPrezime());
			 return new ResponseEntity<>(terminDTO, HttpStatus.OK);
		 }
		 
		 if((termin.getSala().getKapacitet() - 1) > clan.getOdradjeniTermini().size()) { //ukoliko ima mjesta na terminu 
			 odradjeniT.add(termin);							   //dodam termin u listu odradjenih
			 clanoviOdr.add(clan);								   //i clana u clanove koji su odradili taj termin
		 
		 //updateujem clana sa novom listom termina koje je odradio
		 Clan clanToUpdate = new Clan(clan.getId(), clan.getKorisnickoIme(), clan.getIme(), clan.getPrezime(), clan.getPassword(),
				 clan.getEmail(), clan.getDatumRodjenja(), clan.getTelefon(), clan.getUloga(), clan.getActive(), clan.getOcene(), odradjeniT,
				 clan.getOcenjeniTermini());
		 clan = this.clanService.update(clanToUpdate);
		 //updateujem termin sa novom listom clanova koji su ga odradili
		 Termin terminToUpdate = new Termin(termin.getId(), termin.getPocetakTermina(), termin.getKrajTermina(),
				 termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening(), termin.getTrener(),
				 termin.getSala(), termin.getOcene(), termin.getClanoviOcenjenih(), clanoviOdr);
		 termin = this.terminService.update(terminToUpdate);
		 //napravim resposne
		 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(), termin.getKrajTermina(), 
				 termin.getTrajanjeTermina(), termin.getCenaTermina(), "ok", clan.getId(), clan.getKorisnickoIme(),
				 clan.getIme(), clan.getPrezime());
		 return new ResponseEntity<>(terminDTO, HttpStatus.OK);
	 	}
		 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(), termin.getKrajTermina(), 
				 termin.getTrajanjeTermina(), termin.getCenaTermina(), "pun", clan.getId(), clan.getKorisnickoIme(),
				 clan.getIme(), clan.getPrezime());
		 return new ResponseEntity<>(terminDTO, HttpStatus.OK);
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/prikazPrijavljenih")
	 public ResponseEntity<List<TerminDTO>> getPrijavljeniTermini(@RequestBody TerminDTO getDTO) throws Exception {
		 //System.out.println(id);
		 List<Termin> terminList = this.terminService.findAll();
		 List<TerminDTO> terminDTOS = new ArrayList<>();
		 if(getDTO.getClanID() == null) {
			 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
		 }
		 Clan clan = this.clanService.findById(getDTO.getClanID());

	     if(getDTO.getZastita() == null && !getDTO.getZastita().equals("clan")) {
	    	 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	     }
	     
	     Date compare = new Date();
	     //System.out.println("Trenutni datum je " + compare);
	     
	     for (Termin termin : terminList) {
	    	 Trener trener = this.trenerService.findOne(termin.getTrener().getId());
	    	 if(clan.getOdradjeniTermini().contains(termin) && termin.getPocetakTermina().after(compare)
	    			 && trener.getActive() && !trener.getObrisan()) {
	    		 //System.out.println("Datum održavanja termina je " + termin.getPocetakTermina().after(compare));
	    		 //System.out.println("Je li obrisan? " + trener.getObrisan());
	    		 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	    				 termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
	    				 termin.getTrening().getOpis(), termin.getTrening().getTip());
	    	 	 terminDTOS.add(terminDTO);
	    	 }
	     }

	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/prikazOdradjenih")
	 public ResponseEntity<List<TerminDTO>> getOdradjeniTermini(@RequestBody TerminDTO getDTO) throws Exception {
		 //System.out.println(id);
		 List<Termin> terminList = this.terminService.findAll();
		 List<TerminDTO> terminDTOS = new ArrayList<>();
		 if(getDTO.getClanID() == null) {
			 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
		 }
		 Clan clan = this.clanService.findById(getDTO.getClanID());

	     if(getDTO.getZastita() == null && !getDTO.getZastita().equals("clan")) {
	    	 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	     }
	     
	     Date compare = new Date();
	     //System.out.println("Trenutni datum je " + compare);
	     
	     for (Termin termin : terminList) {
	    	 if(clan.getOdradjeniTermini().contains(termin) && termin.getPocetakTermina().before(compare)) {
	    		 //System.out.println("Datum održavanja termina je " + termin.getPocetakTermina().after(compare));
	    		 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	    				 termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
	    				 termin.getTrening().getOpis(), termin.getTrening().getTip());
	    	 	 terminDTOS.add(terminDTO);
	    	 }
	     }

	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/odjavaTermina")
	 public ResponseEntity<TerminDTO> odjavaTermina(@RequestBody TerminDTO getDTO) throws Exception {
		 Clan clan = this.clanService.findById(getDTO.getClanID());
		 Set<Termin> odradjeni = clan.getOdradjeniTermini();
		 Termin odjavaTermina = this.terminService.findOne(getDTO.getId());
		 Set<Clan> clanovi = odjavaTermina.getClanoviOdradjenih();
		 
		 if(getDTO.getZastita() == null || !getDTO.getZastita().equals("clan")) {
			 TerminDTO ret = new TerminDTO(odjavaTermina.getId(), odjavaTermina.getPocetakTermina(), odjavaTermina.getKrajTermina(), 
			 odjavaTermina.getTrajanjeTermina(), odjavaTermina.getCenaTermina(), "denied", clan.getId(), clan.getKorisnickoIme(), clan.getIme(), clan.getPrezime());
			 return new ResponseEntity<>(ret, HttpStatus.OK);
		 }
		 
		 clanovi.remove(clan);
		 odradjeni.remove(odjavaTermina);
		 Clan clanToUpdate = new Clan(clan.getId(), clan.getKorisnickoIme(), clan.getIme(), clan.getPrezime(), clan.getPassword(),
				 clan.getEmail(), clan.getDatumRodjenja(), clan.getTelefon(), clan.getUloga(), clan.getActive(), clan.getOcene(), odradjeni,
				 clan.getOcenjeniTermini());
		 clan = this.clanService.update(clanToUpdate);
		 Termin terminToUpdate = new Termin(odjavaTermina.getId(), odjavaTermina.getPocetakTermina(), odjavaTermina.getKrajTermina(),
				 odjavaTermina.getTrajanjeTermina(), odjavaTermina.getCenaTermina(), odjavaTermina.getTrening(), odjavaTermina.getTrener(),
				 odjavaTermina.getSala(), odjavaTermina.getOcene(), odjavaTermina.getClanoviOcenjenih(), clanovi);
		 odjavaTermina = this.terminService.update(terminToUpdate);
		 TerminDTO ret = new TerminDTO(odjavaTermina.getId(), odjavaTermina.getPocetakTermina(), odjavaTermina.getKrajTermina(), 
				 odjavaTermina.getTrajanjeTermina(), odjavaTermina.getCenaTermina(), "ok", clan.getId(), clan.getKorisnickoIme(), clan.getIme(), clan.getPrezime());
		 return new ResponseEntity<>(ret, HttpStatus.OK);	 
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/prikazOcenjenih")
	 public ResponseEntity<List<TerminDTO>> getOcenjeniTermini(@RequestBody TerminDTO getDTO) throws Exception {
		 //System.out.println(id);
		 List<Termin> terminList = this.terminService.findAll();
		 List<TerminDTO> terminDTOS = new ArrayList<>();
		 List<Ocena> ocene = this.ocenaService.findAll();
		 if(getDTO.getClanID() == null) {
			 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
		 }
		 Clan clan = this.clanService.findById(getDTO.getClanID());
		 Set<Termin> ocenjeniTermini = clan.getOcenjeniTermini();
	     if(getDTO.getZastita() == null && !getDTO.getZastita().equals("clan")) {
	    	 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	     }
	     
	     for (Termin termin : terminList) {
	    	 if(ocenjeniTermini.contains(termin)) {
	    		 double vrOcene = 0.0;
	    		 for(Ocena ocena : ocene) {    			 
	    	    	 if(ocena.getTermin().getId() == termin.getId()) {
	    	    		 vrOcene = ocena.getOcena();
	    	    	 }
	    	     }
	    	    	 
	    		 /*TerminDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
	    					String nazivTreninga, String opisTreninga, String tipTreninga, Long clanID, double ocena)*/
	    		 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(), termin.getKrajTermina(), 
	    				 termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
	    				 termin.getTrening().getOpis(), termin.getTrening().getTip(), clan.getId(), vrOcene);
	    	 	 terminDTOS.add(terminDTO);
	    	 }
	     }
	     

	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/prikazNeocenjenih")
	 public ResponseEntity<List<TerminDTO>> getNeocenjeniTermini(@RequestBody TerminDTO getDTO) throws Exception {
		 //System.out.println(id);
		 List<Termin> terminList = this.terminService.findAll();
		 List<TerminDTO> terminDTOS = new ArrayList<>();
		 if(getDTO.getClanID() == null) {
			 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
		 }
		 Clan clan = this.clanService.findById(getDTO.getClanID());

	     if(getDTO.getZastita() == null && !getDTO.getZastita().equals("clan")) {
	    	 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	     }
	     
	     Date compare = new Date();
	     //System.out.println("Trenutni datum je " + compare);
	     
	     for (Termin termin : terminList) {
	    	 if(clan.getOdradjeniTermini().contains(termin) && termin.getPocetakTermina().before(compare) && !clan.getOcenjeniTermini().contains(termin)) {
	    		 //System.out.println("Datum održavanja termina je " + termin.getPocetakTermina().after(compare));
	    		 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	    				 termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
	    				 termin.getTrening().getOpis(), termin.getTrening().getTip());
	    	 	 terminDTOS.add(terminDTO);
	    	 }
	     }

	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/ocenaTermina")
	 public ResponseEntity<TerminDTO> ocenaTermina(@RequestBody TerminDTO getDTO) throws Exception {
		 Clan clan = this.clanService.findById(getDTO.getClanID());
		 Set<Termin> ocenjeni = clan.getOcenjeniTermini();		 
		 Termin ocenjeniTermin = this.terminService.findOne(getDTO.getId());
		 Set<Clan> clanovi = ocenjeniTermin.getClanoviOcenjenih();
		 Set<Ocena> oceneClana = clan.getOcene();
		 Set<Ocena> oceneTermina = ocenjeniTermin.getOcene();
		 
		 
		 if(getDTO.getZastita() == null || !getDTO.getZastita().equals("clan")) {
			 TerminDTO terminDTO = new TerminDTO(ocenjeniTermin.getId(), ocenjeniTermin.getPocetakTermina(), ocenjeniTermin.getKrajTermina(), 
					 ocenjeniTermin.getTrajanjeTermina(), ocenjeniTermin.getCenaTermina(), "denied", clan.getId(), clan.getKorisnickoIme(),
					 clan.getIme(), clan.getPrezime());
			 return new ResponseEntity<>(terminDTO, HttpStatus.OK);
		 }
		 
		 Ocena ocena = new Ocena(getDTO.getOcena(), ocenjeniTermin, clan);
		 ocenjeni.add(ocenjeniTermin);
		 clanovi.add(clan);
		 oceneTermina.add(ocena);
		 Clan clanToUpdate = new Clan(clan.getId(), clan.getKorisnickoIme(), clan.getIme(), clan.getPrezime(), clan.getPassword(),
				 clan.getEmail(), clan.getDatumRodjenja(), clan.getTelefon(), clan.getUloga(), clan.getActive(), oceneClana, clan.getOdradjeniTermini(),
				 ocenjeni);
		 clan = this.clanService.update(clanToUpdate);
		 Termin terminToUpdate = new Termin(ocenjeniTermin.getId(), ocenjeniTermin.getPocetakTermina(), ocenjeniTermin.getKrajTermina(),
				 ocenjeniTermin.getTrajanjeTermina(), ocenjeniTermin.getCenaTermina(), ocenjeniTermin.getTrening(), ocenjeniTermin.getTrener(),
				 ocenjeniTermin.getSala(), oceneTermina, clanovi, ocenjeniTermin.getClanoviOdradjenih());
		 ocenjeniTermin = this.terminService.update(terminToUpdate);
		 TerminDTO terminDTO = new TerminDTO(ocenjeniTermin.getId(), ocenjeniTermin.getPocetakTermina(), ocenjeniTermin.getKrajTermina(), 
				 ocenjeniTermin.getTrajanjeTermina(), ocenjeniTermin.getCenaTermina(), ocena.getOcena(), clan.getId(), clan.getKorisnickoIme(),
				 clan.getIme(), clan.getPrezime());
		 Trener trener = ocenjeniTermin.getTrener();
		 /*System.out.println("Trener koji drži termin je " + trener.getKorisnickoIme());
		 System.out.println("Prosečna ocena pre ocenjivanja je " + trener.getProsecnaOcena());
		 System.out.println("Dodijeljena ocjena je " + getDTO.getOcena());*/
		 /*Trener(Long id, String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, String uloga, Boolean active, Boolean obrisan, Double prosecnaOcena,
			FitnessCentar fitnessCentar, Set<Termin> termini)*/
		 Trener trenerToUpdate = new Trener(trener.getId(), trener.getKorisnickoIme(), trener.getIme(), trener.getPrezime(), trener.getPassword(),
				 trener.getEmail(), trener.getDatumRodjenja(), trener.getTelefon(), trener.getUloga(), trener.getActive(), trener.getObrisan(),
				 trener.getProsecnaOcena(), trener.getFitnessCentar(), trener.getTermini());
		 trener = this.trenerService.updateAvgGrade(trenerToUpdate);
		 //System.out.println("Prosečna ocena nakon ocenjivanja je " + trener.getProsecnaOcena());
		 return new ResponseEntity<>(terminDTO, HttpStatus.OK);	 
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/prikazTerminaDetaljno")
	 public ResponseEntity<TerminDTO> prikazTermina(@RequestBody TerminDTO getDTO) throws Exception {
		 Termin termin = this.terminService.findOne(getDTO.getId());
		 /*TerminDTO(Long id, String zastita, String korisnickoIme, double ocena, String oznakaSale, Set<Ocena> ocene,
					Set<Clan> clanoviOdradjenih)*/
		 Trener trener = this.trenerService.findOne(termin.getTrener().getId());
		 List<Double> retOcene = new ArrayList<>();
		 Set<Ocena> ocene = termin.getOcene();
		 
		 for(Ocena ocena : ocene) {
			 retOcene.add(ocena.getOcena());
		 }
		 
		 List<String> retClanovi = new ArrayList<>();
		 Set<Clan> clanovi = termin.getClanoviOdradjenih();
		 
		 for(Clan clan : clanovi) {
			 retClanovi.add(clan.getKorisnickoIme());
		 }
		 
		 System.out.println("Ime trenera: " + trener.getKorisnickoIme());
		 if(termin.getOcene() == null) {
			 System.out.println("Ocene su null");
		 }
		 TerminDTO terminDTO = new TerminDTO(termin.getId(), "denied", termin.getTrener().getKorisnickoIme(), termin.getTrener().getProsecnaOcena(),
				 termin.getSala().getOznakaSale(), retOcene, retClanovi);
		 
		 if(getDTO.getZastita() == null || !getDTO.getZastita().equals("clan")) {
			 return new ResponseEntity<>(terminDTO, HttpStatus.OK);
		 }
		 
		 terminDTO.setZastita("ok");
		 System.out.println("ID: " + terminDTO.getId());
		 System.out.println("Zastita: " + terminDTO.getZastita());
		 System.out.println("Korisnicko ime: " + terminDTO.getKorisnickoIme());
		 System.out.println("Ocena trenera: " + termin.getTrener().getProsecnaOcena());
		 System.out.println("Oznaka sale: " + termin.getSala().getOznakaSale());
		 //System.out.println("Ocene: " + termin.getOcene());
		 //System.out.println("Clanovi koji su odradili trening:\n" + termin.getClanoviOdradjenih());
		 
		 return new ResponseEntity<>(terminDTO, HttpStatus.OK);	 
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/profil")
	 public ResponseEntity<ClanDTO> prikazProfila(@RequestBody ClanDTO getDTO) throws Exception {
		 
		 ClanDTO clanDTO = new ClanDTO();
		 if(getDTO.getId() == null) {
			 return new ResponseEntity<>(clanDTO, HttpStatus.OK);
		 }
		 
		 Clan clan = this.clanService.findById(getDTO.getId());
		 List<Double> retOcene = new ArrayList<>();
		 Set<Ocena> ocene = clan.getOcene();
		 List<String> retOdradjeni = new ArrayList<>();
		 Set<Termin> odradjeni = clan.getOdradjeniTermini();
		 List<String> retOcenjeni = new ArrayList<>();
		 Set<Termin> ocenjeni = clan.getOcenjeniTermini();
		 
		 if(getDTO.getZastita() == null || !getDTO.getZastita().equals("clan")) {
			 return new ResponseEntity<>(clanDTO, HttpStatus.OK);	 
		 }
		 
		 for(Ocena ocena : ocene) {
			 retOcene.add(ocena.getOcena());
		 }
		 
		 for(Termin termin : odradjeni) {
			 retOdradjeni.add(termin.getTrening().getNazivTreninga());
		 }
		 for(Termin termin : ocenjeni) {
			 retOcenjeni.add(termin.getTrening().getNazivTreninga());
		 }
		 /*ClanDTO(Long id, String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, Boolean active, Set<Double> ocene, Set<String> odradjeniTermini,
			Set<String> ocenjeniTermini, String zastita)*/
		 ClanDTO clanDTO2 = new ClanDTO(clan.getId(), clan.getKorisnickoIme(), clan.getIme(), clan.getPrezime(), clan.getPassword(), clan.getEmail(),
				 clan.getDatumRodjenja(), clan.getTelefon(), clan.getActive(), retOcene, retOdradjeni, retOcenjeni, "clan");
		 
		 return new ResponseEntity<>(clanDTO2, HttpStatus.OK);	 
	 }
}
