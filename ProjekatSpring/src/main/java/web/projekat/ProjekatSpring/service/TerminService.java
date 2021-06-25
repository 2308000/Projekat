package web.projekat.ProjekatSpring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.projekat.ProjekatSpring.entity.DTO.SalaDTO;
import web.projekat.ProjekatSpring.entity.DTO.SearchDTO;
import web.projekat.ProjekatSpring.entity.DTO.TerminDTO;
import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.Sala;
import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.entity.Trening;
import web.projekat.ProjekatSpring.repository.FitnessCentarRepository;
import web.projekat.ProjekatSpring.repository.TerminRepository;
import web.projekat.ProjekatSpring.repository.TreningRepository;

@Service
public class TerminService {

    @Autowired
    private TerminRepository terminRepository;

    @Autowired
    private TreningRepository treningRepository;

	@Autowired
	private FitnessCentarRepository fitnessCentarRepository;
    
    public Termin findOne(Long id) {
        Termin termin = this.terminRepository.getOne(id);
        return termin;
    }

    public List<Termin> findAll() {
        List<Termin> termini = this.terminRepository.findAll();
        return termini;
    }
    
    public Trening findTrainingByName(String name) {
    	List<Trening> treninzi = this.treningRepository.findAll();
    	Trening retVal = null;
    	for(Trening trening : treninzi) {
    		if(trening.getNazivTreninga().equals(name)) {
    			retVal = trening;
    		}
    	}
    	return retVal;
	}
    
    public List<TerminDTO> findAllByCriteria(SearchDTO searchDTO){
        List<Termin> termini = this.terminRepository.findAll();
        
        List<TerminDTO> terminDTOS = new ArrayList<>();
        
        for (Termin termin : termini) {
	         TerminDTO terminDTO = new TerminDTO(termin.getId(), termin.getPocetakTermina(),
	                 termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
	                 termin.getTrening().getOpis(), termin.getTrening().getTip());
	         		 terminDTOS.add(terminDTO);
        }    
        if(searchDTO.isSve()) {
            return terminDTOS;
        }
        
        List<TerminDTO> cene  = new ArrayList<>();
        for (TerminDTO termin : terminDTOS) {
        	if(termin.getCenaTermina() <= searchDTO.getCena()) {
        		cene.add(termin);
            }
        }
        List<TerminDTO> datumi  = new ArrayList<>();
        for (TerminDTO termin : cene) {
            if(termin.getKrajTermina().before(searchDTO.getDatum())) {
                datumi.add(termin);
            }
        }
        List<TerminDTO> nazivi  = new ArrayList<>();
        for (TerminDTO termin : datumi) {
            if(termin.getNazivTreninga().equalsIgnoreCase(searchDTO.getNazivTreninga()) || searchDTO.getNazivTreninga().equalsIgnoreCase("sve")) {
                nazivi.add(termin);
            }
        }
        List<TerminDTO> opisi  = new ArrayList<>();
        for (TerminDTO termin : nazivi) {
            if(termin.getOpisTreninga().equalsIgnoreCase(searchDTO.getOpisTreninga()) || searchDTO.getOpisTreninga().equalsIgnoreCase("sve")) {
                opisi.add(termin);
            }
        }
        List<TerminDTO> tipovi  = new ArrayList<>();
        for (TerminDTO termin : opisi) {
            if(termin.getTipTreninga().equalsIgnoreCase(searchDTO.getTipTreninga()) || searchDTO.getTipTreninga().equalsIgnoreCase("sve")) {
                tipovi.add(termin);
            }
        }
       return tipovi;
        
    }

    public Termin save(Termin termin) {
        return this.terminRepository.save(termin);
    }

    public Termin create(Termin termin) throws Exception {
        if (termin.getId() != null) {
            throw new Exception("ID must be null!");
        }
        return this.terminRepository.save(termin);
    }
    
    public void delete(Long id) {
        this.terminRepository.deleteById(id);
    }
    
    public FitnessCentar findCenterByID(Long id) {
		return this.fitnessCentarRepository.getOne(id);
	}
    
    public Termin update(Termin termin) throws Exception {
		Termin terminToUpdate = this.terminRepository.getOne(termin.getId());
		
		if(termin.getCenaTermina() != null && termin.getCenaTermina() != 0) { terminToUpdate.setCenaTermina(termin.getCenaTermina()); }
		if(termin.getPocetakTermina() != null) { terminToUpdate.setPocetakTermina(termin.getPocetakTermina()); }
		if(termin.getKrajTermina() != null) { terminToUpdate.setKrajTermina(termin.getKrajTermina()); }
		if(termin.getTrajanjeTermina() != 0) { terminToUpdate.setTrajanjeTermina(termin.getTrajanjeTermina()); }
		terminToUpdate.setSala(termin.getSala());
		terminToUpdate.setTrener(termin.getTrener());
        return this.terminRepository.save(terminToUpdate);
    }
    
    public Iterable<Termin> saveOdradjeni(Set<Termin> odradjeni) {
        return this.terminRepository.saveAll(odradjeni);
    }
    
}