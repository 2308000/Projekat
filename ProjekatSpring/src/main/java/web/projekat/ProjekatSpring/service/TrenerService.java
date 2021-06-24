package web.projekat.ProjekatSpring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
