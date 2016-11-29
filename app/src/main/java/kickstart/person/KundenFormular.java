package kickstart.person;

import org.hibernate.validator.constraints.NotEmpty;

public class KundenFormular {
	@NotEmpty(message = "KundenFormular.vorname.NotEmpty")
	private String vorname;
	
	@NotEmpty(message = "KundenFormular.nachname.NotEmpty")
	private String nachname;
	
	@NotEmpty(message = "KundenFormular.strasse.NotEmpty")
	private String strasse;
	
	@NotEmpty(message = "KundenFormular.ort.NotEmpty")
	private String ort;
	
	@NotEmpty(message = "KundenFormular.plz.NotEmpty")
	private String plz;
	
	@NotEmpty(message = "KundenFormular.email.NotEmpty")
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
