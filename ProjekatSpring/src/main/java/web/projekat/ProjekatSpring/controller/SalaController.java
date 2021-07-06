package web.projekat.ProjekatSpring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.Sala;
import web.projekat.ProjekatSpring.entity.DTO.FitnessCentarDTO;
import web.projekat.ProjekatSpring.entity.DTO.SalaDTO;
import web.projekat.ProjekatSpring.service.SalaService;

@RestController
@RequestMapping(value = "/api/sala")
public class SalaController {
	
	private final SalaService salaService;
	
	@Autowired
	 public SalaController(SalaService salaService) {
	    this.salaService = salaService;
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/dodavanje")
    public ResponseEntity<SalaDTO> createSala(@RequestBody SalaDTO salaDTO) throws Exception {
		
		FitnessCentar fitnessCentar = salaService.findCenterByID(salaDTO.getCentarID());
		
		SalaDTO fakeSala = new SalaDTO(0, "", false, Long.valueOf(0), "greska");
		/*SalaDTO(Integer kapacitet, String oznakaSale, boolean obrisana, Long centarID)*/
        Sala sala = new Sala(salaDTO.getKapacitet(), salaDTO.getOznakaSale(), salaDTO.isObrisana(), fitnessCentar); 
        
        if(salaDTO.getZastita().equals("admin")) {
			List<Sala> sale = salaService.findAllSale();
        	for(Sala sala2 : sale) {
        		if(salaDTO.getOznakaSale().equals(sala2.getOznakaSale())) {
        			SalaDTO noviSalaDTO = new SalaDTO(0, "", false, Long.valueOf(0), "oznaka"); 
        			return new ResponseEntity<>(noviSalaDTO, HttpStatus.CREATED);
        		}
        	}
        	
        	Sala novaSala = salaService.create(sala);
	
	        SalaDTO noviSalaDTO = new SalaDTO(novaSala.getKapacitet(), novaSala.getOznakaSale(), novaSala.isObrisana(), novaSala.getFitnessCentar().getId(), "admin"); 
	        
	        return new ResponseEntity<>(noviSalaDTO, HttpStatus.CREATED);
        } else {
        	return new ResponseEntity<>(fakeSala, HttpStatus.CREATED);
        }
    }
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/prikaz")
    public ResponseEntity<List<SalaDTO>> getAllCenters(@RequestBody SalaDTO salaDTO) {
        List<Sala> listaSala = this.salaService.findAllSale();

        List<SalaDTO> salaDTOS = new ArrayList<>();
        if(salaDTO.getZastita() == null) {
        	return new ResponseEntity<>(salaDTOS, HttpStatus.OK);
        }
        if(salaDTO.getZastita().equals("admin")) {
	        for (Sala sala : listaSala) {
	        	if(!sala.isObrisana() && !sala.getFitnessCentar().isObrisan()) {
	        		SalaDTO salaDTO2 = new SalaDTO(sala.getId(), sala.getKapacitet(), sala.getOznakaSale(), sala.isObrisana(), sala.getFitnessCentar().getId(), "admin"); 
	        		salaDTOS.add(salaDTO2);
	        	} /*SalaDTO(Integer kapacitet, String oznakaSale, boolean obrisana, Long centarID)*/
	        }
	        return new ResponseEntity<>(salaDTOS, HttpStatus.OK);
        } else {
			List<SalaDTO> lista = new ArrayList<>();
			return new ResponseEntity<>(lista, HttpStatus.OK);
	    }
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/izmeni")
    public ResponseEntity<SalaDTO> updateSala(@RequestBody SalaDTO salaDTO) throws Exception {
        List<Sala> listaSala = this.salaService.findAllSale();
        
        if(!salaDTO.getZastita().equals("admin")) {
        	SalaDTO neizmenjeniSalaDTO= new SalaDTO(Long.valueOf(0), 0, "", false, Long.valueOf(0), "greska"); 
			return new ResponseEntity<>(neizmenjeniSalaDTO, HttpStatus.CREATED);
        }
	    for (Sala sala : listaSala) {
	    	if(salaDTO.getOznakaSale().equalsIgnoreCase(sala.getOznakaSale())) {
	    		SalaDTO neizmenjeniSalaDTO= new SalaDTO(Long.valueOf(0), 0, "", false, Long.valueOf(0), "oznaka"); 
				return new ResponseEntity<>(neizmenjeniSalaDTO, HttpStatus.CREATED);
    		}
	    } 
	    Sala updateSala = this.salaService.update(salaDTO);	
	    
	    SalaDTO updatedSala = new SalaDTO(updateSala.getId(), updateSala.getKapacitet(), updateSala.getOznakaSale(),
	    		updateSala.isObrisana(), updateSala.getFitnessCentar().getId(), "admin"); 
	    
	    return new ResponseEntity<>(updatedSala, HttpStatus.OK);     
	}
	
	@PutMapping(value = "/brisanje/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalaDTO> deleteCenter(@PathVariable Long id) throws Exception {
      
        Sala salaToDelete = salaService.deleteLocically(id);
        
        SalaDTO deleteSala = new SalaDTO(salaToDelete.getId(), salaToDelete.getKapacitet(), salaToDelete.getOznakaSale(), salaToDelete.isObrisana()
        		, salaToDelete.getFitnessCentar().getId(),"admin");

        return new ResponseEntity<>(deleteSala, HttpStatus.OK);
    }
}
