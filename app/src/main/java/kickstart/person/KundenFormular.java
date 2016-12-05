package kickstart.person;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The type Kunden formular.
 */
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
	
	@NotEmpty(message = "KundenFormular.telefon.NotEmpty")
	private String telefon;
	
	@NotEmpty(message = "KundenFormular.email.NotEmpty")
	private String email;

    /**
     * Gets vorname.
     *
     * @return the vorname
     */
    public String getVorname() {
		return vorname;
	}

    /**
     * Sets vorname.
     *
     * @param vorname the vorname
     */
    public void setVorname(String vorname) {
		this.vorname = vorname;
	}

    /**
     * Gets nachname.
     *
     * @return the nachname
     */
    public String getNachname() {
		return nachname;
	}

    /**
     * Sets nachname.
     *
     * @param nachname the nachname
     */
    public void setNachname(String nachname) {
		this.nachname = nachname;
	}

    /**
     * Gets strasse.
     *
     * @return the strasse
     */
    public String getStrasse() {
		return strasse;
	}

    /**
     * Sets strasse.
     *
     * @param strasse the strasse
     */
    public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

    /**
     * Gets ort.
     *
     * @return the ort
     */
    public String getOrt() {
		return ort;
	}

    /**
     * Sets ort.
     *
     * @param ort the ort
     */
    public void setOrt(String ort) {
		this.ort = ort;
	}

    /**
     * Gets plz.
     *
     * @return the plz
     */
    public String getPlz() {
		return plz;
	}

    /**
     * Sets plz.
     *
     * @param plz the plz
     */
    public void setPlz(String plz) {
		this.plz = plz;
	}

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
		return email;
	}

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}	
}
