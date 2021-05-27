package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.entity.DTO.TerminDTO;
import web.projekat.ProjekatSpring.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.*;

@RestController
@RequestMapping(value = "/api/termini")
public class TerminController {

	 private final TerminService terminService;

	// constructor-based dependency injection
	 @Autowired
	 public TerminController(TerminService terminService) {
	    this.terminService = terminService;
     }
	 
	 /*@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<TerminDTO> gettermin(@PathVariable("id") Long id) {
	        // Pozivanjem metode servisa dobavljamo zaposlenog po ID-ju
		  	Termin termin = this.terminService.findOne(id);

	        // Kreiramo objekat klase EmployeeDTO koji ćemo vratiti u odgovoru na zahtev
	        TerminDTO terminDTO = new TerminDTO();
	        terminDTO.setId(termin.getId());
	        terminDTO.setPocetakTermina(termin.getPocetakTermina());
	        termin.setKrajTermina(termin.getKrajTermina());
	        termin.setTrajanjeTermina(termin.getTrajanjeTermina());
	        termin.setCenaTermina(termin.getCenaTermina());
	         
	        return new ResponseEntity<>(terminDTO, HttpStatus.OK);
	    }*/
	 @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<TerminDTO>> getTermini() {
		 // Pozivanjem metode servisa dobavljamo sve zaposlene
		 List<Termin> terminList = this.terminService.findAll();

		 // Kreiramo listu DTO objekata koju ćemo vratiti u odgovoru na zahtev
	     List<TerminDTO> terminDTOS = new ArrayList<>();

	     for (Termin termin : terminList) {
	    	 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	    		termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina());
	    	 	terminDTOS.add(terminDTO);
	     }

	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
}
