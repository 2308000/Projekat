package web.projekat.ProjekatSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.projekat.ProjekatSpring.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
}
