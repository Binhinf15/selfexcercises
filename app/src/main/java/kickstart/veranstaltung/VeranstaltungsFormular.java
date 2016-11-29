package kickstart.veranstaltung;

import org.hibernate.validator.constraints.NotEmpty;

public class VeranstaltungsFormular {
	
	@NotEmpty(message = "VeranstaltungsFormular.preis.NotEmpty")
	private String preis;	
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnTag.NotEmpty")
	private String beginnTag;
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnMonat.NotEmpty")
	private String beginnMonat;
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnJahr.NotEmpty")
	private String beginnJahr;
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnStunde.NotEmpty")
	private String beginnStunde;
	
	@NotEmpty(message = "VeranstaltungsFormular.beginnMinute.NotEmpty")
	private String beginnMinute;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussTag.NotEmpty")
	private String schlussTag;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussMonat.NotEmpty")
	private String schlussMonat;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussJahr.NotEmpty")
	private String schlussJahr;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussStunde.NotEmpty")
	private String schlussStunde;
	
	@NotEmpty(message = "VeranstaltungsFormular.schlussMinute.NotEmpty")
	private String schlussMinute;
	
	@NotEmpty(message = "VeranstaltungsFormular.strasse.NotEmpty")
	private String strasse;
	
	@NotEmpty(message = "VeranstaltungsFormular.ort.NotEmpty")
	private String ort;
	
	@NotEmpty(message = "VeranstaltungsFormular.plz.NotEmpty")
	private String plz;
	
	private String bemerkung;
	
	@NotEmpty(message = "VeranstaltungsFormular.eventArt.NotEmpty")
	private EventArt eventArt;
	
	@NotEmpty(message = "VeranstaltungsFormular.kundenId.NotEmpty")
	private long kundenId;

	public String getPreis() {
		return preis;
	}

	public void setPreis(String preis) {
		this.preis = preis;
	}

	public String getBeginnTag() {
		return beginnTag;
	}

	public void setBeginnTag(String beginnTag) {
		this.beginnTag = beginnTag;
	}

	public String getBeginnMonat() {
		return beginnMonat;
	}

	public void setBeginnMonat(String beginnMonat) {
		this.beginnMonat = beginnMonat;
	}

	public String getBeginnJahr() {
		return beginnJahr;
	}

	public void setBeginnJahr(String beginnJahr) {
		this.beginnJahr = beginnJahr;
	}

	public String getBeginnStunde() {
		return beginnStunde;
	}

	public void setBeginnStunde(String beginnStunde) {
		this.beginnStunde = beginnStunde;
	}

	public String getBeginnMinute() {
		return beginnMinute;
	}

	public void setBeginnMinute(String beginnMinute) {
		this.beginnMinute = beginnMinute;
	}

	public String getSchlussTag() {
		return schlussTag;
	}

	public void setSchlussTag(String schlussTag) {
		this.schlussTag = schlussTag;
	}

	public String getSchlussMonat() {
		return schlussMonat;
	}

	public void setSchlussMonat(String schlussMonat) {
		this.schlussMonat = schlussMonat;
	}

	public String getSchlussJahr() {
		return schlussJahr;
	}

	public void setSchlussJahr(String schlussJahr) {
		this.schlussJahr = schlussJahr;
	}

	public String getSchlussStunde() {
		return schlussStunde;
	}

	public void setSchlussStunde(String schlussStunde) {
		this.schlussStunde = schlussStunde;
	}

	public String getSchlussMinute() {
		return schlussMinute;
	}

	public void setSchlussMinute(String schlussMinute) {
		this.schlussMinute = schlussMinute;
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

	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

	public EventArt getEventArt() {
		return eventArt;
	}

	public void setEventArt(EventArt eventArt) {
		this.eventArt = eventArt;
	}

	public long getKundenId() {
		return kundenId;
	}

	public void setKundenId(long kundenId) {
		this.kundenId = kundenId;
	}
}
