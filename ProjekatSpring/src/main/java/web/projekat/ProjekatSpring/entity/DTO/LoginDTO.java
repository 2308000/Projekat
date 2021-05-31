package web.projekat.ProjekatSpring.entity.DTO;

public class LoginDTO {
	private Long id;
	private String korisnickoIme;
	private String error;
	private String password;
	private String uloga;
	
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getUloga() {
		return uloga;
	}
	
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDTO(Long id, String korisnickoIme, String error, String password, String uloga) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.error = error;
		this.password = password;
		this.uloga = uloga;
	}
	
	
}
