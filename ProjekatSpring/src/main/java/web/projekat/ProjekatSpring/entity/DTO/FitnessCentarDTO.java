package web.projekat.ProjekatSpring.entity.DTO;

public class FitnessCentarDTO {
	private Long id;
    private String nazivCentra;
    private String adresa;
    private String brojTelefonaCentra;
    private String emailCentra;
    private boolean obrisan;
    private String zastita;
    
	public String getZastita() {
		return zastita;
	}
	public void setZastita(String zastita) {
		this.zastita = zastita;
	}
	public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNazivCentra() {
		return nazivCentra;
	}
	public void setNazivCentra(String nazivCentra) {
		this.nazivCentra = nazivCentra;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getBrojTelefonaCentra() {
		return brojTelefonaCentra;
	}
	public void setBrojTelefonaCentra(String brojTelefonaCentra) {
		this.brojTelefonaCentra = brojTelefonaCentra;
	}
	public String getEmailCentra() {
		return emailCentra;
	}
	public void setEmailCentra(String emailCentra) {
		this.emailCentra = emailCentra;
	}
	
	public FitnessCentarDTO(Long id, String nazivCentra, String adresa, String brojTelefonaCentra, String emailCentra, boolean obrisan, String zastita) {
		super();
		this.id = id;
		this.nazivCentra = nazivCentra;
		this.adresa = adresa;
		this.brojTelefonaCentra = brojTelefonaCentra;
		this.emailCentra = emailCentra;
		this.obrisan = obrisan;
		this.zastita = zastita;
	}
	
	public FitnessCentarDTO() {
		super();
	}
	
	
}
