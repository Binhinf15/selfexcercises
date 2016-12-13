package kickstart.veranstaltung;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
	public Veranstaltung createVeranstaltung(String beginnDatum, String beginnZeit, String schlussDatum, String schlussZeit
											, String strasse, String ort, String plz, String bemerkung, long kundenId, String eventArt){
		
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate beginnDate = LocalDate.parse(beginnDatum,dateFormatter);
        LocalTime beginnTime = LocalTime.parse(beginnZeit,timeFormatter);
        LocalDate schlussDate = LocalDate.parse(schlussDatum,dateFormatter);
        LocalTime schlussTime = LocalTime.parse(schlussZeit,timeFormatter);	
		LocalDateTime beginn = beginnDate.atTime(beginnTime); 
		LocalDateTime schluss = schlussDate.atTime(schlussTime); 
		
		Adresse adresse = new Adresse(strasse, ort, plz);
		Veranstaltung v = new Veranstaltung(beginn, schluss, adresse, bemerkung, kundenId, EventArt.valueOf(eventArt));
		
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
