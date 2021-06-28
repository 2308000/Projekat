package web.projekat.ProjekatSpring.entity.DTO;

import java.util.Date;

import web.projekat.ProjekatSpring.entity.Clan;
import web.projekat.ProjekatSpring.entity.Termin;

public class TerminDTO {
	private Long id;
	private Date pocetakTermina;
	private Date krajTermina;
	private Integer trajanjeTermina;
	private Integer cenaTermina;
	private String nazivTreninga;
	private String opisTreninga;
	private String tipTreninga;
	private String zastita;
	private Long centarID;
	private Long salaID;
	private Long trenerID;
	private Long clanID;
	private String korisnickoIme;
	private String ime;
	private String prezime;
	private double ocena; 
	
	public TerminDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String opisTreninga, String tipTreninga) {
		super();
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.nazivTreninga = nazivTreninga;
		this.opisTreninga = opisTreninga;
		this.tipTreninga = tipTreninga;
	}
	
	public TerminDTO(Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String opisTreninga, String tipTreninga, String zastita) {
		super();
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.nazivTreninga = nazivTreninga;
		this.opisTreninga = opisTreninga;
		this.tipTreninga = tipTreninga;
		this.zastita = zastita;
	}
	
	public TerminDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String opisTreninga, String tipTreninga, String zastita, Long centarID, Long salaID, Long trenerID) {
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.nazivTreninga = nazivTreninga;
		this.opisTreninga = opisTreninga;
		this.tipTreninga = tipTreninga;
		this.zastita = zastita;
		this.centarID = centarID;
		this.salaID = salaID;
		this.trenerID = trenerID;
	}
	
	public TerminDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String zastita, Long centarID, Long salaID) {
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.nazivTreninga = nazivTreninga;
		this.zastita = zastita;
		this.centarID = centarID;
		this.salaID = salaID;
	}

	
	public TerminDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String zastita, Long clanID, String korisnickoIme, String ime, String prezime) {
		super();
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.zastita = zastita;
		this.clanID = clanID;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
	}
	
	public TerminDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			double ocena, Long clanID, String korisnickoIme, String ime, String prezime) {
		super();
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.ocena = ocena;
		this.clanID = clanID;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
	}

	public TerminDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String opisTreninga, String tipTreninga, Long clanID, double ocena) {
		super();
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.nazivTreninga = nazivTreninga;
		this.opisTreninga = opisTreninga;
		this.tipTreninga = tipTreninga;
		this.clanID = clanID;
		this.ocena = ocena;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
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

	public TerminDTO(Long salaID) {
		this.salaID = salaID;
	}

	
	public Long getTrenerID() {
		return trenerID;
	}

	public void setTrenerID(Long trenerID) {
		this.trenerID = trenerID;
	}

	public TerminDTO() {
		super();
	}

	public Long getSalaID() {
		return salaID;
	}

	public void setSalaID(Long salaID) {
		this.salaID = salaID;
	}

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

	public String getNazivTreninga() {
		return nazivTreninga;
	}

	public void setNazivTreninga(String nazivTreninga) {
		this.nazivTreninga = nazivTreninga;
	}

	public String getOpisTreninga() {
		return opisTreninga;
	}

	public void setOpisTreninga(String opisTreninga) {
		this.opisTreninga = opisTreninga;
	}

	public String getTipTreninga() {
		return tipTreninga;
	}

	public void setTipTreninga(String tipTreninga) {
		this.tipTreninga = tipTreninga;
	}

	public Long getClanID() {
		return clanID;
	}

	public void setClanID(Long clanID) {
		this.clanID = clanID;
	}
	
}
