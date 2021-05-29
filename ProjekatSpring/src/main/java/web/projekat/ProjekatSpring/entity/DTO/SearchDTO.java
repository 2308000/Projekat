package web.projekat.ProjekatSpring.entity.DTO;

public class SearchDTO {
	boolean sve;
	int trajanje;
	int cena;
		
	public SearchDTO() {
		super();
	}
		
	public SearchDTO(boolean sve, int trajanje, int cena) {
		super();
		this.sve = sve;
		this.trajanje = trajanje;
		this.cena = cena;
	}

	public boolean isSve() {
		return sve;
	}
	public void setSve(boolean sve) {
		this.sve = sve;
	}
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	} 
	
	
}
		