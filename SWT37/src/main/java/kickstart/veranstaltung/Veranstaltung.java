package kickstart.veranstaltung;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.salespointframework.quantity.Quantity;

import kickstart.adresse.Adresse;
import kickstart.ware.Ware;

/**
 * The type Veranstaltung.
 */
@Entity
@Table(name = "VERANSTALTUNG")
public class Veranstaltung {
	
	private @Id @GeneratedValue long id;
	private String titel;
	private double preis;
	private LocalDateTime beginnDatum;
	private LocalDateTime schlussDatum;
	@ManyToOne(cascade=CascadeType.ALL)
	private Adresse adresse;
	private String bemerkung;
	private String zubehoer;
	private EventArt eventArt;
	private long kundenId;
	@ElementCollection(targetClass=Long.class)
	private List<Long> mitarbeiterIdListe;
	@ElementCollection
	private Map<Ware,Quantity> warenliste;
	


	/**
     * Instantiates a new Veranstaltung.
     */
// Konstruktor
	public Veranstaltung(){	
		//default Konstruktor wird für Hibernate benötigt
	}

    /**
     * Instantiates a new Veranstaltung.
     *
     * @param beginnDatum  the beginn datum
     * @param schlussDatum the schluss datum
     * @param adresse      the adresse
     * @param bemerkung    the bemerkung
     * @param kundenId     the kunden id
     * @param eventArt     the event art
     */
    public Veranstaltung(LocalDateTime beginnDatum, LocalDateTime schlussDatum, Adresse adresse, String bemerkung, long kundenId, EventArt eventArt, String titel, String zubehoer) {
		this.beginnDatum = beginnDatum;
		this.schlussDatum = schlussDatum;
		this.adresse = adresse;
		this.bemerkung = bemerkung;
		this.kundenId = kundenId;
		this.eventArt = eventArt;
		this.mitarbeiterIdListe = new ArrayList<Long>();
		this.titel = titel;
		this.zubehoer = zubehoer;
		this.warenliste = new HashMap<Ware,Quantity>(0);
		aktualisiereGesammtPreis();
	}
	
	// Methoden
	@Override
	public String toString() {
		return "Veranstaltung [id=" + id + ", titel=" + titel + ", preis=" + preis + ", beginnDatum=" + beginnDatum
				+ ", schlussDatum=" + schlussDatum + ", adresse=" + adresse + ", bemerkung=" + bemerkung + ", zubehoer="
				+ zubehoer + ", eventArt=" + eventArt + ", kundenId=" + kundenId + ", mitarbeiterIdListe="
				+ mitarbeiterIdListe + ", warenliste=" + warenliste + "]";
	}
	
	/**
     * Gets preis.
     *
     * @return the preis
     */
    public double getPreis() {
		return preis;
	}
    
    public void aktualisiereGesammtPreis(){ 	
    	BigDecimal gesammtPreis = BigDecimal.valueOf(0);
    	for(Ware ware : warenliste.keySet()){
    		BigDecimal menge = warenliste.get(ware).getAmount();
    		BigDecimal warenPreis = BigDecimal.valueOf(ware.getPrice().getNumber().doubleValue() );
    		gesammtPreis = gesammtPreis.add(warenPreis.multiply(menge));   		
    	}
    	setPreis(gesammtPreis.doubleValue());
    }
    
    public double berechnePreisWarenmenge(Ware ware,Quantity quantity){
    	BigDecimal warenmengePreis = BigDecimal.valueOf(0);
    	BigDecimal menge = quantity.getAmount();
    	BigDecimal warenPreis = BigDecimal.valueOf(ware.getPrice().getNumber().doubleValue() );
    	warenmengePreis = warenmengePreis.add(warenPreis.multiply(menge));
    	return warenmengePreis.doubleValue();
    }
    
	/**
     * Sets preis.
     *
     * @param preis the preis
     */
    public void setPreis(double preis) {
		this.preis = preis;
	}

    /**
     * Gets beginn datum.
     *
     * @return the beginn datum
     */
    public LocalDateTime getBeginnDatum() {
		return beginnDatum;
	}

    /**
     * Sets beginn datum.
     *
     * @param beginnDatum the beginn datum
     */
    public void setBeginnDatum(LocalDateTime beginnDatum) {
		this.beginnDatum = beginnDatum;
	}

    /**
     * Gets schluss datum.
     *
     * @return the schluss datum
     */
    public LocalDateTime getSchlussDatum() {
		return schlussDatum;
	}

    /**
     * Sets schluss datum.
     *
     * @param schlussDatum the schluss datum
     */
    public void setSchlussDatum(LocalDateTime schlussDatum) {
		this.schlussDatum = schlussDatum;
	}

    /**
     * Gets adresse.
     *
     * @return the adresse
     */
    public Adresse getAdresse() {
		return adresse;
	}

    /**
     * Sets adresse.
     *
     * @param adresse the adresse
     */
    public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

    /**
     * Gets bemerkung.
     *
     * @return the bemerkung
     */
    public String getBemerkung() {
		return bemerkung;
	}

    /**
     * Sets bemerkung.
     *
     * @param bemerkung the bemerkung
     */
    public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

    /**
     * Gets kunden id.
     *
     * @return the kunden id
     */
    public long getKundenId() {
		return kundenId;
	}

    /**
     * Sets kunden id.
     *
     * @param kundenId the kunden id
     */
    public void setKundenId(long kundenId) {
		this.kundenId = kundenId;
	}

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
		return id;
	}

    /**
     * Gets event art.
     *
     * @return the event art
     */
    public EventArt getEventArt() {
		return eventArt;
	}

    /**
     * Sets event art.
     *
     * @param eventArt the event art
     */
    public void setEventArt(EventArt eventArt) {
		this.eventArt = eventArt;
	}

    /**
     * Gets mitarbeiter id liste.
     *
     * @return the mitarbeiter id liste
     */
    public List<Long> getMitarbeiterIdListe() {
		return mitarbeiterIdListe;
	}

    /**
     * Sets mitarbeiter id liste.
     *
     * @param mitarbeiterIdListe the mitarbeiter id liste
     */
    public void setMitarbeiterIdListe(List<Long> mitarbeiterIdListe) {
		this.mitarbeiterIdListe = mitarbeiterIdListe;
	}

	public Map<Ware, Quantity> getWarenliste() {
		return warenliste;
	}

	public void setWarenliste(Map<Ware, Quantity> warenliste) {
		this.warenliste = warenliste;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public String getBeginn(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm");
		String beginn = beginnDatum.format(formatter);
		return beginn;
	}
	
	public String getSchluss(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm");
		String schluss = schlussDatum.format(formatter);
		return schluss;
	}

	public String getZubehoer() {
		return zubehoer;
	}

	public void setZubehoer(String zubehoer) {
		this.zubehoer = zubehoer;
	}
	
}
