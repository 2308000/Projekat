package web.projekat.ProjekatSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.projekat.ProjekatSpring.entity.Trening;

public interface TreningRepository extends JpaRepository<Trening, Long>{
}