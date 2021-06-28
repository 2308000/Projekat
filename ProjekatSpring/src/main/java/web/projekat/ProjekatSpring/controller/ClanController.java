package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.Ocena;
import web.projekat.ProjekatSpring.entity.Sala;
import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.entity.Trening;
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
	// constructor-based dependency injection
	 @Autowired
	 public ClanController(TerminService terminService, ClanService clanService, OcenaService ocenaService) {
	    this.terminService = terminService;
	    this.clanService = clanService;	    
	    this.ocenaService = ocenaService;
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
	    	 if(!clan.getOdradjeniTermini().contains(termin) && termin.getPocetakTermina().after(compare)) {
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
		 
		 if(termin.getSala().getKapacitet() > clan.getOdradjeniTermini().size()) { //ukoliko ima mjesta na terminu 
			 odradjeniT.add(termin);							   //dodam termin u listu odradjenih
			 clanoviOdr.add(clan);								   //i clana u clanove koji su odradili taj termin
		 }
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
	    	 if(clan.getOdradjeniTermini().contains(termin) && termin.getPocetakTermina().after(compare)) {
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
		 return new ResponseEntity<>(terminDTO, HttpStatus.OK);	 
	 }
}
