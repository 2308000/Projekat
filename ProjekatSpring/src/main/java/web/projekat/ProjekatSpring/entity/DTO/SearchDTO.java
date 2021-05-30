package web.projekat.ProjekatSpring.entity.DTO;

import java.util.*;

public class SearchDTO {
	private boolean sve;
	private Date datum;
	private int cena;
	private String nazivTreninga;
	private String opisTreninga;
	private String tipTreninga;
	public boolean isSve() {
		return sve;
	}
	public void setSve(boolean sve) {
		this.sve = sve;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
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
	
	public SearchDTO(boolean sve, Date datum, int cena, String nazivTreninga, String opisTreninga, String tipTreninga) {
		super();
		this.sve = sve;
		this.datum = datum;
		this.cena = cena;
		this.nazivTreninga = nazivTreninga;
		this.opisTreninga = opisTreninga;
		this.tipTreninga = tipTreninga;
	}
	
	public SearchDTO() {
		super();
	}
	
	
}
		