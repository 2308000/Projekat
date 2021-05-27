package web.projekat.ProjekatSpring.entity.DTO;

import java.util.Date;

public class TerminDTO {
	private Long id;
	private Date pocetakTermina;
	private Date krajTermina;
	private Integer trajanjeTermina;
	private Integer cenaTermina;
	
	public TerminDTO() {
	}
	
	public TerminDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina) {
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
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
}
