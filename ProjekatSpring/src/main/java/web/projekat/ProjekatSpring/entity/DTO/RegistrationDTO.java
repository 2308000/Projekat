package web.projekat.ProjekatSpring.entity.DTO;

import java.util.Date;

public class RegistrationDTO {
	private Long id;
	private String korisnickoIme;
	private String ime;
	private String prezime;
	private String password;
	private String email;
	private Date datumRodjenja;
	private String telefon;
	private String uloga;
	private boolean active; 
	private String zastita;
	private Long centarID;
	
	
	public Long getCentarID() {
		return centarID;
	}

	public void setCentarID(Long centarID) {
		this.centarID = centarID;
	}

	public String getZastita() {
		return zastita;
	}

	public void setZastita(String zastita) {
		this.zastita = zastita;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public RegistrationDTO(String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, String uloga, boolean active) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.uloga = uloga;
		this.active = active;
	}
	
	public RegistrationDTO(Long id, String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, String uloga, boolean active) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.uloga = uloga;
		this.active = active;
	}
	
	public RegistrationDTO(String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, String uloga, boolean active, String zastita) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.uloga = uloga;
		this.active = active;
		this.zastita = zastita;
	}

	
	public RegistrationDTO(String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, String uloga, boolean active, Long centarID) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.uloga = uloga;
		this.centarID = centarID;
		this.active = active;
	}
	
	public RegistrationDTO(Long id, String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, String uloga, boolean active, Long centarID) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.uloga = uloga;
		this.centarID = centarID;
		this.active = active;
	}
	public RegistrationDTO(Long id) {
		super();
		this.id = id;
	}

	public RegistrationDTO() {
		super();
	}
	
	
}
