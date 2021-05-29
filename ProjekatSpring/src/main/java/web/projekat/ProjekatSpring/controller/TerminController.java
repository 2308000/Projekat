package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.DTO.SearchDTO;
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
	 
	 @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<TerminDTO>> getTermini() {
		 List<Termin> terminList = this.terminService.findAll();

	     List<TerminDTO> terminDTOS = new ArrayList<>();

	     for (Termin termin : terminList) {
	    	 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	    		termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina());
	    	 	terminDTOS.add(terminDTO);
	     }

	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/pretragaPoKriterijumu")
	 public ResponseEntity<List<TerminDTO>> getTerminiByCriteria(@RequestBody SearchDTO getDTO) throws Exception {
	     SearchDTO searchDTO = new SearchDTO(getDTO.isSve(), getDTO.getTrajanje(), getDTO.getCena());
	     List<Termin> terminiList = this.terminService.findAllByCriteria(searchDTO);
	
	     List<TerminDTO> terminDTOS = new ArrayList<>();
	     
	     for (Termin termin : terminiList) {
	         TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	                 termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina());
	         terminDTOS.add(terminDTO);
	     }
	     
	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
}
