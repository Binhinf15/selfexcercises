package kickstart.person;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import kickstart.adresse.Adresse;

@Entity
public abstract class Person {
	
	@Id @GeneratedValue
	private long id;
	protected String vorname;
	protected String nachname;
	@ManyToOne(cascade=CascadeType.ALL)
	protected Adresse adresse;
	protected String email;
	
	// Methoden
	public long getId() {
		return id;
	}
	
	public String getVorname(){
		return vorname;
	}
	
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	public String getNachname(){
		return nachname;
	}
	
	public void setNachname(String nachname){
		this.nachname = nachname;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
