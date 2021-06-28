package web.projekat.ProjekatSpring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.repository.ClanRepository;

@Service
public class ClanService {
	
	@Autowired
	private ClanRepository clanRepository;
	
	public List<Clan> findAll() {
        return this.clanRepository.findAll();   
    }
	
	public Clan findById(Long id) {
        return this.clanRepository.getOne(id);
    }
	
	public Clan update(Clan clan) {
		Clan clanToUpdate = this.clanRepository.getOne(clan.getId());
		
		if(clan.getOcene() != null) clanToUpdate.setOcene(clan.getOcene());
		if(clan.getOcenjeniTermini() != null) clanToUpdate.setOcenjeniTermini(clan.getOcenjeniTermini());
        
        return this.clanRepository.save(clanToUpdate);
	}
}