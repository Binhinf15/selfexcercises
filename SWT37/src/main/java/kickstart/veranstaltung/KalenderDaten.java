package kickstart.veranstaltung;

import java.time.LocalDateTime;

public class KalenderDaten {
	private long id;
	private String titel;
	private LocalDateTime beginnDatum;
	private LocalDateTime schlussDatum;
	
	public KalenderDaten(long id, String titel, LocalDateTime beginnDatum, LocalDateTime schlussDatum) {
		this.id = id;
		this.titel = titel;
		this.beginnDatum = beginnDatum;
		this.schlussDatum = schlussDatum;
	}

	public String getBeginnJasonKalender(){
		String beginnJasonKalender = beginnDatum.toString();
		return beginnJasonKalender;
	}
	
	public String getSchlussJasonKalender(){
		String schlussJasonKalender = schlussDatum.toString();
		return schlussJasonKalender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
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
	
}
