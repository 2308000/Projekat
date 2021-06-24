package web.projekat.ProjekatSpring.controller;

import web.projekat.ProjekatSpring.entity.DTO.LoginDTO;
import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.entity.Administrator;
import web.projekat.ProjekatSpring.service.LoginService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/login")
public class LoginController {

	private final LoginService loginService;
	
	@Autowired
	 public LoginController(LoginService loginService) {
	    this.loginService = loginService;
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {
        LoginDTO loginDTO2 = new LoginDTO(loginDTO.getId(), loginDTO.getKorisnickoIme(), loginDTO.getError(),
        		loginDTO.getPassword(), loginDTO.getUloga());
        /*LoginDTO(Long id, String korisnickoIme, String err1, String password, String err2, String uloga)*/
        List<Administrator> administratori = loginService.findAllAdmins();
        List<Trener> treneri = loginService.findAllTrainers();
        List<Clan> clanovi = loginService.findAllMembers();
        
        for(Administrator admin : administratori) {
        	if(admin.getActive()) {
        		if(admin.getKorisnickoIme().equalsIgnoreCase(loginDTO2.getKorisnickoIme())) {
        			if(admin.getPassword().equals(loginDTO2.getPassword())) {
        				loginDTO2.setUloga("admin");
        				loginDTO2.setId(admin.getId());
        				loginDTO2.setError("Welcome " + loginDTO2.getKorisnickoIme() + "!");
        				return new ResponseEntity<>(loginDTO2, HttpStatus.OK);
        			} else {
        				loginDTO2.setError("Password is incorrect!");
        				return new ResponseEntity<>(loginDTO2, HttpStatus.OK);
        			}
        		}
        	}
        }
        
        for(Trener trener : treneri) {
	        	if(trener.getActive()) {
	        		//System.out.println(trener.getKorisnickoIme() + ", da li je obrisan? " + trener.getObrisan());
	        		if(!trener.getObrisan()) {
		        		if(trener.getKorisnickoIme().equalsIgnoreCase(loginDTO2.getKorisnickoIme())) {
		        			if(trener.getPassword().equals(loginDTO2.getPassword())) {
		        				loginDTO2.setUloga("trener");
		        				loginDTO2.setId(trener.getId());
		        				loginDTO2.setError("Welcome " + loginDTO2.getKorisnickoIme() + "!");
		        				return new ResponseEntity<>(loginDTO2, HttpStatus.OK);
		        			} else {
		        				loginDTO2.setError("Password is incorrect!");
		        				return new ResponseEntity<>(loginDTO2, HttpStatus.OK);
		        			}
		        		}
	        		} else {
	        			if(trener.getKorisnickoIme().equalsIgnoreCase(loginDTO2.getKorisnickoIme())) {
		            		loginDTO2.setError("Trener " + loginDTO2.getKorisnickoIme() + " je obrisan");
		            		/*System.out.println(trener.getKorisnickoIme() + ", da li je obrisan? " + trener.getObrisan());
		            		System.out.println("obrisan!\n");*/
		            		return new ResponseEntity<>(loginDTO2, HttpStatus.OK);
	        			}
	            	}
	        	} else {
	        		if(trener.getKorisnickoIme().equalsIgnoreCase(loginDTO2.getKorisnickoIme())) {
	        			loginDTO2.setError("Trener " + loginDTO2.getKorisnickoIme() + " jos nije prihvacen!");
	        			return new ResponseEntity<>(loginDTO2, HttpStatus.OK);
	        		}        		
	        	}     		
        }
        
        for(Clan clan : clanovi) {
        	if(clan.isActive()) {
        		if(clan.getKorisnickoIme().equalsIgnoreCase(loginDTO2.getKorisnickoIme())) {
        			if(clan.getPassword().equals(loginDTO2.getPassword())) {
        				loginDTO2.setUloga("clan");
        				loginDTO2.setId(clan.getId());
        				loginDTO2.setError("Welcome " + loginDTO2.getKorisnickoIme() + "!");
        				return new ResponseEntity<>(loginDTO2, HttpStatus.OK);
        			} else {
        				loginDTO2.setError("Password is incorrect!");
        				return new ResponseEntity<>(loginDTO2, HttpStatus.OK);
        			}
        		}
        	}
        }
        
        loginDTO2.setError("Username does not exist!");
        return new ResponseEntity<>(loginDTO2, HttpStatus.OK);
    }
}
