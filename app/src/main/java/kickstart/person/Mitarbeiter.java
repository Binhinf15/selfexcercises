package kickstart.person;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.salespointframework.useraccount.UserAccount;

import kickstart.adresse.Adresse;
import kickstart.person.Person;

/**
 * The type Mitarbeiter.
 */
@Entity
@Table(name="MITARBEITER")
public class Mitarbeiter extends Person {

	private @OneToOne UserAccount userAccount;

	/**
	 * Instantiates a new Mitarbeiter.
	 */
// Konstruktor
	public Mitarbeiter(){
		//default Konstruktor wird für Hibernate benötigt
	}

	/**
	 * Instantiates a new Mitarbeiter.
	 *
	 * @param vorname     the vorname
	 * @param nachname    the nachname
	 * @param adresse     the adresse
	 * @param userAccount the user account
	 * @param email       the email
	 */
	public Mitarbeiter(String vorname, String nachname, Adresse adresse, UserAccount userAccount, String email, String telefon) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
		this.email = email;
		this.userAccount = userAccount;
		this.telefon = telefon;
	}

	/**
	 * Gets user account.
	 *
	 * @return the user account
	 */
// Methoden
	public UserAccount getUserAccount() {
		return userAccount;
	}
}
