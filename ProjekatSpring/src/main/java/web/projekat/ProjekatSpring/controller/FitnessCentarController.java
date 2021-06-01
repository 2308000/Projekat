package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.DTO.FitnessCentarDTO;
import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.service.FitnessCentarService;

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
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/novi")
    public ResponseEntity<FitnessCentarDTO> createFitnessCentar(@RequestBody FitnessCentarDTO fitnessCentarDTO) throws Exception {
        FitnessCentar fitnessCentar = new FitnessCentar(fitnessCentarDTO.getNazivCentra(), fitnessCentarDTO.getAdresa(), 
        		fitnessCentarDTO.getBrojTelefonaCentra(), fitnessCentarDTO.getEmailCentra(), false); //fitnessCentarDTO
        /*FitnessCentar(String nazivCentra, String adresa, String brojTelefonaCentra, String emailCentra,
			boolean obrisan)*/
        FitnessCentar noviFitnessCentar = fitnessCentarService.create(fitnessCentar);

        FitnessCentarDTO noviFitnessCentarDTO = new FitnessCentarDTO(noviFitnessCentar.getNazivCentra(), noviFitnessCentar.getAdresa(),
        		noviFitnessCentar.getBrojTelefonaCentra(), noviFitnessCentar.getEmailCentra(), false); //noviFitnessCentar
        
        return new ResponseEntity<>(noviFitnessCentarDTO, HttpStatus.CREATED);
    }
}
