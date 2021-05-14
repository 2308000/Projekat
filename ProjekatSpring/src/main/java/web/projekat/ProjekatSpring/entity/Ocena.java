package web.projekat.ProjekatSpring.entity;

import java.util.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OCENA")
public class Ocena implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private Double ocena;
	
	@ManyToOne(fetch = FetchType.EAGER)
    private Termin termin;
	
	@ManyToOne(fetch = FetchType.EAGER)
    private Clan clan;

	public Ocena() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}
}

