package web.projekat.ProjekatSpring.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table
public class Clan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="korisnicko_ime", nullable = false, unique = true)
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

    @Column(unique = true)
    private String telefon;

    @Column
    private String uloga;
    
    @Column
    private Boolean active;
    
    @OneToMany(mappedBy = "clan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> ocene = new HashSet<>();
    
    @ManyToMany
    @JoinTable(name = "odradjeni_termini",
            joinColumns = @JoinColumn(name = "clan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "termin_id", referencedColumnName = "id"))
    private Set<Termin> odradjeniTermini = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "ocenjeni_termini",
            joinColumns = @JoinColumn(name = "clan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "termin_id", referencedColumnName = "id"))
    private Set<Termin> ocenjeniTermini = new HashSet<>();
    
	public Clan() {
		
	}
	
	
	public Clan(String korisnickoIme, String ime, String prezime, String password, String email,
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

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Set<Termin> getOdradjeniTermini() {
		return odradjeniTermini;
	}

	public void setOdradjeniTermini(Set<Termin> odradjeniTermini) {
		this.odradjeniTermini = odradjeniTermini;
	}

	public Set<Termin> getOcenjeniTermini() {
		return ocenjeniTermini;
	}


	public void setOcenjeniTermini(Set<Termin> ocenjeniTermini) {
		this.ocenjeniTermini = ocenjeniTermini;
	}


	public Boolean getActive() {
		return active;
	}


	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
}
