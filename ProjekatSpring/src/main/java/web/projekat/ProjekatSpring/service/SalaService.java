package web.projekat.ProjekatSpring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.Sala;
import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.entity.DTO.SalaDTO;
import web.projekat.ProjekatSpring.repository.SalaRepository;
import web.projekat.ProjekatSpring.repository.FitnessCentarRepository;

@Service
public class SalaService {

	@Autowired 
	SalaRepository salaRepository;
	
	@Autowired
	FitnessCentarRepository fitnessCentarRepository;
	
	public List<Sala> findAllSale() {
        List<Sala> sale = this.salaRepository.findAll();
        return sale;
    }
	
	public Sala findById(Long id) {
		return this.salaRepository.getOne(id);
	}
	
	public Sala save(Sala sala) {
        return this.salaRepository.save(sala);
    }
	
	public Sala create(Sala sala) throws Exception {
        if (sala.getId() != null) {
            throw new Exception("ID must be null!");
        }
        return this.salaRepository.save(sala);
    }
	
	public Sala deleteLocically(Long id) throws Exception {
		Sala salaToDelete = this.salaRepository.getOne(id);

		salaToDelete.setObrisana(true);
        
        return this.salaRepository.save(salaToDelete);
    }
	
	public Sala update(SalaDTO salaDTO) throws Exception {
		Sala salaToUpdate = this.salaRepository.getOne(salaDTO.getId());

		if(salaDTO.getKapacitet() != 0) salaToUpdate.setKapacitet(salaDTO.getKapacitet());
		if(!salaDTO.getOznakaSale().isEmpty()) salaToUpdate.setOznakaSale(salaDTO.getOznakaSale());

        return this.salaRepository.save(salaToUpdate);
    }
	
	public FitnessCentar findCenterByID(Long id) {
		return this.fitnessCentarRepository.getOne(id);
	}
}
