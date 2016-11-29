package kickstart.veranstaltung;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kickstart.adresse.Adresse;

@Entity
@Table(name = "VERANSTALTUNG")
public class Veranstaltung {
	
	private @Id @GeneratedValue long id;
	private double preis;	
	private LocalDateTime beginnDatum;
	private LocalDateTime schlussDatum;
	@ManyToOne(cascade=CascadeType.ALL)
	private Adresse adresse;
	private String bemerkung;
	private EventArt eventArt;
	private long kundenId;
	@ElementCollection(targetClass=Long.class)
	private List<Long> mitarbeiterIdListe;
	
	// Konstruktor
	public Veranstaltung(){	
	}

	public Veranstaltung(LocalDateTime beginnDatum, LocalDateTime schlussDatum, Adresse adresse, String bemerkung, long kundenId, EventArt eventArt) {
		this.beginnDatum = beginnDatum;
		this.schlussDatum = schlussDatum;
		this.adresse = adresse;
		this.bemerkung = bemerkung;
		this.kundenId = kundenId;
		this.eventArt = eventArt;
		this.mitarbeiterIdListe = new ArrayList<Long>();
	}
	
	// Methoden
	
	public double getPreis() {
		return preis;
	}

	@Override
	public String toString() {
		return "Veranstaltung [id=" + id + ", preis=" + preis + ", beginnDatum=" + beginnDatum + ", schlussDatum="
				+ schlussDatum + ", adresse=" + adresse + ", bemerkung=" + bemerkung + ", eventArt=" + eventArt
				+ ", kundenId=" + kundenId + ", mitarbeiterIdListe=" + mitarbeiterIdListe + "]";
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public LocalDateTime getBeginnDatum() {
		return beginnDatum;
	}

	public void setBeginnDatum(LocalDateTime beginnDatum) {
		this.beginnDatum = beginnDatum;
	}

	public LocalDateTime getSchlussDatum() {
		return schlussDatum;
	}

	public void setSchlussDatum(LocalDateTime schlussDatum) {
		this.schlussDatum = schlussDatum;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

	public long getKundenId() {
		return kundenId;
	}

	public void setKundenId(long kundenId) {
		this.kundenId = kundenId;
	}

	public long getId() {
		return id;
	}

	public EventArt getEventArt() {
		return eventArt;
	}

	public void setEventArt(EventArt eventArt) {
		this.eventArt = eventArt;
	}

	public List<Long> getMitarbeiterIdListe() {
		return mitarbeiterIdListe;
	}

	public void setMitarbeiterIdListe(List<Long> mitarbeiterIdListe) {
		this.mitarbeiterIdListe = mitarbeiterIdListe;
	}
}
