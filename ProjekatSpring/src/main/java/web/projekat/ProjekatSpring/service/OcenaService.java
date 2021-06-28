package web.projekat.ProjekatSpring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.projekat.ProjekatSpring.entity.Ocena;
import web.projekat.ProjekatSpring.repository.OcenaRepository;

@Service
public class OcenaService {

	@Autowired
	OcenaRepository ocenaRepository;
	
	public List<Ocena> findAll() {
		return this.ocenaRepository.findAll();
	}
	
	public Ocena create(Ocena ocena) throws Exception {
        if (ocena.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Ocena novaOcena = this.ocenaRepository.save(ocena);
        return novaOcena;
    }
}
