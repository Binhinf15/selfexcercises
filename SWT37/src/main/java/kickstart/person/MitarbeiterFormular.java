package kickstart.person;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The type Mitarbeiter formular.
 */
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

	@NotEmpty(message = "MitarbeiterFormular.telefon.NotEmpty")
	private String telefon;
	
	private double gehalt;
	
	// Getter und Setter
	@Override
	public String toString() {
		return "MitarbeiterFormular [vorname=" + vorname + ", nachname=" + nachname + ", strasse=" + strasse + ", ort="
				+ ort + ", plz=" + plz + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", email=" + email + ", telefon=" + telefon + ", gehalt=" + gehalt + "]";
	}
	
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
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
		return username;
	}

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
		this.username = username;
	}

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
		return password;
	}

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
		this.password = password;
	}

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
		return role;
	}

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
		this.role = role;
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

	public double getGehalt() {
		return gehalt;
	}

	public void setGehalt(double gehalt) {
		this.gehalt = gehalt;
	}
}
