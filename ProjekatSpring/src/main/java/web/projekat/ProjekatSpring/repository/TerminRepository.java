package web.projekat.ProjekatSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.projekat.ProjekatSpring.entity.Termin;

public interface TerminRepository extends JpaRepository<Termin, Long>{
}
