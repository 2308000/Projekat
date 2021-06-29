package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.DTO.SearchDTO;
import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.Sala;
import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.entity.Trening;
import web.projekat.ProjekatSpring.entity.DTO.TerminDTO;
import web.projekat.ProjekatSpring.service.FitnessCentarService;
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
@RequestMapping(value = "/api/raspored")
public class TerminController {

	 private final TerminService terminService;
	 private final FitnessCentarService fitnessCentarService;
	 private final SalaService salaService;
	 private final TrenerService trenerService;
	// constructor-based dependency injection
	 @Autowired
	 public TerminController(TerminService terminService, FitnessCentarService fitnessCentarService, SalaService salaService, TrenerService trenerService) {
	    this.terminService = terminService;
	    this.fitnessCentarService = fitnessCentarService;
	    this.salaService = salaService;
	    this.trenerService = trenerService;	    
     }
	 
	 @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<TerminDTO>> getTermini() {
		 List<Termin> terminList = this.terminService.findAll();

	     List<TerminDTO> terminDTOS = new ArrayList<>();

	     for (Termin termin : terminList) {
	    	 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	    		termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
	    		termin.getTrening().getOpis(), termin.getTrening().getTip());
	    	 	terminDTOS.add(terminDTO);
	     }

	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/clan")
	 public ResponseEntity<List<TerminDTO>> getTerminForClan(@RequestBody TerminDTO getDTO) throws Exception {  
	     List<Termin> terminList = this.terminService.findAll();

	     List<TerminDTO> terminDTOS = new ArrayList<>();
	     
	     if(getDTO.getZastita() == null || !getDTO.getZastita().equals("clan")) {
	    	 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	     }
	     
	     for (Termin termin : terminList) {
	    	 TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	    		termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
	    		termin.getTrening().getOpis(), termin.getTrening().getTip());
	    	 	terminDTOS.add(terminDTO);
	     }

