package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.DTO.RegistrationDTO;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.*;

@RestController
@RequestMapping(value = "/api/verifikacija")
public class VerificationController {
	
	
	private final RegistrationService registrationService;
	
	@Autowired
	 public VerificationController(RegistrationService registrationService) {
	    this.registrationService = registrationService;
    }
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/inactive")
    public ResponseEntity<List<RegistrationDTO>> getInactiveTrainers(@RequestBody RegistrationDTO registrationDTO) {
        // Pozivanjem metode servisa dobavljamo sve zaposlene
        List<Trener> listaTrenera = this.registrationService.findAllTrainers();

        // Kreiramo listu DTO objekata koju ćemo vratiti u odgovoru na zahtev
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        /*System.out.println(uloga);
        System.out.println(uloga + " je jedanaka admin: " + uloga.equals("admin"));*/
        if(registrationDTO.getUloga().equals("admin")) {
	        for (Trener trener : listaTrenera) {
	            // Kreiramo EmployeeDTO za svakog zaposlenog, kojeg je vratila metoda findAll()
	            // i ubacujemo ga u listu employeeDTOS
	        	if(!trener.getActive() && !trener.getObrisan()) {       		
		        	RegistrationDTO registrationDTO2 = new RegistrationDTO(trener.getId(), trener.getKorisnickoIme(), trener.getIme(), trener.getPrezime(), 
		        			trener.getPassword(), trener.getEmail(), trener.getDatumRodjenja(), 
		        			trener.getTelefon(), trener.getUloga(), trener.getActive(), trener.getFitnessCentar().getId());
		        	registrationDTOS.add(registrationDTO2);
	        	}
	        }
	
	        // Vraćamo odgovor 200 OK, a kroz body odgovora šaljemo podatke o pronađenim zaposlenima
	        return new ResponseEntity<>(registrationDTOS, HttpStatus.OK);
		} else {
			//System.out.println("stigo");
			List<RegistrationDTO> lista = new ArrayList<>();
			return new ResponseEntity<>(lista, HttpStatus.OK);
	    }
	}
	@PutMapping(value = "prihvacen/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationDTO> updateTrener(@PathVariable Long id) throws Exception {
      
        // Pozivanjem metode servisa ažuriramo podatke o zaposlenom
        Trener updatedTrener = registrationService.update(id);
        updatedTrener.setId(id);
        
        // Mapiramo ažuriranog zaposlenog na DTO objekat koji vraćamo kroz body odgovora
        RegistrationDTO updatedRegDTO = new RegistrationDTO(updatedTrener.getId(), updatedTrener.getKorisnickoIme(), updatedTrener.getIme(), updatedTrener.getPrezime(), 
        		updatedTrener.getPassword(), updatedTrener.getEmail(), updatedTrener.getDatumRodjenja(), 
        		updatedTrener.getTelefon(), updatedTrener.getUloga(), updatedTrener.getActive(), updatedTrener.getFitnessCentar().getId());

        // Vraćamo odgovor 200 OK, a kroz body odgovora šaljemo podatke o ažuriranom zaposlenom
        return new ResponseEntity<>(updatedRegDTO, HttpStatus.OK);
    }
	
	@DeleteMapping(value = "odbijen/{id}")
    public ResponseEntity<Void> deleteTrener(@PathVariable Long id) {
        // Pozivanjem metode servisa brišemo zaposlenog po ID-ju
        this.registrationService.delete(id);

        // Vraćamo odgovor 204 NO_CONTENT koji signalizira uspešno brisanje
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/brisanje")
    public ResponseEntity<List<RegistrationDTO>> getAllTrainers(@RequestBody RegistrationDTO registrationDTO) {
        // Pozivanjem metode servisa dobavljamo sve zaposlene
        List<Trener> listaTrenera = this.registrationService.findAllTrainers();

        // Kreiramo listu DTO objekata koju ćemo vratiti u odgovoru na zahtev
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        /*System.out.println(uloga);
        System.out.println(uloga + " je jedanaka admin: " + uloga.equals("admin"));*/
        if(registrationDTO.getUloga().equals("admin")) {
	        for (Trener trener : listaTrenera) {	        	      		
		        RegistrationDTO registrationDTO2 = new RegistrationDTO(trener.getId(), trener.getKorisnickoIme(), trener.getIme(), trener.getPrezime(), 
    			trener.getPassword(), trener.getEmail(), trener.getDatumRodjenja(), 
    			trener.getTelefon(), trener.getUloga(), trener.getActive(), trener.getFitnessCentar().getId());
		        registrationDTOS.add(registrationDTO2);	        	
	        }
	        return new ResponseEntity<>(registrationDTOS, HttpStatus.OK);
		} else {
			List<RegistrationDTO> lista = new ArrayList<>();
			return new ResponseEntity<>(lista, HttpStatus.OK);
	    }
	}
	
	@PutMapping(value = "brisanje/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationDTO> deleteTrainerLogically(@PathVariable Long id) throws Exception {
      
        // Pozivanjem metode servisa ažuriramo podatke o zaposlenom
        Trener trainerToDelete = this.registrationService.deleteLogically(id);
        trainerToDelete.setActive(false);
        
        // Mapiramo ažuriranog zaposlenog na DTO objekat koji vraćamo kroz body odgovora
        RegistrationDTO updatedRegDTO = new RegistrationDTO(trainerToDelete.getId(), trainerToDelete.getKorisnickoIme(), trainerToDelete.getIme(), trainerToDelete.getPrezime(), 
        		trainerToDelete.getPassword(), trainerToDelete.getEmail(), trainerToDelete.getDatumRodjenja(), 
        		trainerToDelete.getTelefon(), trainerToDelete.getUloga(), trainerToDelete.getActive(), trainerToDelete.getFitnessCentar().getId());

        // Vraćamo odgovor 200 OK, a kroz body odgovora šaljemo podatke o ažuriranom zaposlenom
        return new ResponseEntity<>(updatedRegDTO, HttpStatus.OK);
    }

}
