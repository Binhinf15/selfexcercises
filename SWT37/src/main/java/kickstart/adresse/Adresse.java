package kickstart.adresse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Adresse.
 */
@Entity
@Table(name="ADRESSE")
public class Adresse {
	
	@Id @GeneratedValue
	private long id;
	private String strasse;
	private String ort;
	private String plz;

    /**
     * Instantiates a new Adresse.
     */
// Konstruktor
	public Adresse(){
		//default Konstruktor wird für Hibernate benötigt
	}

    /**
     * Instantiates a new Adresse.
     *
     * @param strasse the String strasse
     * @param ort     the String ort
     * @param plz     the String plz
     */
    public Adresse(String strasse, String ort, String plz) {
		this.strasse = strasse;
		this.ort = ort;
		this.plz = plz;
	}

    /**
     * Gets long id.
     *
     * @return long id
     */
// Methoden
	public long getId() {
		return id;
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
     * Sets String strasse.
     *
     * @param strasse the String strasse
     */
    public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

    /**
     * Gets String ort.
     *
     * @return the String ort
     */
    public String getOrt() {
		return ort;
	}

    /**
     * Sets String ort.
     *
     * @param ort the String ort
     */
    public void setOrt(String ort) {
		this.ort = ort;
	}

    /**
     * Gets String plz.
     *
     * @return the plz
     */
    public String getPlz() {
		return plz;
	}

    /**
     * Sets plz.
     *
     * @param plz the String plz
     */
    public void setPlz(String plz) {
		this.plz = plz;
	}

	@Override
	public String toString() {
		return "Adresse [strasse=" + strasse + ", ort=" + ort + ", plz=" + plz + "]";
	}
}
