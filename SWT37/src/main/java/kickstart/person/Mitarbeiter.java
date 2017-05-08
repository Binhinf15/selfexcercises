package kickstart.person;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.salespointframework.useraccount.UserAccount;

import kickstart.adresse.Adresse;

/**
 * The type Mitarbeiter.
 */
@Entity
@Table(name="MITARBEITER")
public class Mitarbeiter extends Person {

	private @OneToOne UserAccount userAccount;
	private double gehalt;

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
	public Mitarbeiter(String vorname, String nachname, Adresse adresse, UserAccount userAccount, String email, String telefon, double gehalt) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
		this.email = email;
		this.userAccount = userAccount;
		this.telefon = telefon;
		this.gehalt = gehalt;
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

	public double getGehalt() {
		return gehalt;
	}

	public void setGehalt(double gehalt) {
		this.gehalt = gehalt;
	}
}
