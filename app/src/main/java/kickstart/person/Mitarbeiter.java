package kickstart.person;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.salespointframework.useraccount.UserAccount;

import kickstart.adresse.Adresse;
import kickstart.person.Person;

@Entity
@Table(name="MITARBEITER")
public class Mitarbeiter extends Person {
	
	private @OneToOne UserAccount userAccount;
	
	// Konstruktor
	// default Konstruktor wird für hybernate benötigt
	public Mitarbeiter(){		
	}
	
	public Mitarbeiter(String vorname, String nachname, Adresse adresse, UserAccount userAccount, String email) {
		super.vorname = vorname;
		super.nachname = nachname;
		super.adresse = adresse;
		super.email = email;
		this.userAccount = userAccount;
	}
	
	// Methoden
	public UserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public String toString() {
		return "Mitarbeiter [userAccount=" + userAccount + ", vorname=" + vorname + ", nachname=" + nachname
				+ ", adresse=" + adresse + ", email=" + email + "]";
	}
}
