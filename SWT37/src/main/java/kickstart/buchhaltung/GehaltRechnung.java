package kickstart.buchhaltung;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The type Gehalt rechnung.
 */
@Entity
public class GehaltRechnung {

    private @Id @GeneratedValue long id;
    private long mitarbeiterId;
    private LocalDate datum;
    private double preis;
    private boolean bezahlt;

    // Konstruktor
    /**
     * Instantiates a new Gehalt rechnung.
     */
    public GehaltRechnung(){   	
    }

    public GehaltRechnung (long mitarbeiterId, LocalDate datum, double preis){
        this.mitarbeiterId = mitarbeiterId;
        this.datum = datum;
        this.preis = preis;
        this.bezahlt = false;
    }

    // Methoden
	public long getId() {
		return id;
	}

	public long getMitarbeiterId() {
		return mitarbeiterId;
	}

	public void setMitarbeiterId(long mitarbeiterId) {
		this.mitarbeiterId = mitarbeiterId;
	}

	public LocalDate getDatum() {
		return datum;
	}
	
	public String getStringDatum() {		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String beginn = datum.format(formatter);
		return beginn;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public boolean isBezahlt() {
		return bezahlt;
	}

	public String isBezahltJaNein() {
		String status = "Nein";
		if(isBezahlt()){
			status = "Ja";
		}
		return status;
	}

	public void setBezahlt(boolean bezahlt) {
		this.bezahlt = bezahlt;
	}
	
	public void setBezahltSwap(){
		if(isBezahlt()){
			setBezahlt(false);
		}else{
			setBezahlt(true);
		}
	}
    
}
