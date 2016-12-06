package kickstart.veranstaltung;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The type Veranstaltungs formular.
 */
public class VeranstaltungsFormular {
	
	//@NotEmpty(message = "VeranstaltungsFormular.preis.NotEmpty")
	private int preis;	
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnTag.NotEmpty")
	private int beginnTag;
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnMonat.NotEmpty")
	private int beginnMonat;
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnJahr.NotEmpty")
	private int beginnJahr;
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnStunde.NotEmpty")
	private int beginnStunde;
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnMinute.NotEmpty")
	private int beginnMinute;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussTag.NotEmpty")
	private int schlussTag;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussMonat.NotEmpty")
	private int schlussMonat;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussJahr.NotEmpty")
	private int schlussJahr;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussStunde.NotEmpty")
	private int schlussStunde;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussMinute.NotEmpty")
	private int schlussMinute;
	
	@NotEmpty(message = "VeranstaltungsFormular.strasse.NotEmpty")
	private String strasse;
	
	@NotEmpty(message = "VeranstaltungsFormular.ort.NotEmpty")
	private String ort;
	
	@NotEmpty(message = "VeranstaltungsFormular.plz.NotEmpty")
	private String plz;
	
	private String bemerkung;
	
	@NotEmpty(message = "VeranstaltungsFormular.eventArt.NotEmpty")
	private String eventArt;
	
	@NotEmpty(message = "VeranstaltungsFormular.kundenId.NotEmpty")
	private long kundenId;
	
	private List<Long> mitarbeiterIdListe;

    /**
     * Gets preis.
     *
     * @return the preis
     */
    public int getPreis() {
		return preis;
	}

    /**
     * Sets preis.
     *
     * @param preis the preis
     */
    public void setPreis(int preis) {
		this.preis = preis;
	}

    /**
     * Gets beginn tag.
     *
     * @return the beginn tag
     */
    public int getBeginnTag() {
		return beginnTag;
	}

    /**
     * Sets beginn tag.
     *
     * @param beginnTag the beginn tag
     */
    public void setBeginnTag(int beginnTag) {
		this.beginnTag = beginnTag;
	}

    /**
     * Gets beginn monat.
     *
     * @return the beginn monat
     */
    public int getBeginnMonat() {
		return beginnMonat;
	}

    /**
     * Sets beginn monat.
     *
     * @param beginnMonat the beginn monat
     */
    public void setBeginnMonat(int beginnMonat) {
		this.beginnMonat = beginnMonat;
	}

    /**
     * Gets beginn jahr.
     *
     * @return the beginn jahr
     */
    public int getBeginnJahr() {
		return beginnJahr;
	}

    /**
     * Sets beginn jahr.
     *
     * @param beginnJahr the beginn jahr
     */
    public void setBeginnJahr(int beginnJahr) {
		this.beginnJahr = beginnJahr;
	}

    /**
     * Gets beginn stunde.
     *
     * @return the beginn stunde
     */
    public int getBeginnStunde() {
		return beginnStunde;
	}

    /**
     * Sets beginn stunde.
     *
     * @param beginnStunde the beginn stunde
     */
    public void setBeginnStunde(int beginnStunde) {
		this.beginnStunde = beginnStunde;
	}

    /**
     * Gets beginn minute.
     *
     * @return the beginn minute
     */
    public int getBeginnMinute() {
		return beginnMinute;
	}

    /**
     * Sets beginn minute.
     *
     * @param beginnMinute the beginn minute
     */
    public void setBeginnMinute(int beginnMinute) {
		this.beginnMinute = beginnMinute;
	}

    /**
     * Gets schluss tag.
     *
     * @return the schluss tag
     */
    public int getSchlussTag() {
		return schlussTag;
	}

    /**
     * Sets schluss tag.
     *
     * @param schlussTag the schluss tag
     */
    public void setSchlussTag(int schlussTag) {
		this.schlussTag = schlussTag;
	}

    /**
     * Gets schluss monat.
     *
     * @return the schluss monat
     */
    public int getSchlussMonat() {
		return schlussMonat;
	}

    /**
     * Sets schluss monat.
     *
     * @param schlussMonat the schluss monat
     */
    public void setSchlussMonat(int schlussMonat) {
		this.schlussMonat = schlussMonat;
	}

    /**
     * Gets schluss jahr.
     *
     * @return the schluss jahr
     */
    public int getSchlussJahr() {
		return schlussJahr;
	}

    /**
     * Sets schluss jahr.
     *
     * @param schlussJahr the schluss jahr
     */
    public void setSchlussJahr(int schlussJahr) {
		this.schlussJahr = schlussJahr;
	}

    /**
     * Gets schluss stunde.
     *
     * @return the schluss stunde
     */
    public int getSchlussStunde() {
		return schlussStunde;
	}

    /**
     * Sets schluss stunde.
     *
     * @param schlussStunde the schluss stunde
     */
    public void setSchlussStunde(int schlussStunde) {
		this.schlussStunde = schlussStunde;
	}

    /**
     * Gets schluss minute.
     *
     * @return the schluss minute
     */
    public int getSchlussMinute() {
		return schlussMinute;
	}

    /**
     * Sets schluss minute.
     *
     * @param schlussMinute the schluss minute
     */
    public void setSchlussMinute(int schlussMinute) {
		this.schlussMinute = schlussMinute;
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
}
