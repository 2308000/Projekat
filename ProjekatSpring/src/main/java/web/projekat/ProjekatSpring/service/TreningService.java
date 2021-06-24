package web.projekat.ProjekatSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.projekat.ProjekatSpring.entity.Trening;
import web.projekat.ProjekatSpring.repository.TreningRepository;

@Service
public class TreningService {
	
	@Autowired
	TreningRepository treningRepository;
	
	public Trening create(Trening trening) throws Exception {
        if (trening.getId() != null) {
            throw new Exception("ID must be null!");
        }
        return this.treningRepository.save(trening);
    }
}
