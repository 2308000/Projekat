package web.projekat.ProjekatSpring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.projekat.ProjekatSpring.entity.DTO.SearchDTO;
import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.repository.TerminRepository;

@Service
public class TerminService {

    @Autowired
    private TerminRepository terminRepository;

    public Termin findOne(Long id) {
        Termin termin = this.terminRepository.getOne(id);
        return termin;
    }

    public List<Termin> findAll() {
        List<Termin> termini = this.terminRepository.findAll();
        return termini;
    }
    
    public List<Termin> findAllByCriteria(SearchDTO searchDTO){
        List<Termin> termini = this.terminRepository.findAll();
        if(searchDTO.isSve()) {
            return termini;
        }
        List<Termin> cene  = new ArrayList<>();
        for (Termin termin : termini) {
        	if(termin.getCenaTermina() <= searchDTO.getCena()) {
        		cene.add(termin);
            }
        }
            List<Termin> trajanja  = new ArrayList<>();
            for (Termin termin : cene) {
                if(termin.getTrajanjeTermina() <= searchDTO.getTrajanje()) {
                    trajanja.add(termin);
                }
            }
            return trajanja;
        
    }

    public Termin save(Termin termin) {
        return this.terminRepository.save(termin);
    }

    public void delete(Long id) {
        this.terminRepository.deleteById(id);
    }
    
}