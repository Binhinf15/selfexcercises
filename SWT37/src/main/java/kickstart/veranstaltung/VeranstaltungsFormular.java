package kickstart.veranstaltung;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import kickstart.ware.Ware;

/**
 * The type Veranstaltungs formular.
 */
public class VeranstaltungsFormular {
	
	//@NotEmpty(message = "VeranstaltungsFormular.preis.NotEmpty")
	private double preis;
	
	private String beginnDatum;
	
	private String beginnZeit;
	
	private String schlussDatum;
	
	private String schlussZeit;
	
	@NotEmpty(message = "VeranstaltungsFormular.strasse.NotEmpty")
	private String strasse;
	
	@NotEmpty(message = "VeranstaltungsFormular.ort.NotEmpty")
	private String ort;
	
	@NotEmpty(message = "VeranstaltungsFormular.plz.NotEmpty")
	private String plz;
	
	private String bemerkung;
	
	private String zubehoer;
	
	@NotEmpty(message = "VeranstaltungsFormular.eventArt.NotEmpty")
	private String eventArt;
	
//	@NotEmpty(message = "VeranstaltungsFormular.kundenId.NotEmpty")
	private long kundenId;
	
	private List<Long> mitarbeiterIdListe;
	
	private Ware ware = new Ware();
	
	private String titel;
	

    /**
     * Gets preis.
     *
     * @return the preis
     */
    public double getPreis() {
		return preis;
	}

    /**
     * Sets preis.
     *
     * @param preis the preis
     */
    public void setPreis(double preis) {
		this.preis = preis;
	}
    
    public String getBeginnDatum() {
		return beginnDatum;
	}

	public void setBeginnDatum(String beginnDatum) {
		this.beginnDatum = beginnDatum;
	}

	public String getBeginnZeit() {
		return beginnZeit;
	}

	public void setBeginnZeit(String beginnZeit) {
		this.beginnZeit = beginnZeit;
	}

	public String getSchlussDatum() {
		return schlussDatum;
	}

	public void setSchlussDatum(String schlussDatum) {
		this.schlussDatum = schlussDatum;
	}

	public String getSchlussZeit() {
		return schlussZeit;
	}

	public void setSchlussZeit(String schlussZeit) {
		this.schlussZeit = schlussZeit;
	}
    
    /**
     * Gets strasse.
     *
     * @return the strasse
     */
    public String getStrasse() {
		return strasse;
	}

    /**
     * Sets strasse.
     *
     * @param strasse the strasse
     */
    public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

    /**
     * Gets ort.
     *
     * @return the ort
     */
    public String getOrt() {
		return ort;
	}

    /**
     * Sets ort.
     *
     * @param ort the ort
     */
    public void setOrt(String ort) {
		this.ort = ort;
	}

    /**
     * Gets plz.
     *
     * @return the plz
     */
    public String getPlz() {
		return plz;
	}

    /**
     * Sets plz.
     *
     * @param plz the plz
     */
    public void setPlz(String plz) {
		this.plz = plz;
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
     * Gets event art.
     *
     * @return the event art
     */
    public String getEventArt() {
		return eventArt;
	}

    /**
     * Sets event art.
     *
     * @param eventArt the event art
     */
    public void setEventArt(String eventArt) {
		this.eventArt = eventArt;
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

	public List<Long> getMitarbeiterIdListe() {
		return mitarbeiterIdListe;
	}

	public void setMitarbeiterIdListe(List<Long> mitarbeiterIdListe) {
		this.mitarbeiterIdListe = mitarbeiterIdListe;
	}

	public Ware getWare() {
		return ware;
	}

	public void setWare(Ware ware) {
		this.ware = ware;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getZubehoer() {
		return zubehoer;
	}

	public void setZubehoer(String zubehoer) {
		this.zubehoer = zubehoer;
	}
	
}
