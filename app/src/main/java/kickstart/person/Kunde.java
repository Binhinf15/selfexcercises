package kickstart.person;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kickstart.adresse.Adresse;

/**
 * The type Kunde.
 */
@Entity
@Table(name="KUNDE")
public class Kunde extends Person {

	/**
	 * Instantiates a new Kunde.
	 */
// Konstruktor
	public Kunde(){		
	}

	/**
	 * Instantiates a new Kunde.
	 *
	 * @param vorname  the vorname
	 * @param nachname the nachname
	 * @param adresse  the adresse
	 * @param email    the email
	 * @param telefon    the telefon
	 */
	public Kunde(String vorname, String nachname, Adresse adresse, String email, String telefon){
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.adresse = adresse;
		this.telefon = telefon;
	}
}
