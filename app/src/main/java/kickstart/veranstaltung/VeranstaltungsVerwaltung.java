package kickstart.veranstaltung;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kickstart.adresse.Adresse;
import kickstart.person.Kunde;
import kickstart.person.KundenRepository;
import kickstart.person.MitarbeiterRepository;

@Component
public class VeranstaltungsVerwaltung {

	private final VeranstaltungsRepository vRepo;
	private final KundenRepository kRepo;
	private final MitarbeiterRepository mRepo;
	
	// Konstruktor
	@Autowired
	public VeranstaltungsVerwaltung(VeranstaltungsRepository vRepo, KundenRepository kRepo, MitarbeiterRepository mRepo){
		this.vRepo = vRepo;
		this.kRepo = kRepo;
		this.mRepo = mRepo;
	}
	
	// Methoden
	public Veranstaltung createVeranstaltung(int beginnTag, int beginnMonat, int beginnJahr, int beginnStunde, int beginnMinute, 
											int schlussTag, int schlussMonat, int schlussJahr, int schlussStunde, int schlussMinute,
											String strasse, String ort, String plz, String bemerkung, Kunde kunde, String eventArt){
		
		LocalDateTime beginnDatum = LocalDateTime.of(beginnJahr, beginnMonat, beginnTag, beginnStunde, beginnMinute); 
		LocalDateTime schlussDatum = LocalDateTime.of(schlussJahr, schlussMonat, schlussTag, schlussStunde, schlussMinute);
		Adresse adresse = new Adresse(strasse, ort, plz);
		long kundenId = kunde.getId();
		Veranstaltung v = new Veranstaltung(beginnDatum, schlussDatum, adresse, bemerkung, kundenId, EventArt.valueOf(eventArt));
		return v;
	}
	
	public void saveVeranstaltung(Veranstaltung veranstaltung){
		vRepo.save(veranstaltung);
	}

	public VeranstaltungsRepository getVeranstaltungsRepo() {
		return vRepo;
	}

	public KundenRepository getKundenRepo() {
		return kRepo;
	}

	public MitarbeiterRepository getMitarbeiterRepo() {
		return mRepo;
	}
}
