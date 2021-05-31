package web.projekat.ProjekatSpring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.repository.ClanRepository;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.repository.TrenerRepository;
import web.projekat.ProjekatSpring.entity.Administrator;
import web.projekat.ProjekatSpring.repository.AdministratorRepository;
import web.projekat.ProjekatSpring.entity.DTO.LoginDTO;

@Service
public class LoginService {

	@Autowired
    private ClanRepository clanRepository;
	
	@Autowired
	private TrenerRepository trenerRepository;
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	public List<Clan> findAllMembers() {
        List<Clan> clanovi = this.clanRepository.findAll();
        return clanovi;
    }
	
	public List<Trener> findAllTrainers() {
        List<Trener> treneri = this.trenerRepository.findAll();
        return treneri;
    }
	
	public List<Administrator> findAllAdmins() {
        List<Administrator> administratori = this.administratorRepository.findAll();
        return administratori;
    }
}
