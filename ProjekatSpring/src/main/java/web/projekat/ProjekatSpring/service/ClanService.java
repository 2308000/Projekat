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
	
	public Iterable<Clan> saveClanovi(Set<Clan> clanovi) {
		return this.clanRepository.saveAll(clanovi);
	}
}