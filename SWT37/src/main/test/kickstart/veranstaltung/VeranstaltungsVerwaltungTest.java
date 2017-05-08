package kickstart.veranstaltung;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kickstart.AbstractIntegrationTests;
import kickstart.person.Kunde;
import kickstart.person.KundenRepository;
import kickstart.person.Mitarbeiter;
import kickstart.person.MitarbeiterRepository;
import kickstart.person.PersonenVerwaltung;

import org.salespointframework.useraccount.UserAccountManager;

public class VeranstaltungsVerwaltungTest extends AbstractIntegrationTests   {
	
	@Autowired VeranstaltungsRepository vRepo;
	@Autowired KundenRepository kRepo;
	@Autowired MitarbeiterRepository mRepo;
	@Autowired UserAccountManager uaManager;
	//@Autowired List<EventArt> enumEventArtList;
	
	public VeranstaltungsVerwaltungTest(){	
	}
	
	@Test
    public void createVeranstaltung() {
		
		VeranstaltungsVerwaltung vVerwaltung = new VeranstaltungsVerwaltung (vRepo, kRepo, mRepo);
		Veranstaltung v = vRepo.save(vVerwaltung.createVeranstaltung("2000-01-01", "10:00", "2000-01-01", "20:00"
																	, "e", "f", "g", "h", 1 , "EVENTCATERING","Sommerfest","Gabel"));		
		assertNotNull("Kunde darf nicht null sein", v);
		assertEquals("Veranstaltungsinfo stimmt", "2000-01-01T10:00", v.getBeginnDatum().toString());
		assertEquals("Veranstaltungsinfo stimmt", "2000-01-01T20:00", v.getSchlussDatum().toString());
		assertEquals("Veranstaltungsinfo stimmt", "e" ,v.getAdresse().getStrasse());
		assertEquals("Veranstaltungsinfo stimmt", "f" ,v.getAdresse().getOrt());
		assertEquals("Veranstaltungsinfo stimmt", "g" ,v.getAdresse().getPlz());
		assertEquals("Veranstaltungsinfo stimmt","h" ,v.getBemerkung());
		assertEquals("Veranstaltungsinfo stimmt", 1 ,v.getKundenId());
		assertEquals("Veranstaltungsinfo stimmt", "EVENTCATERING" ,v.getEventArt().toString());
		assertEquals("Veranstaltungsinfo stimmt", "Sommerfest" ,v.getTitel());
		assertEquals("Veranstaltungsinfo stimmt", "Gabel" ,v.getZubehoer());
		
    }

    @Test
    public void bearbeiteVeranstaltung() {
    	
    	VeranstaltungsVerwaltung vVerwaltung = new VeranstaltungsVerwaltung (vRepo, kRepo, mRepo);

    }

    @Test
    public void getZugewieseneMitarbeiter() {
    	
    	VeranstaltungsVerwaltung vVerwaltung = new VeranstaltungsVerwaltung(vRepo, kRepo, mRepo);
    	PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo);
    	
    	Veranstaltung v = vRepo.save(vVerwaltung.createVeranstaltung("2000-01-01", "10:00", "2000-01-01", "20:00"
																	, "e", "f", "g", "h", 1 , "EVENTCATERING","Sommerfest","Gabel"));
    	Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "b", "c", "d", "e", "sdfghjjh", "g", "ROLE_ADMIN", "h", "i", 100));
    	
    	assertTrue(vVerwaltung.getZugewieseneMitarbeiter(v.getMitarbeiterIdListe()).isEmpty() );
    	v.getMitarbeiterIdListe().add(m.getId());
    	assertTrue(vVerwaltung.getZugewieseneMitarbeiter(v.getMitarbeiterIdListe()).contains(m));
  
    }

    @Test
    public void deleteVeranstaltung() {
    	
		VeranstaltungsVerwaltung vVerwaltung = new VeranstaltungsVerwaltung(vRepo, kRepo, mRepo);
		
		Veranstaltung v = vRepo.save(vVerwaltung.createVeranstaltung("2000-01-01", "10:00", "2000-01-01", "20:00"
																	, "e", "f", "g", "h", 1 , "EVENTCATERING","Sommerfest","Gabel"));
		
		assertTrue(vRepo.findAllByOrderById().contains(v));
		vRepo.delete(v.getId());		
		assertFalse(vRepo.findAllByOrderById().contains(v));

    }

    @Test
    public void saveVeranstaltung() {
    	
		VeranstaltungsVerwaltung vVerwaltung = new VeranstaltungsVerwaltung(vRepo, kRepo, mRepo);
		
		Veranstaltung v = vRepo.save(vVerwaltung.createVeranstaltung("2000-01-01", "10:00", "2000-01-01", "20:00"
																	, "e", "f", "g", "h", 1 , "EVENTCATERING","Sommerfest","Gabel"));
		
		assertThat(vRepo.findAll()).contains(v);
    }

}