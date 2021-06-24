package web.projekat.ProjekatSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.projekat.ProjekatSpring.entity.FitnessCentar;
import web.projekat.ProjekatSpring.entity.DTO.FitnessCentarDTO;
import web.projekat.ProjekatSpring.repository.FitnessCentarRepository;

@Service
public class FitnessCentarService {
	
	@Autowired 
	FitnessCentarRepository fitnessCentarRepository;
	
	public List<FitnessCentar> findAllCenters() {
        List<FitnessCentar> centri = this.fitnessCentarRepository.findAll();
        return centri;
    }
	
	public FitnessCentar findById(Long id) {
		return this.fitnessCentarRepository.getOne(id);
	}
	
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

        fitnessCentarToUpdate.setObrisan(true);
        
        return this.fitnessCentarRepository.save(fitnessCentarToUpdate);
    }
	
	public FitnessCentar update(FitnessCentarDTO fitnessCentarDTO) throws Exception {
		FitnessCentar fitnessCentarToUpdate = this.fitnessCentarRepository.getOne(fitnessCentarDTO.getId());

        // Postavljanje novog radnog mesta
		if(!fitnessCentarDTO.getNazivCentra().isEmpty()) fitnessCentarToUpdate.setNazivCentra(fitnessCentarDTO.getNazivCentra());
        if(!fitnessCentarDTO.getAdresa().isEmpty()) fitnessCentarToUpdate.setAdresa(fitnessCentarDTO.getAdresa());
        if(!fitnessCentarDTO.getBrojTelefonaCentra().isEmpty()) fitnessCentarToUpdate.setNazivCentra(fitnessCentarDTO.getBrojTelefonaCentra());
        if(!fitnessCentarDTO.getEmailCentra().isEmpty()) fitnessCentarToUpdate.setEmailCentra(fitnessCentarDTO.getEmailCentra());

        // Čuvanje u bazi
        return this.fitnessCentarRepository.save(fitnessCentarToUpdate);
    }
}
