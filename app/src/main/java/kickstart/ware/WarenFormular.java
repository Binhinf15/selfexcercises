package kickstart.ware;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class WarenFormular {
	@NotEmpty(message = "WarenFormular.name.NotEmpty")
	private String name;
	
	@NotNull(message = "WarenFormular.menge.NotEmpty")
	private long menge;
	
	@NotNull(message = "WarenFormular.preis.NotEmpty")
	private double preis;

	@NotEmpty(message = "WarenFormular.beschreibung.NotEmpty")
	private String beschreibung;

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

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}


	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

}
