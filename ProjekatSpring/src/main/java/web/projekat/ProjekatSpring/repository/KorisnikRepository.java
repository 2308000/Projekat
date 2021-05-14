package web.projekat.ProjekatSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.projekat.ProjekatSpring.entity.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
}
