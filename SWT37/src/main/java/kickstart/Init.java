package kickstart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.salespointframework.core.DataInitializer;
import org.springframework.stereotype.Component;

import kickstart.buchhaltung.BuchhaltungsVerwaltung;
import kickstart.buchhaltung.KundenRechnung;
import kickstart.person.Kunde;
import kickstart.person.Mitarbeiter;
import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsVerwaltung;
import kickstart.ware.LagerVerwaltung;



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
		
//		initUser(pVerwaltung);
//		initVeranstaltung(vVerwaltung, lVerwaltung, pVerwaltung);
//		initRechnung(bVerwaltung);
//		initGehalt(bVerwaltung);
//		initWare(lVerwaltung);
	}

	private void initUser(PersonenVerwaltung pVerwaltung){	
	
		Mitarbeiter m1 = pVerwaltung.createMitarbeiter("Hannes", "Wurst", "Oppermannstr. 15","Berlin","12679", "boss", "123", "ROLE_ADMIN", "Hans@Testmail.de", "01734556", 3000.00);
		pVerwaltung.saveMitarbeiter(m1);	
		Mitarbeiter m2 = pVerwaltung.createMitarbeiter("Lulu", "Käse", "Prager Straße 20","Dresden","01069", "lulu", "123", "ROLE_KOCH", "lulu@Testmail.de", "0123456", 800.00);
		pVerwaltung.saveMitarbeiter(m2);
		Mitarbeiter m3 = pVerwaltung.createMitarbeiter("Koko", "Kokusnuss", "Kroko Straße 09","Leibzig", "12679", "koko", "123", "ROLE_SERVICE", "koko@Testmail.de", "0123456", 800.00);
		pVerwaltung.saveMitarbeiter(m3);
		
//		System.out.println(m1.getGehalt());
		
		Kunde k1 = pVerwaltung.createKunde("Peter","Klug", "Straße mimi", "Dresden", "01069", "Peter@testmail.de", "012342345");
		pVerwaltung.saveKunde(k1);
		Kunde k2 = pVerwaltung.createKunde("Günther","Lauch", "Straße haha", "Dresden", "01069", "a@testmail.de", "017634343");
		pVerwaltung.saveKunde(k2);
		Kunde k3 = pVerwaltung.createKunde("Horst","Müller", "Straße kiki", "Dresden", "01069", "b@testmail.de", "01787446");
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
		Veranstaltung v1 = vVerwaltung.createVeranstaltung("2010-05-16", "08:00", "2011-05-16", "15:00", "Pragerstraße", "Dresden", "01069", "Bemerkung zu der Veranstaltung", k1.get().getId(), "EVENTCATERING", "Sommerfest","");
		Veranstaltung v2 = vVerwaltung.createVeranstaltung("2010-05-16", "10:00", "2011-05-16", "17:30", "sdfg", "Dresden", "01069", "Bemerkung zu der Veranstaltung", k2.get().getId(), "EVENTCATERING", "Winterfest","");
		v1.getMitarbeiterIdListe().add(1L);
		v2.getMitarbeiterIdListe().add(2L);
		vVerwaltung.saveVeranstaltung(v1);
		vVerwaltung.saveVeranstaltung(v2);		
		
		pVerwaltung.deaktiviereMitarbeiter(3L);
		LocalDateTime beginn = LocalDateTime.parse("2012-05-16T09:00");
		LocalDateTime schluss = LocalDateTime.parse("2015-05-16T19:00");
		System.out.println(pVerwaltung.getFreieMitarbeiter(beginn, schluss));
	}


	/**
	 * Init rechnung.
	 *
	 * @param bVerwaltung the b verwaltung
	 */
	public void initRechnung (BuchhaltungsVerwaltung bVerwaltung) {
		KundenRechnung kr = bVerwaltung.createKundenRechnungTest();
		kr.setBezahlt(false);
		kr.setDatum(LocalDate.now());
		kr.setKundenId(1L);
		kr.setVeranstaltungsId(1L);
		kr.setPreis(555.33);
		
		KundenRechnung kr2 = bVerwaltung.createKundenRechnungTest();
		kr2.setBezahlt(false);
		kr2.setDatum(LocalDate.now());
		kr2.setKundenId(2L);
		kr2.setVeranstaltungsId(2L);
		kr2.setPreis(100);
	}

	/**
	 * Init gehalt.
	 *
	 * @param bVerwaltung the b verwaltung
	 */
	public void initGehalt (BuchhaltungsVerwaltung bVerwaltung) {
		LocalDate date = LocalDate.of(2017,1,10);
//		LocalDate date2 = LocalDate.of(2017,1,11);
//		bVerwaltung.createGehaltRechnung(1L, date);
		bVerwaltung.createGehaltRechnungForAll(date);
//		bVerwaltung.createGehaltRechnungForAll(date2);
		System.out.println("point0 alleGehaltRechnungen: " + bVerwaltung.getGehaltRepo().findAll());

		LocalDate c = LocalDate.of(2017,1,10);
		LocalDate d = LocalDate.of(2017,1,10);
		System.out.println("point0 " + bVerwaltung.summeFuerGehaltRechnungIntervall(c,d));
		System.out.println("point0 " + bVerwaltung.getGehaltRechnungListFuerIntervall(c,d));


	}

	/**
	 * Init ware.
	 *
	 * @param lVerwaltung the l verwaltung
	 */
	public void initWare(LagerVerwaltung lVerwaltung){
		lVerwaltung.createInventoryItem("Ananas", 5.49, 100,"");
		lVerwaltung.createInventoryItem("Wasserflasche 1,0l", 0.95, 30,"");
		lVerwaltung.createInventoryItem("Toastbrot 500g", 0.49, 100,"");
		lVerwaltung.createInventoryItem("Artischocke", 2.49, 100,"");
		lVerwaltung.createInventoryItem("Bier", 100, 200,"");
		/*
		for(InventoryItem i : lVerwaltung.getInventory().findAll()){
			System.out.println("Item1: " + i.getProduct().getName()  + " " + i.getQuantity());
		}
		
		for(Ware i : lVerwaltung.getWarenRepo().findAll()){
			System.out.println("Ware1: " + i.toString());
		}
		
		InventoryItem item = lVerwaltung.getInventory().findByProductName("Bier");
		Optional<Ware> ware = lVerwaltung.getWarenRepo().findOne(item.getProduct().getId());
		item.getProduct().setPrice(Money.of(12345, "EUR"));
		ware.get().setBeschreibung("test");
		ware.get().setName("HUhuh");
		
		for(InventoryItem i : lVerwaltung.getInventory().findAll()){
			System.out.println("Item2: " + i.getProduct().getName() + " " + i.getQuantity());
		}
		
		for(Ware i : lVerwaltung.getWarenRepo().findAll()){
			System.out.println("Ware2: " + i.toString());
		}
		*/
	}
}
