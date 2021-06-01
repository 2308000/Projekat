package web.projekat.ProjekatSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.repository.FitnessCentarRepository;

@Service
public class FitnessCentarService {
	
	@Autowired 
	FitnessCentarRepository fitnessCentarRepository;
	
	/*public List<Trener> findAllTrainers() {
        List<Trener> treneri = this.trenerRepository.findAll();
        return treneri;
    }*/
	
	public FitnessCentar save(FitnessCentar fitnessCentar) {
        return this.fitnessCentarRepository.save(fitnessCentar);
    }
	
	public FitnessCentar create(FitnessCentar fitnessCentar) throws Exception {
        if (fitnessCentar.getId() != null) {
            throw new Exception("ID must be null!");
        }
        return this.fitnessCentarRepository.save(fitnessCentar);
    }
	
	public FitnessCentar delete(Long id) throws Exception {
		FitnessCentar fitnessCentarToUpdate = this.fitnessCentarRepository.getOne(id);
        if (fitnessCentarToUpdate == null) {
            throw new Exception("Fitness Center doesn't exist!");
        }

        fitnessCentarToUpdate.setObrisan(true);
        
        return this.fitnessCentarRepository.save(fitnessCentarToUpdate);
    }
}
