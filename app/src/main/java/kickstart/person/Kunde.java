package kickstart.person;

import javax.persistence.Entity;
import javax.persistence.Table;

import kickstart.adresse.Adresse;

@Entity
@Table(name="KUNDE")
public class Kunde extends Person {
	
	// Konstruktor
	// default Konstruktor wird für hybernate benötigt
	public Kunde(){		
	}
	
	public Kunde(String vorname, String nachname, Adresse adresse, String email){
		super.vorname = vorname;
		super.nachname = nachname;
		super.email = email;
		super.adresse = adresse;
	}
	// Methoden

	@Override
	public String toString() {
		return "Kunde [vorname=" + vorname + ", nachname=" + nachname + ", adresse=" + adresse + ", email=" 
				+ email + "]";
	}
	
}
