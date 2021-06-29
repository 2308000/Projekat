package web.projekat.ProjekatSpring.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.projekat.ProjekatSpring.entity.Ocena;
import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.repository.TrenerRepository;

@Service
public class TrenerService {
	
	@Autowired
	private TrenerRepository trenerRepository;
	
	public List<Trener> findAll() {
        return this.trenerRepository.findAll();   
    }
	
	public Trener findOne(Long id) {
        return this.trenerRepository.getOne(id);
    }
	
	public Trener updateAvgGrade(Trener trener) {
		Trener trenerToUpdate = this.trenerRepository.getOne(trener.getId());
		Set<Termin> termini = trener.getTermini();
		double sum = trener.getProsecnaOcena();
		double size;
		if(trener.getProsecnaOcena()==null || trener.getProsecnaOcena() == 0) {
			size = 0;
		} else {
			size = 1;
		}
		System.out.println("Prosecna ocena trenera pre je " + sum);		
		for(Termin termin : termini) {
			if(termin.getTrener().getId() == trener.getId()) {
				
				for(Ocena ocena : termin.getOcene()) {
					sum += ocena.getOcena();
				}
				System.out.println("Sve sabrane ocene iznose " + sum);
				size += termin.getOcene().size();
				System.out.println("Broj sabranih ocena iznosi " + size);
			}
		}
		sum /= size;
		System.out.println("Prosecna ocena trenera posle je " + sum);
		trenerToUpdate.setProsecnaOcena(sum);
		this.trenerRepository.save(trenerToUpdate);
		return trenerToUpdate;
	}
}
