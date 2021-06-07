package web.projekat.ProjekatSpring.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "SALA")
public class Sala implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer kapacitet;

    @Column(name = "oznaka_sale", nullable = false, unique = true)
    private String oznakaSale;
    
    @Column
    private boolean obrisana;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private FitnessCentar fitnessCentar;
    
    @OneToMany(mappedBy = "sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Termin> termini = new HashSet<>();

	public Sala() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FitnessCentar getFitnessCentar() {
		return fitnessCentar;
	}

	public void setFitnessCentar(FitnessCentar fitnessCentar) {
		this.fitnessCentar = fitnessCentar;
	}

	public Integer getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(Integer kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getOznakaSale() {
		return oznakaSale;
	}

	public void setOznakaSale(String oznakaSale) {
		this.oznakaSale = oznakaSale;
	}

	public Set<Termin> getTermini() {
		return termini;
	}

	public void setTermini(Set<Termin> termini) {
		this.termini = termini;
	}

	public boolean isObrisana() {
		return obrisana;
	}

	public void setObrisana(boolean obrisan) {
		this.obrisana = obrisan;
	}

	public Sala(Long id, Integer kapacitet, String oznakaSale, boolean obrisan) {
		super();
		this.id = id;
		this.kapacitet = kapacitet;
		this.oznakaSale = oznakaSale;
		this.obrisana = obrisan;
	}  
}
