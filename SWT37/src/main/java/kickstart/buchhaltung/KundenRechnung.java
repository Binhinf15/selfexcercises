package kickstart.buchhaltung;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.salespointframework.quantity.Quantity;

import kickstart.ware.Ware;

/**
 * The type Kunden rechnung.
 */
@Entity
public class KundenRechnung {

    private @Id @GeneratedValue long id;
    private long kundenId;
    private long veranstaltungsId;
    private double preis ;
    private LocalDate datum;
    private boolean bezahlt;
    
    // Konstruktor
    public KundenRechnung(){
    }
    
    public KundenRechnung(long kundenId, long veranstaltungsId, Map<Ware,Quantity> warenliste) {
        this.kundenId = kundenId;
        this.veranstaltungsId = veranstaltungsId;
        this.datum = LocalDate.now();
        this.bezahlt = false;
        aktualisierePreis(warenliste);
    }
    
    // Methoden
    // Preis der Veranstaltung für Rechnung berechnen
    public void aktualisierePreis(Map<Ware,Quantity> warenliste){ 	
    	BigDecimal gesammtPreis = BigDecimal.valueOf(0);
    	for(Ware ware : warenliste.keySet()){
    		BigDecimal menge = warenliste.get(ware).getAmount();
    		BigDecimal warenPreis = BigDecimal.valueOf(ware.getPrice().getNumber().doubleValue() );
    		gesammtPreis = gesammtPreis.add(warenPreis.multiply(menge));   		
    	}
    	setPreis(gesammtPreis.doubleValue());
    }
    
	public long getId() {
		return id;
	}

	public long getKundenId() {
		return kundenId;
	}

	public void setKundenId(long kundenId) {
		this.kundenId = kundenId;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
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

	public long getVeranstaltungsId() {
		return veranstaltungsId;
	}

	public void setVeranstaltungsId(long veranstaltungsId) {
		this.veranstaltungsId = veranstaltungsId;
	}
	
}