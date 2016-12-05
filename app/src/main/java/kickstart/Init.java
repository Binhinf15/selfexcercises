package kickstart;

import java.util.Optional;

import org.salespointframework.core.DataInitializer;
import org.springframework.stereotype.Component;

import kickstart.person.Kunde;
import kickstart.person.Mitarbeiter;
import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsVerwaltung;

import kickstart.buchhaltung.BuchhaltungsVerwaltung;
import kickstart.buchhaltung.KundenRechnung;
import kickstart.buchhaltung.GehaltRechnung;

import kickstart.ware.LagerVerwaltung;
import kickstart.ware.Ware;


/**
 * The type Init.
 */
@Component
public class Init implements DataInitializer {

	private final PersonenVerwaltung pVerwaltung;
	private final VeranstaltungsVerwaltung vVerwaltung;
	private final BuchhaltungsVerwaltung bVerwaltung;
	private final LagerVerwaltung lVerwaltung;

	/**
	 * Instantiates a new Init.
	 *
	 * @param pVerwaltung the p verwaltung
	 * @param vVerwaltung the v verwaltung
	 * @param bVerwaltung the b verwaltung
	 * @param lVerwaltung the l verwaltung
	 */
// Konstruktor
	public Init(PersonenVerwaltung pVerwaltung, VeranstaltungsVerwaltung vVerwaltung, BuchhaltungsVerwaltung bVerwaltung, LagerVerwaltung lVerwaltung){

		this.pVerwaltung = pVerwaltung;
		this.vVerwaltung = vVerwaltung;
		this.bVerwaltung = bVerwaltung;
		this.lVerwaltung = lVerwaltung;
	}		
	
	// Methoden
	// methode initialize() wird von Spring beim start automatisch ausgeführt.
	public void initialize() {
		
		initUser(pVerwaltung);
		initVeranstaltung(vVerwaltung, lVerwaltung);
		initRechnung(bVerwaltung);
		initGehalt(bVerwaltung);
		initWare(lVerwaltung);
	}

	private void initUser(PersonenVerwaltung pVerwaltung){	
	
		Mitarbeiter m1 = pVerwaltung.createMitarbeiter("vornameHans", "nachname", "userHans", "straße haha","Berlin","12679", "22", "ADMIN", "Hans@Testmail.de", "01734556");
		pVerwaltung.saveMitarbeiter(m1);
		System.out.println("");
		System.out.println(m1.getId());
		System.out.println(m1.getVorname());
		System.out.println(m1.getUserAccount().getUsername());
		System.out.println(m1.getUserAccount().getPassword());
		System.out.println(m1.toString());
		System.out.println("");
		
		Kunde k1 = pVerwaltung.createKunde("vornamePeter","nachnameKLug", "Straße mimi", "Dresden", "01069", "Peter@testmail.de", "012342345");
		pVerwaltung.saveKunde(k1);
		System.out.println("");
		System.out.println(k1.getId());
		System.out.println(k1.getVorname());
		System.out.println(k1.getAdresse().getStrasse());
		System.out.println(k1.getAdresse().getOrt());
		System.out.println(k1.getAdresse().getPlz());
		System.out.println(k1.getAdresse().toString());
		System.out.println(k1.toString());
		System.out.println("");		
		Kunde k2 = pVerwaltung.createKunde("A","asdf", "Straße haha", "Dresden", "01069", "a@testmail.de", "017634343");
		pVerwaltung.saveKunde(k2);
		Kunde k3 = pVerwaltung.createKunde("B","yxcv", "Straße kiki", "Dresden", "01069", "b@testmail.de", "01787446");
		pVerwaltung.saveKunde(k3);
		Kunde k4 = pVerwaltung.createKunde("BCD","qwer", "Straße lala", "Dresden", "01069", "bcd@testmail.de", "017647737");
		pVerwaltung.saveKunde(k4);
		Kunde k5 = pVerwaltung.createKunde("LKJH","lkjjh", "Straße ohoh", "Dresden", "01069", "lkjh@testmail.de", "7876543");
		pVerwaltung.saveKunde(k5);
		System.out.println(k5.toString());
		pVerwaltung.bearbeiteKunde(5, "g", "g", "g", "g", "g", "g", "g");
		System.out.println(k5.toString());
		
		Mitarbeiter m2 = pVerwaltung.createMitarbeiter("LULU", "nachname2", "userlulu", "straße haha","Berlin","12679", "22", "ADMIN", "Hans@Testmail.de", "0123456");
		pVerwaltung.saveMitarbeiter(m2);
	}