	     return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	 }
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/trener")
	 public ResponseEntity<List<TerminDTO>> getTerminForTrainer(@RequestBody TerminDTO getDTO) throws Exception {
		 //System.out.println(id);
		 List<Termin> terminList = this.terminService.findAll();
		 Trener trener = this.trenerService.findOne(getDTO.getId());
	     List<TerminDTO> terminDTOS = new ArrayList<>();

	     if(getDTO.getZastita() == null || !getDTO.getZastita().equals("trener")) {
	    	 return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	     }
	     for (Termin termin : terminList) {
	    	 if(trener.getId() == termin.getTrener().getId()) {
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
             value = "/pretragaPoKriterijumu")
	 public ResponseEntity<List<TerminDTO>> getTerminiByCriteria(@RequestBody SearchDTO getDTO) throws Exception {
	     SearchDTO searchDTO = new SearchDTO(getDTO.isSve(), getDTO.getDatum(), getDTO.getCena(), getDTO.getNazivTreninga(), 
	    		 getDTO.getOpisTreninga(), getDTO.getTipTreninga());
	     List<TerminDTO> terminiList = this.terminService.findAllByCriteria(searchDTO);
	     
	     return new ResponseEntity<>(terminiList, HttpStatus.OK);
	 }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE,
             value = "/pretragaPoKriterijumu/clan")
	 public ResponseEntity<List<TerminDTO>> getTerminiByCriteriaForClan(@RequestBody SearchDTO getDTO) throws Exception {
	     SearchDTO searchDTO = new SearchDTO(getDTO.isSve(), getDTO.getDatum(), getDTO.getCena(), getDTO.getNazivTreninga(), 
	    		 getDTO.getOpisTreninga(), getDTO.getTipTreninga());
	     List<TerminDTO> terminiList = this.terminService.findAllByCriteria(searchDTO);
	     
	     return new ResponseEntity<>(terminiList, HttpStatus.OK);
	 }
	 
	 @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/getSale")
	    public ResponseEntity<List<TerminDTO>> getSale(@RequestBody TerminDTO terminDTO) {

	        List<TerminDTO> terminDTOS = new ArrayList<>();    
	        Trener trener = this.trenerService.findOne(terminDTO.getId());
	        Set<Sala> listaSala = trener.getFitnessCentar().getSale();	
	        if(!terminDTO.getZastita().equals("trener")) {
	        	return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	        }
	        for(Sala sala : listaSala) {
	        	TerminDTO terminDTO2 = new TerminDTO(sala.getId()); 
	        	terminDTOS.add(terminDTO2); 
	        }
	        	
	        return new ResponseEntity<>(terminDTOS, HttpStatus.OK);	
		}
	 
	 
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE, value = "/noviTermin")
	    public ResponseEntity<TerminDTO> dodavanjeTermina(@RequestBody TerminDTO terminDTO) throws Exception {
		 Trener trener = this.trenerService.findOne(terminDTO.getId());
		 Trening trening = this.terminService.findTrainingByName(terminDTO.getNazivTreninga());
		 Sala sala = this.salaService.findById(terminDTO.getSalaID());
		 	FitnessCentar fitnessCentar = terminService.findCenterByID(trener.getFitnessCentar().getId());
		 	Long centarID = fitnessCentar.getId();
		 	/*TerminDTO(Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String zastita, Long centarID, Long salaID)*/
		 	TerminDTO terminDTO2 = new TerminDTO(terminDTO.getId(), terminDTO.getPocetakTermina(), terminDTO.getKrajTermina(), terminDTO.getTrajanjeTermina(),
		 			terminDTO.getCenaTermina(), terminDTO.getNazivTreninga(), terminDTO.getZastita(), centarID, terminDTO.getSalaID());
		 	
		 	/*String nazivTreninga = "";
		 	String opisTreninga = "";
		 	for(Trening trening : listaTreninga) {
		 		if(trening.getNazivTreninga().equals(terminDTO2.getNazivTreninga())) {
		 			nazivTreninga.equals(trening.getNazivTreninga());
		 			opisTreninga.equals(trening.getOpis());
		 		}
		 	}*/
		 	/*Trening(String nazivTreninga, String opis, String tip)*/
		 	Termin termin = new Termin(terminDTO2.getPocetakTermina(), terminDTO2.getKrajTermina(), terminDTO2.getTrajanjeTermina(), terminDTO2.getCenaTermina(), trening, trener, sala);
	        /*public Termin(Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina, trening, trener, sala)*/
		 	
	        if(!terminDTO2.getZastita().equals("trener")) {
		 		TerminDTO newTerminDTO = new TerminDTO(Long.valueOf(0), trener.getDatumRodjenja(), trener.getDatumRodjenja(), 0, 0, "", "", "", "greska", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		 		/*public TerminDTO(Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
				String nazivTreninga, String opisTreninga, String tipTreninga, String zastita, Long centarID, Long salaID)*/
		 		return new ResponseEntity<>(newTerminDTO, HttpStatus.CREATED);
	        }
	        
	        FitnessCentar centar = this.fitnessCentarService.findById(centarID);
	        Set<Sala> sale = centar.getSale();
	        for(Sala s : sale) {
	        	//System.out.println("stigao u for za sale");
	        	if(s.getId() == terminDTO2.getSalaID()) {
	        		//System.out.println("stigao u uslov");
	        		Set<Termin> termini = s.getTermini();
	        		for(Termin termin2 : termini) {
	        			if(terminDTO2.getPocetakTermina().after(termin2.getPocetakTermina()) && terminDTO2.getPocetakTermina().before(termin2.getKrajTermina())) {
	        				//System.out.println("stigao u uslov za pocetak");
	        				TerminDTO newTerminDTO = new TerminDTO(Long.valueOf(0), trener.getDatumRodjenja(), trener.getDatumRodjenja(), 0, 0, "", "", "", "pocetak", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
	        				return new ResponseEntity<>(newTerminDTO, HttpStatus.CREATED);
	        			}
	        			if(terminDTO2.getKrajTermina().after(termin2.getPocetakTermina()) && terminDTO2.getKrajTermina().before(termin2.getKrajTermina())) {
	        				System.out.println("stigao u uslov za kraj");
	        				TerminDTO newTerminDTO = new TerminDTO(Long.valueOf(0), trener.getDatumRodjenja(), trener.getDatumRodjenja(), 0, 0, "", "", "", "kraj", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
	        				return new ResponseEntity<>(newTerminDTO, HttpStatus.CREATED);	        				
	        			}
	        		}
	        	}
	        }
	        
	        Termin noviTermin = this.terminService.create(termin);
	        TerminDTO newTerminDTO = new TerminDTO(noviTermin.getId(), noviTermin.getPocetakTermina(), noviTermin.getKrajTermina(), noviTermin.getTrajanjeTermina(),
	        		noviTermin.getCenaTermina(), noviTermin.getTrening().getNazivTreninga(), noviTermin.getTrening().getOpis(), noviTermin.getTrening().getTip(), "validan", 
	        		centarID, noviTermin.getSala().getId(), noviTermin.getTrener().getId());
  	
	        return new ResponseEntity<>(newTerminDTO, HttpStatus.CREATED);
	    }
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/izmeni")
    public ResponseEntity<TerminDTO> updateTermin(@RequestBody TerminDTO terminDTO) throws Exception {
		Sala sala = this.salaService.findById(terminDTO.getSalaID());
		Trening trening = this.terminService.findTrainingByName(terminDTO.getNazivTreninga());
		/*List<Sala> sale = this.salaService.findAllSale();
		for(Sala sala : sale) {
			if(sala.getId() == termin2.getSala().getId()) {
				salaID = sala.getId();
			}
		}
		Sala sala = this.salaService.findById(salaID);*/
		//System.out.println("ID sale je " + sala.getId());
		Trener trener = this.trenerService.findOne(terminDTO.getTrenerID());
		//System.out.println("ID trenera je " + trener.getId());
		FitnessCentar fitnessCentar = terminService.findCenterByID(trener.getFitnessCentar().getId());
		Long centarID = fitnessCentar.getId();
        
        if(!terminDTO.getZastita().equals("trener")) {
        	TerminDTO newTerminDTO = new TerminDTO(Long.valueOf(0), trener.getDatumRodjenja(), trener.getDatumRodjenja(), 0, 0, "", "", "", "greska", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
			return new ResponseEntity<>(newTerminDTO, HttpStatus.CREATED);
        }
        
        Set<Sala> sale = fitnessCentar.getSale();
        for(Sala s : sale) {
        	//System.out.println("stigao u for za sale");
        	if(s.getId() == terminDTO.getSalaID()) {
        		//System.out.println("stigao u uslov");
        		Set<Termin> termini = s.getTermini();
        		for(Termin termin3 : termini) {
        			if(terminDTO.getPocetakTermina() == null || terminDTO.getKrajTermina() == null) continue;
        			if(terminDTO.getPocetakTermina().after(termin3.getPocetakTermina()) && terminDTO.getPocetakTermina().before(termin3.getKrajTermina())) {
        				//System.out.println("stigao u uslov za pocetak");
        				TerminDTO newTerminDTO = new TerminDTO(Long.valueOf(0), trener.getDatumRodjenja(), trener.getDatumRodjenja(), 0, 0, "", "", "", "pocetak", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
        				return new ResponseEntity<>(newTerminDTO, HttpStatus.CREATED);
        			}
        			if(terminDTO.getKrajTermina().after(termin3.getPocetakTermina()) && terminDTO.getKrajTermina().before(termin3.getKrajTermina())) {
        				TerminDTO newTerminDTO = new TerminDTO(Long.valueOf(0), trener.getDatumRodjenja(), trener.getDatumRodjenja(), 0, 0, "", "", "", "kraj", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
        				return new ResponseEntity<>(newTerminDTO, HttpStatus.CREATED);	        				
        			}
        		}
        	}
        }
        
        Termin termin = new Termin(terminDTO.getId(), terminDTO.getPocetakTermina(), terminDTO.getKrajTermina(), terminDTO.getTrajanjeTermina(), terminDTO.getCenaTermina(), trening, trener, sala);
	    Termin noviTermin = this.terminService.update(termin);	
	    /*System.out.println("ID Sale po terminu je " + noviTermin.getSala().getId());
	    System.out.println("ID Trenera po terminu je " + noviTermin.getTrener().getId());*/
	    TerminDTO updatedTerminDTO = new TerminDTO(noviTermin.getId(), noviTermin.getPocetakTermina(), noviTermin.getKrajTermina(), noviTermin.getTrajanjeTermina(),
        		noviTermin.getCenaTermina(), noviTermin.getTrening().getNazivTreninga(), noviTermin.getTrening().getOpis(), noviTermin.getTrening().getTip(), "validan", 
        		centarID, noviTermin.getSala().getId(), noviTermin.getTrener().getId());
	    /*TerminDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String opisTreninga, String tipTreninga, String zastita, Long centarID, Long salaID, Long trenerID)*/
        return new ResponseEntity<>(updatedTerminDTO, HttpStatus.CREATED);   
	}
}
