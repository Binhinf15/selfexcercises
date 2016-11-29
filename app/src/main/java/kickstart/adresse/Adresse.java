package kickstart.adresse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADRESSE")
public class Adresse {
	
	@Id @GeneratedValue
	private long id;
	private String strasse;
	private String ort;
	private String plz;
	
	// Konstruktor
	public Adresse(){
	}
	
	public Adresse(String strasse, String ort, String plz) {
		this.strasse = strasse;
		this.ort = ort;
		this.plz = plz;
	}
	
	// Methoden
	public long getId() {
		return id;
	}
	
	public String getStrasse() {
		return strasse;
	}
	
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	public String getOrt() {
		return ort;
	}
	
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public String getPlz() {
		return plz;
	}
	
	public void setPlz(String plz) {
		this.plz = plz;
	}

	@Override
	public String toString() {
		return "Adresse [strasse=" + strasse + ", ort=" + ort + ", plz=" + plz + "]";
	}
}
