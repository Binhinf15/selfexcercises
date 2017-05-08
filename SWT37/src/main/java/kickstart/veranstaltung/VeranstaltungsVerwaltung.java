package kickstart.veranstaltung;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kickstart.adresse.Adresse;
import kickstart.person.KundenRepository;
import kickstart.person.Mitarbeiter;
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
											, String strasse, String ort, String plz, String bemerkung, long kundenId, String eventArt, String titel, String zubehoer){
		
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate beginnDate = LocalDate.parse(beginnDatum,dateFormatter);
        LocalTime beginnTime = LocalTime.parse(beginnZeit,timeFormatter);
        LocalDate schlussDate = LocalDate.parse(schlussDatum,dateFormatter);
        LocalTime schlussTime = LocalTime.parse(schlussZeit,timeFormatter);	
		LocalDateTime beginn = beginnDate.atTime(beginnTime); 
		LocalDateTime schluss = schlussDate.atTime(schlussTime); 
		
		Adresse adresse = new Adresse(strasse, ort, plz);
		Veranstaltung v = new Veranstaltung(beginn, schluss, adresse, bemerkung, kundenId, EventArt.valueOf(eventArt), titel, zubehoer);
		
		return v;
	}
	
	public void bearbeiteVeranstaltung(long veranstaltungsId, String beginnDatum, String beginnZeit, String schlussDatum, String schlussZeit
									, String strasse, String ort, String plz, String bemerkung, long kundenId, String eventArt, String titel, String zubehoer){
			
		Veranstaltung v = vRepo.findOne(veranstaltungsId).get();
		
		LocalDate beginnDate = LocalDate.parse(beginnDatum);
        LocalTime beginnTime = LocalTime.parse(beginnZeit);
        LocalDate schlussDate = LocalDate.parse(schlussDatum);
        LocalTime schlussTime = LocalTime.parse(schlussZeit);	
		LocalDateTime beginn = beginnDate.atTime(beginnTime); 
		LocalDateTime schluss = schlussDate.atTime(schlussTime); 
		
		v.setBeginnDatum(beginn);
		v.setSchlussDatum(schluss);		
		v.getAdresse().setStrasse(strasse);
		v.getAdresse().setOrt(ort);
		v.getAdresse().setPlz(plz);
		v.setBemerkung(bemerkung);
		v.setKundenId(kundenId);
		v.setEventArt(EventArt.valueOf(eventArt));
		v.setTitel(titel);
		v.setZubehoer(zubehoer);
		saveVeranstaltung(v);
	}
	
	public List<Mitarbeiter> getZugewieseneMitarbeiter(List<Long> mitarbeiterIds){
		List<Mitarbeiter> zugewieseneMitarbeiter = new ArrayList<Mitarbeiter>(0);		
		for(Long mId : mitarbeiterIds){
			Mitarbeiter mitarbeiter = mRepo.findOne(mId).get();
			zugewieseneMitarbeiter.add(mitarbeiter);
		}
		return zugewieseneMitarbeiter;	
	}
	
	public void deleteVeranstaltung(long id){
		vRepo.delete(id);
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
