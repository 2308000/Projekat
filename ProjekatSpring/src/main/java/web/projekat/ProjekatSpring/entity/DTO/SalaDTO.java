package web.projekat.ProjekatSpring.entity.DTO;



public class SalaDTO {
	
	private Long id;

    private Integer kapacitet;

    private String oznakaSale;
    
    private boolean obrisana;
    
    private Long centarID;

    private String zastita;
    
    
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

	public boolean isObrisana() {
		return obrisana;
	}

	public void setObrisana(boolean obrisana) {
		this.obrisana = obrisana;
	}

	public Long getCentarID() {
		return centarID;
	}

	public void setCentarID(Long centarID) {
		this.centarID = centarID;
	}

	public SalaDTO(Long id, Integer kapacitet, String oznakaSale, boolean obrisana, Long centarID, String zastita) {
		super();
		this.id = id;
		this.kapacitet = kapacitet;
		this.oznakaSale = oznakaSale;
		this.obrisana = obrisana;
		this.centarID = centarID;
		this.zastita = zastita;
	}

	public SalaDTO(Integer kapacitet, String oznakaSale, boolean obrisana, Long centarID, String zastita) {
		super();
		this.kapacitet = kapacitet;
		this.oznakaSale = oznakaSale;
		this.obrisana = obrisana;
		this.centarID = centarID;
		this.zastita = zastita;
	}

	public SalaDTO() {
		super();
	}
}
