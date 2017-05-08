package kickstart.ware;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class WarenFormular {
	@NotEmpty(message = "WarenFormular.name.NotEmpty")
	private String name;
	
	private long menge;
	
	@NotNull(message = "WarenFormular.preis.NotEmpty")
	private double price;
	
	private String einheit;

	private String beschreibung;
	
	// Methoden
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMenge() {
		return menge;
	}

	public void setMenge(long menge) {
		this.menge = menge;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getEinheit() {
		return einheit;
	}

	public void setEinheit(String einheit) {
		this.einheit = einheit;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}	
}
