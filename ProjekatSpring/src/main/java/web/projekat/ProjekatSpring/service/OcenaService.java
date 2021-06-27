package web.projekat.ProjekatSpring.service;

import java.util.List;

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
}
