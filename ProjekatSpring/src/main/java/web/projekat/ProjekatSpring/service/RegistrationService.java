package web.projekat.ProjekatSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.repository.ClanRepository;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.repository.TrenerRepository;

@Service
public class RegistrationService {
	
	@Autowired
    private ClanRepository clanRepository;
	
	@Autowired
	private TrenerRepository trenerRepository;
	
	public List<Trener> findAllTrainers() {
        List<Trener> treneri = this.trenerRepository.findAll();
        return treneri;
    }
	
	public Clan save(Clan clan) {
        return this.clanRepository.save(clan);
    }
	
	public Trener save(Trener trener) {
        return this.trenerRepository.save(trener);
    }
	
	public Clan create(Clan clan) throws Exception {
        if (clan.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Clan noviClan = this.clanRepository.save(clan);
        return noviClan;
    }
	
	public Trener create(Trener trener) throws Exception {
        if (trener.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Trener noviTrener = this.trenerRepository.save(trener);
        return noviTrener;
    }
	
	public Trener update(Long id) throws Exception {
    	Trener trenerToUpdate = this.trenerRepository.getOne(id);
        if (trenerToUpdate == null) {
            throw new Exception("Employee doesn't exist!");
        }

        // Postavljanje novog radnog mesta
        trenerToUpdate.setActive(true);

        // Čuvanje u bazi
        return this.trenerRepository.save(trenerToUpdate);
    }
	
	public void delete(Long id) {
        this.trenerRepository.deleteById(id);
    }
	
}