	/**
	 * Init veranstaltung.
	 *
	 * @param vVerwaltung the v verwaltung
	 * @param lVerwaltung the l verwaltung
	 */
	public void initVeranstaltung(VeranstaltungsVerwaltung vVerwaltung, LagerVerwaltung lVerwaltung){
		
		vVerwaltung.getKundenRepo().findAll().forEach((Kunde x)->System.out.println("Kunde hat Id:" + x.getId()));
		
		Optional<Kunde> k1 = vVerwaltung.getKundenRepo().findOne((long) 1);
		Veranstaltung v1 = vVerwaltung.createVeranstaltung(20, 10, 2016, 8, 0, 20, 11, 2016, 15, 0, "Pragerstraße", "Dresden", "01069", "Bemerkung zu der Veranstaltung", k1.get().getId(), "EVENTCATERING");
		vVerwaltung.saveVeranstaltung(v1);
		System.out.println("");
		System.out.println(v1.getBeginnDatum().toString());
		System.out.println(v1.getSchlussDatum().toString());
		System.out.println(v1.getAdresse().toString());
		System.out.println(v1.getBemerkung());
		System.out.println(v1.getKundenId());
		System.out.println(v1.getId());
		System.out.println(v1.getEventArt().name());
		System.out.println(vVerwaltung.getVeranstaltungsRepo().findByKundenId(1).toString());
		System.out.println("");	
		
		
		Optional<Kunde> k2 = vVerwaltung.getKundenRepo().findOne((long) 2);
		Optional<Kunde> k3 = vVerwaltung.getKundenRepo().findOne((long) 3);
		Optional<Kunde> k4 = vVerwaltung.getKundenRepo().findOne((long) 4);
		Veranstaltung v2 = vVerwaltung.createVeranstaltung(16, 10, 2017, 8, 0, 20, 11, 2016, 15, 0, "sdfg", "Dresden", "01069", "Bemerkung zu der Veranstaltung", k3.get().getId(), "EVENTCATERING");
		Veranstaltung v3 = vVerwaltung.createVeranstaltung(17, 11, 2016, 14, 0, 20, 11, 2016, 15, 0, "qwer", "Dresden", "01069", "Bemerkung zu der Veranstaltung", k1.get().getId(), "RENTACOOK");
		Veranstaltung v4 = vVerwaltung.createVeranstaltung(17, 11, 2016, 8, 0, 20, 11, 2016, 15, 0, "yxcvb", "Dresden", "01069", "Bemerkung zu der Veranstaltung", k4.get().getId(), "MOBILEBREAKFAST");
		vVerwaltung.saveVeranstaltung(v2);
		vVerwaltung.saveVeranstaltung(v3);
		vVerwaltung.saveVeranstaltung(v4);
		
		
		System.out.println(vVerwaltung.getMitarbeiterRepo().findOne((long) 1).get().getId());
		v2.getMitarbeiterIdListe().add(vVerwaltung.getMitarbeiterRepo().findOne((long) 1).get().getId());
		v2.getMitarbeiterIdListe().add(vVerwaltung.getMitarbeiterRepo().findOne((long) 2).get().getId());
		
		System.out.println(v2.toString());
		
		Ware w1 = lVerwaltung.createWare("apfel", 12);
		Ware w2 = lVerwaltung.createWare("honig", 3);
		lVerwaltung.saveWare(w1);
		lVerwaltung.saveWare(w2);
	}


	/**
	 * Init rechnung.
	 *
	 * @param bVerwaltung the b verwaltung
	 */
	public void initRechnung (BuchhaltungsVerwaltung bVerwaltung) {
		KundenRechnung krechnung = bVerwaltung.createKundenRechnung(1.0, 2, 1, "wdwddw");
		bVerwaltung.saveKundenRechnung(krechnung);

		System.out.println(bVerwaltung.getCrepo().findAll());
	}

	/**
	 * Init gehalt.
	 *
	 * @param bVerwaltung the b verwaltung
	 */
	public void initGehalt (BuchhaltungsVerwaltung bVerwaltung) {
		GehaltRechnung grechnung = bVerwaltung.createGehaltRechnung("Rolf",1, 2000.0, "bester Arbeiter");
		bVerwaltung.saveGehaltRechnung(grechnung);

		System.out.println(bVerwaltung.getGrepo().findAll());
	}

	/**
	 * Init ware.
	 *
	 * @param lVerwaltung the l verwaltung
	 */
	public void initWare(LagerVerwaltung lVerwaltung){
		Ware w1 = lVerwaltung.createWare("Brot", 12345);
		Ware w2 = lVerwaltung.createWare("Wasser", 566);
		Ware w3 = lVerwaltung.createWare("Schinken", 653);
		
		lVerwaltung.saveWare(w1);
		lVerwaltung.saveWare(w2);
		lVerwaltung.saveWare(w3);
		
		System.out.println(lVerwaltung.getWarenRepo().findAll());

	}
}
