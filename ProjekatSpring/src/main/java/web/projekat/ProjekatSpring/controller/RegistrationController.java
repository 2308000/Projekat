package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.DTO.RegistrationDTO;
import web.projekat.ProjekatSpring.entity.Administrator;
import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.service.RegistrationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/registracija")
public class RegistrationController {
	
	private final RegistrationService registrationService;

	// constructor-based dependency injection
	 @Autowired
	 public RegistrationController(RegistrationService registrationService) {
	    this.registrationService = registrationService;
     }
	 
	 @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/centarID")
	    public ResponseEntity<List<RegistrationDTO>> getInactiveTrainers(@RequestBody RegistrationDTO registrationDTO) {
	        
	        List<FitnessCentar> listaCentara = this.registrationService.findAllCenters();     
	        List<RegistrationDTO> registrationDTOS = new ArrayList<>(); 
	        if(!registrationDTO.getZastita().equals("admin")) {
	        	return new ResponseEntity<>(registrationDTOS, HttpStatus.OK);	
	        }
	        		
	        for (FitnessCentar centar : listaCentara) {
	        	if(!centar.isObrisan()) {     		
		        	RegistrationDTO registrationDTO2 = new RegistrationDTO(centar.getId());
		        	registrationDTOS.add(registrationDTO2);   
	        	}
	        }
	        return new ResponseEntity<>(registrationDTOS, HttpStatus.OK);	
	 	}
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE, value = "/trener")
	    public ResponseEntity<RegistrationDTO> createTrainer(@RequestBody RegistrationDTO registrationDTO) throws Exception {
		 
		 	FitnessCentar fitnessCentar = registrationService.findCenterByID(registrationDTO.getCentarID());
		 	
	        Trener trener = new Trener(registrationDTO.getKorisnickoIme(), registrationDTO.getIme(), registrationDTO.getPrezime(), 
	        		registrationDTO.getPassword(), registrationDTO.getEmail(), registrationDTO.getDatumRodjenja(), 
	        		registrationDTO.getTelefon(), registrationDTO.getUloga(), registrationDTO.isActive(), false, fitnessCentar);
	        /*Trener(String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, String uloga, boolean active)*/
	        
	        List<Trener> treneri = registrationService.findAllTrainers();
	        List<Clan> clanovi = registrationService.findAllMembers();
	        List<Administrator> admini = registrationService.findAllAdmins();
	        
	        for(Administrator adminZastita : admini) {
	        	if(adminZastita.getKorisnickoIme().equalsIgnoreCase(trener.getKorisnickoIme())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"username", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}	
	        	if(adminZastita.getEmail().equalsIgnoreCase(trener.getEmail())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"email", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        	
	        	if(adminZastita.getTelefon().equals(trener.getTelefon())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"broj", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        }
	        for(Trener trenerZastita : treneri) {
	        	if(trenerZastita.getKorisnickoIme().equalsIgnoreCase(trener.getKorisnickoIme())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"username", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}	
	        	if(trenerZastita.getEmail().equalsIgnoreCase(trener.getEmail())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"email", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        	
	        	if(trenerZastita.getTelefon().equals(trener.getTelefon())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"broj", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        }
	        
	        for(Clan clanZastita : clanovi) {
	        	if(clanZastita.getKorisnickoIme().equalsIgnoreCase(trener.getKorisnickoIme())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"username", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}	
	        	if(clanZastita.getEmail().equalsIgnoreCase(trener.getEmail())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"email", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        	
	        	if(clanZastita.getTelefon().equals(trener.getTelefon())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"broj", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        }
	        
	        Trener noviTrener = registrationService.create(trener);
	        
	        RegistrationDTO newRegistrationDTO = new RegistrationDTO(noviTrener.getKorisnickoIme(), noviTrener.getIme(), noviTrener.getPrezime(), 
	        		noviTrener.getPassword(), noviTrener.getEmail(), noviTrener.getDatumRodjenja(), 
	        		noviTrener.getTelefon(), noviTrener.getUloga(), noviTrener.getActive(), noviTrener.getFitnessCentar().getId());	
        	
        	return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	    }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE, value = "/clan")
	    public ResponseEntity<RegistrationDTO> createMember(@RequestBody RegistrationDTO registrationDTO) throws Exception {
	        Clan clan = new Clan(registrationDTO.getKorisnickoIme(), registrationDTO.getIme(), registrationDTO.getPrezime(), 
	        		registrationDTO.getPassword(), registrationDTO.getEmail(), registrationDTO.getDatumRodjenja(), 
	        		registrationDTO.getTelefon(), registrationDTO.getUloga(), registrationDTO.isActive());
	        /*Clan(String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, String uloga, boolean active)*/
	        
	        List<Trener> treneri = registrationService.findAllTrainers();
	        List<Clan> clanovi = registrationService.findAllMembers();
	        List<Administrator> admini = registrationService.findAllAdmins();
	        
	        for(Administrator adminZastita : admini) {
	        	if(adminZastita.getKorisnickoIme().equalsIgnoreCase(clan.getKorisnickoIme())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", clan.getDatumRodjenja(), "",
	        				"username", true);
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}	
	        	if(adminZastita.getEmail().equalsIgnoreCase(clan.getEmail())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", clan.getDatumRodjenja(), "",
	        				"email", true);
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        	
	        	if(adminZastita.getTelefon().equals(clan.getTelefon())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", clan.getDatumRodjenja(), "",
	        				"broj", true);
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        }
	        
	        for(Trener trenerZastita : treneri) {
	        	if(trenerZastita.getKorisnickoIme().equalsIgnoreCase(clan.getKorisnickoIme())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", clan.getDatumRodjenja(), "",
	        				"username", true);
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}	
	        	if(trenerZastita.getEmail().equalsIgnoreCase(clan.getEmail())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", clan.getDatumRodjenja(), "",
	        				"email", true);
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        	
	        	if(trenerZastita.getTelefon().equals(clan.getTelefon())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", clan.getDatumRodjenja(), "",
	        				"broj", true);
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        }
	        
	        for(Clan clanZastita : clanovi) {
	        	if(clanZastita.getKorisnickoIme().equalsIgnoreCase(clan.getKorisnickoIme())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", clan.getDatumRodjenja(), "",
	        				"username", true);
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}	
	        	if(clanZastita.getEmail().equalsIgnoreCase(clan.getEmail())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", clan.getDatumRodjenja(), "",
	        				"email", true);
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        	
	        	if(clanZastita.getTelefon().equals(clan.getTelefon())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", clan.getDatumRodjenja(), "",
	        				"broj", true);
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        }
	        
	        Clan noviClan = registrationService.create(clan);

	        RegistrationDTO newRegistrationDTO = new RegistrationDTO(noviClan.getKorisnickoIme(), noviClan.getIme(), noviClan.getPrezime(), 
	        		noviClan.getPassword(), noviClan.getEmail(), noviClan.getDatumRodjenja(), 
	        		noviClan.getTelefon(), noviClan.getUloga(), noviClan.isActive());
	        
	        return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	    }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE, value = "/atrener")
	    public ResponseEntity<RegistrationDTO> createTrainerByAdmin(@RequestBody RegistrationDTO registrationDTO) throws Exception {
		 	FitnessCentar fitnessCentar = registrationService.findCenterByID(registrationDTO.getCentarID());
		 
	        Trener trener = new Trener(registrationDTO.getKorisnickoIme(), registrationDTO.getIme(), registrationDTO.getPrezime(), 
	        		registrationDTO.getPassword(), registrationDTO.getEmail(), registrationDTO.getDatumRodjenja(), 
	        		registrationDTO.getTelefon(), registrationDTO.getUloga(), registrationDTO.isActive(), false, fitnessCentar);
	        /*Trener(String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, String uloga, boolean active)*/
	        
	        List<Trener> treneri = registrationService.findAllTrainers();
	        List<Clan> clanovi = registrationService.findAllMembers();
	        List<Administrator> admini = registrationService.findAllAdmins();
	        
	        if(!registrationDTO.getZastita().equals("admin")) {
        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
        				"trener", trener.getActive(), "greska");
        		
        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
        	}
	        
	        for(Administrator adminZastita : admini) {
	        	if(adminZastita.getKorisnickoIme().equalsIgnoreCase(trener.getKorisnickoIme())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"username", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}	
	        	if(adminZastita.getEmail().equalsIgnoreCase(trener.getEmail())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"email", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        	
	        	if(adminZastita.getTelefon().equals(trener.getTelefon())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"broj", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        }
	        for(Trener trenerZastita : treneri) {
	        	if(trenerZastita.getKorisnickoIme().equalsIgnoreCase(trener.getKorisnickoIme())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"username", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}	
	        	if(trenerZastita.getEmail().equalsIgnoreCase(trener.getEmail())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"email", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        	
	        	if(trenerZastita.getTelefon().equals(trener.getTelefon())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"broj", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        }
	        
	        for(Clan clanZastita : clanovi) {
	        	if(clanZastita.getKorisnickoIme().equalsIgnoreCase(trener.getKorisnickoIme())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"username", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}	
	        	if(clanZastita.getEmail().equalsIgnoreCase(trener.getEmail())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"email", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        	
	        	if(clanZastita.getTelefon().equals(trener.getTelefon())) {
	        		RegistrationDTO newRegistrationDTO = new RegistrationDTO("", "", "", "", "", trener.getDatumRodjenja(), "",
	        				"broj", trener.getActive());
	        		
	        		return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	        	}
	        }
	        
	        Trener noviTrener = registrationService.create(trener);
	        
	        RegistrationDTO newRegistrationDTO = new RegistrationDTO(noviTrener.getKorisnickoIme(), noviTrener.getIme(), noviTrener.getPrezime(), 
	        		noviTrener.getPassword(), noviTrener.getEmail(), noviTrener.getDatumRodjenja(), 
	        		noviTrener.getTelefon(), noviTrener.getUloga(), noviTrener.getActive(), noviTrener.getFitnessCentar().getId());	
     	
     	return new ResponseEntity<>(newRegistrationDTO, HttpStatus.CREATED);
	    }
}
