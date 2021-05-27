package web.projekat.ProjekatSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.repository.TerminRepository;

@Service
public class TerminService {

    @Autowired
    private TerminRepository terminRepository;

    public Termin findOne(Long id) {
        Termin termin = this.terminRepository.getOne(id);
        return termin;
    }

    public List<Termin> findAll() {
        List<Termin> termin = this.terminRepository.findAll();
        return termin;
    }

    public Termin save(Termin termin) {
        return this.terminRepository.save(termin);
    }

    public void delete(Long id) {
        this.terminRepository.deleteById(id);
    }
    
}