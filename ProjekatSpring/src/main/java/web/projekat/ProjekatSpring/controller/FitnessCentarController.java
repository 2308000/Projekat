package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.DTO.FitnessCentarDTO;
import web.projekat.ProjekatSpring.entity.DTO.RegistrationDTO;
import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.service.FitnessCentarService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/fitnessCentar")
public class FitnessCentarController {

	private final FitnessCentarService fitnessCentarService;
	
	@Autowired
	 public FitnessCentarController(FitnessCentarService fitnessCentarService) {
	    this.fitnessCentarService = fitnessCentarService;
    }
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/prikaz")
    public ResponseEntity<List<FitnessCentarDTO>> getAllCenters(@RequestBody FitnessCentarDTO fitnessCentarDTO) {
        List<FitnessCentar> listaCentara = this.fitnessCentarService.findAllCenters();

        List<FitnessCentarDTO> fitnessCentarDTOS = new ArrayList<>();
        
        if(fitnessCentarDTO.getZastita().equals("admin")) {
	        for (FitnessCentar fitnessCentar : listaCentara) {
	        	if(!fitnessCentar.isObrisan()) {
		        	FitnessCentarDTO fitnessCentarDTO2 = new FitnessCentarDTO(fitnessCentar.getId(), fitnessCentar.getNazivCentra(), fitnessCentar.getAdresa(),
		        			fitnessCentar.getBrojTelefonaCentra(), fitnessCentar.getEmailCentra(), fitnessCentar.isObrisan(), "admin"); 
		        	fitnessCentarDTOS.add(fitnessCentarDTO2);
	        	}
	        }
	        return new ResponseEntity<>(fitnessCentarDTOS, HttpStatus.OK);
        } else {
			List<FitnessCentarDTO> lista = new ArrayList<>();
			return new ResponseEntity<>(lista, HttpStatus.OK);
	    }
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/novi")
    public ResponseEntity<FitnessCentarDTO> createFitnessCentar(@RequestBody FitnessCentarDTO fitnessCentarDTO) throws Exception {
		
		FitnessCentarDTO fakeFitnessCenter = new FitnessCentarDTO(Long.valueOf(0), "", "", "", "", false, "greska");
		
        FitnessCentar fitnessCentar = new FitnessCentar(fitnessCentarDTO.getNazivCentra(), fitnessCentarDTO.getAdresa(), 
        		fitnessCentarDTO.getBrojTelefonaCentra(), fitnessCentarDTO.getEmailCentra(), false); //fitnessCentarDTO
        /*FitnessCentar(String nazivCentra, String adresa, String brojTelefonaCentra, String emailCentra,
			boolean obrisan)*/
        
        if(fitnessCentarDTO.getZastita().equals("admin")) {
        	List<FitnessCentar> centri = fitnessCentarService.findAllCenters();
        	for(FitnessCentar centar : centri) {
        		if(fitnessCentarDTO.getBrojTelefonaCentra().equals(centar.getBrojTelefonaCentra())) {
        			System.out.println(fitnessCentarDTO.getBrojTelefonaCentra() + " " + centar.getBrojTelefonaCentra());
        			FitnessCentarDTO noviFitnessCentarDTO = new FitnessCentarDTO(Long.valueOf(0), "", "", "", "", false, "broj"); 
        			return new ResponseEntity<>(noviFitnessCentarDTO, HttpStatus.CREATED);
        		}
        		if(fitnessCentarDTO.getEmailCentra().equalsIgnoreCase(centar.getEmailCentra())) {
        			System.out.println(fitnessCentarDTO.getEmailCentra() + " " + centar.getEmailCentra());
        			FitnessCentarDTO noviFitnessCentarDTO = new FitnessCentarDTO(Long.valueOf(0), "", "", "", "", false, "email"); 
        			return new ResponseEntity<>(noviFitnessCentarDTO, HttpStatus.CREATED);
        		}
        	}
        	
	        FitnessCentar noviFitnessCentar = fitnessCentarService.create(fitnessCentar);
	
	        FitnessCentarDTO noviFitnessCentarDTO = new FitnessCentarDTO(noviFitnessCentar.getId(), noviFitnessCentar.getNazivCentra(), noviFitnessCentar.getAdresa(),
	        		noviFitnessCentar.getBrojTelefonaCentra(), noviFitnessCentar.getEmailCentra(), false, "admin"); 
	        
	        return new ResponseEntity<>(noviFitnessCentarDTO, HttpStatus.CREATED);
        } else {
        	return new ResponseEntity<>(fakeFitnessCenter, HttpStatus.CREATED);
        }
    }
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/izmeni")
    public ResponseEntity<FitnessCentarDTO> updateCenter(@RequestBody FitnessCentarDTO fitnessCentarDTO) throws Exception {
        List<FitnessCentar> listaCentara = this.fitnessCentarService.findAllCenters();
	    for (FitnessCentar centar : listaCentara) {
	    	if(fitnessCentarDTO.getBrojTelefonaCentra().equals(centar.getBrojTelefonaCentra())) {
    			System.out.println(fitnessCentarDTO.getBrojTelefonaCentra() + " " + centar.getBrojTelefonaCentra());
    			FitnessCentarDTO neizmenjeniFitnessCentarDTO = new FitnessCentarDTO(Long.valueOf(0), "", "", "", "", false, "broj"); 
    			return new ResponseEntity<>(neizmenjeniFitnessCentarDTO, HttpStatus.CREATED);
    		}
    		if(fitnessCentarDTO.getEmailCentra().equalsIgnoreCase(centar.getEmailCentra())) {
    			System.out.println(fitnessCentarDTO.getEmailCentra() + " " + centar.getEmailCentra());
    			FitnessCentarDTO neizmenjeniFitnessCentarDTO = new FitnessCentarDTO(Long.valueOf(0), "", "", "", "", false, "email"); 
    			return new ResponseEntity<>(neizmenjeniFitnessCentarDTO, HttpStatus.CREATED);
    		}
	    } 
	    FitnessCentar updateFitnessCentar = this.fitnessCentarService.update(fitnessCentarDTO);	
	    
	    FitnessCentarDTO updatedFitnessCentar = new FitnessCentarDTO(updateFitnessCentar.getId(), updateFitnessCentar.getNazivCentra(),
	    		updateFitnessCentar.getAdresa(), updateFitnessCentar.getBrojTelefonaCentra(), updateFitnessCentar.getEmailCentra(), false, "admin"); 
	    
	    return new ResponseEntity<>(updatedFitnessCentar, HttpStatus.OK);     
	}
	
	@PutMapping(value = "/brisanje/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FitnessCentarDTO> deleteCenter(@PathVariable Long id) throws Exception {
      
        FitnessCentar updatedFitnessCentar = fitnessCentarService.delete(id);
        
        FitnessCentarDTO updatedFitnesscentarDTO = new FitnessCentarDTO(updatedFitnessCentar.getId(), updatedFitnessCentar.getNazivCentra(),
        		updatedFitnessCentar.getAdresa(), updatedFitnessCentar.getBrojTelefonaCentra(), updatedFitnessCentar.getEmailCentra(), updatedFitnessCentar.isObrisan(), "admin");

        return new ResponseEntity<>(updatedFitnesscentarDTO, HttpStatus.OK);
    }
}
