package web.projekat.ProjekatSpring.entity;

import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Table
public class Trening implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naziv_treninga", nullable = false)
    private String nazivTreninga;

    @Column
    private String opis;

    @Column(nullable = false)
    private String tip;
    
    @OneToMany(mappedBy = "trening", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Termin> termini = new HashSet<>();

    public Trening() {
    	
	}

    
	public Trening(String nazivTreninga, String opis, String tip) {
		super();
		this.nazivTreninga = nazivTreninga;
		this.opis = opis;
		this.tip = tip;
	}


	public Set<Termin> getTermini() {
		return termini;
	}


	public void setTermini(Set<Termin> termini) {
		this.termini = termini;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivTreninga() {
		return nazivTreninga;
	}

	public void setNazivTreninga(String nazivTreninga) {
		this.nazivTreninga = nazivTreninga;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
}

