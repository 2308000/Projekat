package web.projekat.ProjekatSpring.entity.DTO;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.entity.Sala;
import web.projekat.ProjekatSpring.entity.Termin;
import web.projekat.ProjekatSpring.entity.Trener;
import web.projekat.ProjekatSpring.entity.Trening;

public class PrijavaDTO {
	private Long id; //id termina
	private Long clanID;
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String password;
    private String email;
    private Date datumRodjenja;
    private String telefon;
    private String uloga;
    private Boolean active;
    private Date pocetakTermina;
    private Date krajTermina;
    private Integer trajanjeTermina;
    private Integer cenaTermina;
    private Trening trening;    
    private Trener trener; 
    private Sala sala; 
    private String zastita;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClanID() {
		return clanID;
	}
	public void setClanID(Long clanID) {
		this.clanID = clanID;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Date getPocetakTermina() {
		return pocetakTermina;
	}
	public void setPocetakTermina(Date pocetakTermina) {
		this.pocetakTermina = pocetakTermina;
	}
	public Date getKrajTermina() {
		return krajTermina;
	}
	public void setKrajTermina(Date krajTermina) {
		this.krajTermina = krajTermina;
	}
	public Integer getTrajanjeTermina() {
		return trajanjeTermina;
	}
	public void setTrajanjeTermina(Integer trajanjeTermina) {
		this.trajanjeTermina = trajanjeTermina;
	}
	public Integer getCenaTermina() {
		return cenaTermina;
	}
	public void setCenaTermina(Integer cenaTermina) {
		this.cenaTermina = cenaTermina;
	}
	public Trening getTrening() {
		return trening;
	}
	public void setTrening(Trening trening) {
		this.trening = trening;
	}
	public Trener getTrener() {
		return trener;
	}
	public void setTrener(Trener trener) {
		this.trener = trener;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public String getZastita() {
		return zastita;
	}
	public void setZastita(String zastita) {
		this.zastita = zastita;
	}
	public PrijavaDTO(Long id, Long clanID, String korisnickoIme, String ime, String prezime, String password,
			String email, Date datumRodjenja, String telefon, String uloga, Boolean active, Date pocetakTermina,
			Date krajTermina, Integer trajanjeTermina, Integer cenaTermina, Trening trening, Trener trener, Sala sala, String zastita) {
		super();
		this.id = id;
		this.clanID = clanID;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.uloga = uloga;
		this.active = active;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.trening = trening;
		this.trener = trener;
		this.sala = sala;
		this.zastita = zastita;
	} 
	
	
	
	
	
}
