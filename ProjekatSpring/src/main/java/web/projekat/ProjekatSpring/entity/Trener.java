package web.projekat.ProjekatSpring.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table
public class Trener implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "korisnicko_ime", nullable = false, unique = true)
	private String korisnickoIme;

	@Column(nullable = false)
	private String ime;

	@Column
	private String prezime;

	@Column(nullable = false)
	private String password;
	    
	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "datum_rodjenja")
	private Date datumRodjenja;

	@Column(nullable = false)
	private String telefon;

	@Column
	private String uloga;
	
	@Column
	private Boolean active;
	
	@Column(name = "prosecna_ocena")
	private Double prosecnaOcena;
	
    @ManyToOne(fetch = FetchType.EAGER)
    private FitnessCentar fitnessCentar;
    
    @OneToMany(mappedBy = "trener", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Termin> termini = new HashSet<>();

	public Trener() {
		prosecnaOcena = 0.0;
	}

	
	public Trener(String korisnickoIme, String ime, String prezime, String password, String email, Date datumRodjenja,
			String telefon, String uloga, boolean active) {
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
		this.prosecnaOcena = 0.0;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public FitnessCentar getFitnessCentar() {
		return fitnessCentar;
	}

	public void setFitnessCentar(FitnessCentar fitnessCentar) {
		this.fitnessCentar = fitnessCentar;
	}

	public Set<Termin> getTermini() {
		return termini;
	}

	public void setTermini(Set<Termin> termini) {
		this.termini = termini;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}	
	
}