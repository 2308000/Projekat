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
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/inactive")
    public ResponseEntity<List<RegistrationDTO>> getInactiveTrainers() {
        // Pozivanjem metode servisa dobavljamo sve zaposlene
        List<Trener> listaTrenera = this.registrationService.findAllTrainers();

        // Kreiramo listu DTO objekata koju ćemo vratiti u odgovoru na zahtev
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();

        for (Trener trener : listaTrenera) {
            // Kreiramo EmployeeDTO za svakog zaposlenog, kojeg je vratila metoda findAll()
            // i ubacujemo ga u listu employeeDTOS
        	if(!trener.getActive()) {       		
	        	RegistrationDTO registrationDTO = new RegistrationDTO(trener.getId(), trener.getKorisnickoIme(), trener.getIme(), trener.getPrezime(), 
	        			trener.getPassword(), trener.getEmail(), trener.getDatumRodjenja(), 
	        			trener.getTelefon(), trener.getUloga(), trener.getActive());
	        	registrationDTOS.add(registrationDTO);
        	}
        }

        // Vraćamo odgovor 200 OK, a kroz body odgovora šaljemo podatke o pronađenim zaposlenima
        return new ResponseEntity<>(registrationDTOS, HttpStatus.OK);
    }
	
	@PostMapping(value = "prihvacen/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationDTO> updateTrener(@PathVariable Long id) throws Exception {
      
        // Pozivanjem metode servisa ažuriramo podatke o zaposlenom
        Trener updatedTrener = registrationService.update(id);
        updatedTrener.setId(id);
        
        // Mapiramo ažuriranog zaposlenog na DTO objekat koji vraćamo kroz body odgovora
        RegistrationDTO updatedRegDTO = new RegistrationDTO(updatedTrener.getId(), updatedTrener.getKorisnickoIme(), updatedTrener.getIme(), updatedTrener.getPrezime(), 
        		updatedTrener.getPassword(), updatedTrener.getEmail(), updatedTrener.getDatumRodjenja(), 
        		updatedTrener.getTelefon(), updatedTrener.getUloga(), updatedTrener.getActive());

        // Vraćamo odgovor 200 OK, a kroz body odgovora šaljemo podatke o ažuriranom zaposlenom
        return new ResponseEntity<>(updatedRegDTO, HttpStatus.OK);
    }
	
	@PostMapping(value = "odbijen/{id}")
    public ResponseEntity<Void> deleteTrener(@PathVariable Long id) {
        // Pozivanjem metode servisa brišemo zaposlenog po ID-ju
        this.registrationService.delete(id);

        // Vraćamo odgovor 204 NO_CONTENT koji signalizira uspešno brisanje
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
