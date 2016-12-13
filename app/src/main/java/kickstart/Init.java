package kickstart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		initVeranstaltung(vVerwaltung, lVerwaltung, pVerwaltung);
		initRechnung(bVerwaltung);
		initGehalt(bVerwaltung);
		initWare(lVerwaltung);
	}

	private void initUser(PersonenVerwaltung pVerwaltung){	
	
		Mitarbeiter m1 = pVerwaltung.createMitarbeiter("vornameHans", "nachname", "boss", "straße haha","Berlin","12679", "123", "ROLE_ADMIN", "Hans@Testmail.de", "01734556");
		pVerwaltung.saveMitarbeiter(m1);	
		Mitarbeiter m2 = pVerwaltung.createMitarbeiter("lulu", "nachname2", "lulu", "straße haha","Berlin","12679", "123", "ROLE_KOCH", "Hans@Testmail.de", "0123456");
		pVerwaltung.saveMitarbeiter(m2);
		
		Kunde k1 = pVerwaltung.createKunde("vornamePeter","nachnameKLug", "Straße mimi", "Dresden", "01069", "Peter@testmail.de", "012342345");
		pVerwaltung.saveKunde(k1);
		Kunde k2 = pVerwaltung.createKunde("A","asdf", "Straße haha", "Dresden", "01069", "a@testmail.de", "017634343");
		pVerwaltung.saveKunde(k2);
		Kunde k3 = pVerwaltung.createKunde("B","yxcv", "Straße kiki", "Dresden", "01069", "b@testmail.de", "01787446");
		pVerwaltung.saveKunde(k3);
	}

	/**
	 * Init veranstaltung.
	 *
	 * @param vVerwaltung the v verwaltung
	 * @param lVerwaltung the l verwaltung
	 */
	public void initVeranstaltung(VeranstaltungsVerwaltung vVerwaltung, LagerVerwaltung lVerwaltung, PersonenVerwaltung pVerwaltung){
		
		vVerwaltung.getKundenRepo().findAll().forEach((Kunde x)->System.out.println("Kunde hat Id:" + x.getId()));
			
		Optional<Kunde> k1 = vVerwaltung.getKundenRepo().findOne((long) 1);
		Optional<Kunde> k2 = vVerwaltung.getKundenRepo().findOne((long) 2);
		Veranstaltung v1 = vVerwaltung.createVeranstaltung("2010-05-16", "08:00", "2011-05-16", "15:00", "Pragerstraße", "Dresden", "01069", "Bemerkung zu der Veranstaltung", k1.get().getId(), "EVENTCATERING");
		Veranstaltung v2 = vVerwaltung.createVeranstaltung("2010-05-16", "10:00", "2011-05-16", "17:30", "sdfg", "Dresden", "01069", "Bemerkung zu der Veranstaltung", k2.get().getId(), "EVENTCATERING");
		v1.getMitarbeiterIdListe().add(2L);
		v1.getMitarbeiterIdListe().add(1L);
		v2.getMitarbeiterIdListe().add(2L);
		vVerwaltung.saveVeranstaltung(v1);
		vVerwaltung.saveVeranstaltung(v2);		
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
		Ware w1 = lVerwaltung.createWare("Brot", 12345, 2.1, "honig");
		Ware w2 = lVerwaltung.createWare("Wasser", 566, 0.5, "cola");
		Ware w3 = lVerwaltung.createWare("Schinken", 653, 3.99, "schaf");
		
		lVerwaltung.saveWare(w1);
		lVerwaltung.saveWare(w2);
		lVerwaltung.saveWare(w3);
		
		System.out.println(lVerwaltung.getWarenRepo().findAll());

	}
}
