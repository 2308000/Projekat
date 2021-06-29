package web.projekat.ProjekatSpring.entity.DTO;

import java.util.*;

public class ClanDTO {
	private Long id;
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String password;
    private String email;
    private Date datumRodjenja;
    private String telefon;
    private List<Double> ocene = new ArrayList<>();
    private List<String> odradjeniTermini = new ArrayList<>();
    private List<String> ocenjeniTermini = new ArrayList<>();
    private String zastita; 
    
    
	public ClanDTO() {
		super();
	}

	public ClanDTO(Long id, String korisnickoIme, String ime, String prezime, String password, String email,
			Date datumRodjenja, String telefon, Boolean active, List<Double> retOcene, List<String> retOdradjeni,
			List<String> retOcenjeni, String zastita) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.ocene = retOcene;
		this.odradjeniTermini = retOdradjeni;
		this.ocenjeniTermini = retOcenjeni;
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

	public String getZastita() {
		return zastita;
	}

	public void setZastita(String zastita) {
		this.zastita = zastita;
	}

	public List<Double> getOcene() {
		return ocene;
	}

	public void setOcene(List<Double> ocene) {
		this.ocene = ocene;
	}

	public List<String> getOdradjeniTermini() {
		return odradjeniTermini;
	}

	public void setOdradjeniTermini(List<String> odradjeniTermini) {
		this.odradjeniTermini = odradjeniTermini;
	}

	public List<String> getOcenjeniTermini() {
		return ocenjeniTermini;
	}

	public void setOcenjeniTermini(List<String> ocenjeniTermini) {
		this.ocenjeniTermini = ocenjeniTermini;
	}
}
