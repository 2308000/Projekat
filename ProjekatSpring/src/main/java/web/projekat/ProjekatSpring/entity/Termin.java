package web.projekat.ProjekatSpring.entity;

import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Table
public class Termin implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "pocetak_termina", nullable = false)
    private Date pocetakTermina;
	
    @Column(name = "kraj_termina")
    private Date krajTermina;
    
    @Column(name = "trajanje_termina")
    private Integer trajanjeTermina;

    @Column(name = "cena_termina", nullable = false)
    private Integer cenaTermina;

    @ManyToOne(fetch = FetchType.EAGER)
    private Trening trening;    
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Trener trener; 
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Sala sala; 
    
    @OneToMany(mappedBy = "termin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> ocene = new HashSet<>();
    
    @ManyToMany(mappedBy = "odradjeniTermini")
    private Set<Clan> clanoviOdradjenih = new HashSet<>();
    
    @ManyToMany(mappedBy = "neodradjeniTermini")
    private Set<Clan> clanoviNeodradjenih = new HashSet<>();
    
    
	public Termin() {
		
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

	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
	}

	public Trener getTrener() {
		return trener;
	}

	public void setTrener(Trener trener) {
		this.trener = trener;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Set<Clan> getClanoviOdradjenih() {
		return clanoviOdradjenih;
	}

	public void setClanoviOdradjenih(Set<Clan> clanoviOdradjenih) {
		this.clanoviOdradjenih = clanoviOdradjenih;
	}

	public Set<Clan> getClanoviNeodradjenih() {
		return clanoviNeodradjenih;
	}

	public void setClanoviNeodradjenih(Set<Clan> clanoviNeodradjenih) {
		this.clanoviNeodradjenih = clanoviNeodradjenih;
	}
}