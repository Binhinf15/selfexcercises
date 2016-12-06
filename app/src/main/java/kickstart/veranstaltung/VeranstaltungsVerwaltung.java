package kickstart.veranstaltung;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kickstart.adresse.Adresse;
import kickstart.person.AccountRolle;
import kickstart.person.Kunde;
import kickstart.person.KundenRepository;
import kickstart.person.MitarbeiterRepository;

/**
 * The type Veranstaltungs verwaltung.
 */
@Component
public class VeranstaltungsVerwaltung {

	private final VeranstaltungsRepository vRepo;
	private final KundenRepository kRepo;
	private final MitarbeiterRepository mRepo;
	private final List<EventArt> enumEventArtList;

    /**
     * Instantiates a new Veranstaltungs verwaltung.
     *
     * @param vRepo the v repo
     * @param kRepo the k repo
     * @param mRepo the m repo
     */
// Konstruktor
	@Autowired
	public VeranstaltungsVerwaltung(VeranstaltungsRepository vRepo, KundenRepository kRepo, MitarbeiterRepository mRepo){
		this.vRepo = vRepo;
		this.kRepo = kRepo;
		this.mRepo = mRepo;
		this.enumEventArtList = Arrays.asList(EventArt.values());
	}

    /**
     * Create veranstaltung veranstaltung.
     *
     * @param beginnTag     the beginn tag
     * @param beginnMonat   the beginn monat
     * @param beginnJahr    the beginn jahr
     * @param beginnStunde  the beginn stunde
     * @param beginnMinute  the beginn minute
     * @param schlussTag    the schluss tag
     * @param schlussMonat  the schluss monat
     * @param schlussJahr   the schluss jahr
     * @param schlussStunde the schluss stunde
     * @param schlussMinute the schluss minute
     * @param strasse       the strasse
     * @param ort           the ort
     * @param plz           the plz
     * @param bemerkung     the bemerkung
     * @param kundenId      the kunden id
     * @param eventArt      the event art
     * @return the veranstaltung
     */
// Methoden
	public Veranstaltung createVeranstaltung(int beginnTag, int beginnMonat, int beginnJahr, int beginnStunde, int beginnMinute, 
											int schlussTag, int schlussMonat, int schlussJahr, int schlussStunde, int schlussMinute,
											String strasse, String ort, String plz, String bemerkung, long kundenId, String eventArt){
		
		LocalDateTime beginnDatum = LocalDateTime.of(beginnJahr, beginnMonat, beginnTag, beginnStunde, beginnMinute); 
		LocalDateTime schlussDatum = LocalDateTime.of(schlussJahr, schlussMonat, schlussTag, schlussStunde, schlussMinute);
		Adresse adresse = new Adresse(strasse, ort, plz);
		Veranstaltung v = new Veranstaltung(beginnDatum, schlussDatum, adresse, bemerkung, kundenId, EventArt.valueOf(eventArt));
		return v;
	}

    /**
     * Save veranstaltung.
     *
     * @param veranstaltung the veranstaltung
     */
    public void saveVeranstaltung(Veranstaltung veranstaltung){
		vRepo.save(veranstaltung);
	}

    /**
     * Gets veranstaltungs repo.
     *
     * @return the veranstaltungs repo
     */
    public VeranstaltungsRepository getVeranstaltungsRepo() {
		return vRepo;
	}

    /**
     * Gets kunden repo.
     *
     * @return the kunden repo
     */
    public KundenRepository getKundenRepo() {
		return kRepo;
	}

    /**
     * Gets mitarbeiter repo.
     *
     * @return the mitarbeiter repo
     */
    public MitarbeiterRepository getMitarbeiterRepo() {
		return mRepo;
	}

	public List<EventArt> getEnumEventArtList() {
		return enumEventArtList;
	}
}
