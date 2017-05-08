package kickstart.person;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import kickstart.adresse.Adresse;

/**
 * The type Person.
 */
@MappedSuperclass
public abstract class Person {
	
	@Id @GeneratedValue
	private long id;
    /**
     * The Vorname.
     */
    protected String vorname;
    /**
     * The Nachname.
     */
    protected String nachname;
    /**
     * The Adresse.
     */
    @ManyToOne(cascade=CascadeType.ALL)
	protected Adresse adresse;
    
	protected String telefon;	
    /**
     * The Email.
     */
    protected String email;

    /**
     * Get vorname string.
     *
     * @return the string
     */
// Methoden
	public String getVorname(){
		return vorname;
	}

    /**
     * Set vorname.
     *
     * @param vorname the vorname
     */
    public void setVorname(String vorname){
		this.vorname = vorname;
	}

    /**
     * Get nachname string.
     *
     * @return the string
     */
    public String getNachname(){
		return nachname;
	}

    /**
     * Set nachname.
     *
     * @param nachname the nachname
     */
    public void setNachname(String nachname){
		this.nachname = nachname;
	}

    /**
     * Gets adresse.
     *
     * @return the adresse
     */
    public Adresse getAdresse() {
		return adresse;
	}

    /**
     * Sets adresse.
     *
     * @param adresse the adresse
     */
    public void setAdresse(Adresse adresse) {
		this.adresse = adresse;	
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
		return id;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", vorname=" + vorname + ", nachname=" + nachname + ", adresse=" + adresse
				+ ", email=" + email + ", telefon=" + telefon + "]";
	}
}
