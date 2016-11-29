package kickstart.person;

import org.hibernate.validator.constraints.NotEmpty;

public class MitarbeiterFormular {

	@NotEmpty(message = "MitarbeiterFormular.vorname.NotEmpty")
	private String vorname;
	
	@NotEmpty(message = "MitarbeiterFormular.nachname.NotEmpty")
	private String nachname;
	
	@NotEmpty(message = "MitarbeiterFormular.strasse.NotEmpty")
	private String strasse;
	
	@NotEmpty(message = "MitarbeiterFormular.ort.NotEmpty")
	private String ort;
	
	@NotEmpty(message = "MitarbeiterFormular.plz.NotEmpty")
	private String plz;
	
	@NotEmpty(message = "MitarbeiterFormular.username.NotEmpty")
	private String username;
	
	@NotEmpty(message = "MitarbeiterFormular.password.NotEmpty")
	private String password;
	
	@NotEmpty(message = "MitarbeiterFormular.role.NotEmpty")
	private String role;
	
	@NotEmpty(message = "MitarbeiterFormular.role.NotEmpty")
	private String email;

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
