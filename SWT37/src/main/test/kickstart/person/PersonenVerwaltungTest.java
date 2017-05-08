package kickstart.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;

import kickstart.person.AccountRolle;
import kickstart.person.KundenRepository;
import kickstart.person.MitarbeiterRepository;
import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsRepository;
import kickstart.veranstaltung.VeranstaltungsVerwaltung;
import kickstart.AbstractIntegrationTests;

public class PersonenVerwaltungTest extends AbstractIntegrationTests   {
	
	@Autowired UserAccountManager uaManager;
	@Autowired MitarbeiterRepository mRepo;
	@Autowired KundenRepository kRepo;
	@Autowired VeranstaltungsRepository vRepo;
//	@Autowired List<AccountRolle> enumAccountRolleList;
	
	//default Konstruktor
	public PersonenVerwaltungTest(){	
	}
	
	@Test
	public void createMitarbeiter(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
	
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		
		assertNotNull("Mitarbeiter darf nicht null sein", m);
		assertEquals("MitarbeiterVorname soll a sein", "a", m.getVorname() );
		assertEquals("MitarbeiterNachname soll b sein", "b" , m.getNachname() );
		assertEquals("Straße soll c sein", "c", m.getAdresse().getStrasse() );
		assertEquals("Ort soll d sein", "d", m.getAdresse().getOrt() );
		assertEquals("PLZ soll e sein", "e", m.getAdresse().getPlz() );
		assertEquals("Username soll f sein", "f", m.getUserAccount().getUsername() );		
		assertEquals("Email soll f sein", "h", m.getEmail() );
		assertEquals("Telefonnummer soll g sein", "i", m.getTelefon() );
		assertEquals("Gehalt soll 100 sein",100,m.getGehalt(),0.001);
	}
	
	@Test
	public void bearbeiteMitarbeiter(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		pVerwaltung.bearbeiteMitarbeiter(m.getId(), "a2", "b2", "c2", "d2", "e2", "ROLE_KOCH", "h2", "i2", 200);
		
		assertEquals("MitarbeiterVorname soll a2 sein", "a2", m.getVorname() );
		assertEquals("MitarbeiterNachname soll b2 sein", "b2", m.getNachname() );
		assertEquals("Straße soll c2 sein", "c2", m.getAdresse().getStrasse() );
		assertEquals("Ort soll d2 sein", "d2", m.getAdresse().getOrt() );
		assertEquals("PLZ soll e2 sein", "e2", m.getAdresse().getPlz() );
		assertEquals("Rolle soll ROLE_KOCH sein", "ROLE_KOCH", m.getUserAccount().getRoles() );		
		assertEquals("Email soll f sein", "h2", m.getEmail() );
		assertEquals("Telefonnummer soll g sein", "i2", m.getTelefon() );
		assertEquals("Gehalt soll 100 sein", 200, m.getGehalt(),0.001);
	}
	
	@Test
	public void getFreieMitarbeiter(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		VeranstaltungsVerwaltung vVerwaltung = new VeranstaltungsVerwaltung(vRepo, kRepo, mRepo);	
		
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		Kunde k = kRepo.save(pVerwaltung.createKunde("a", "b", "c", "d", "e", "f", "g"));
		LocalDateTime beginn = LocalDateTime.of(2000, 01, 20, 00, 00);
		LocalDateTime schluss = LocalDateTime.of(2000, 01, 21, 00, 00);
		
		//all Mitarbeiter sollten zur Verfügung stehen
		for(Mitarbeiter mitarbeiter : mRepo.findAll()){
			assertThat(pVerwaltung.getFreieMitarbeiter(beginn, schluss)).contains(mitarbeiter);
		}
		
		//Mitarbeiter m wird zu Veranstaltung zugeordnet und sollte nicht mehr frei sein
		Veranstaltung v = vRepo.save(vVerwaltung.createVeranstaltung(beginn.toLocalDate().toString(), beginn.toLocalTime().toString()
									, schluss.toLocalDate().toString(), schluss.toLocalTime().toString()
									, "a", "b", "c", "d", k.getId(), "EVENTCATERING", "e", "f"));
		v.getMitarbeiterIdListe().add(m.getId());
		
		assertFalse(pVerwaltung.getFreieMitarbeiter(beginn, schluss).contains(m));
		//Prüfen ob restliche Mitarbeiter noch verfügbar; sollte alle bis auf m sein
		List<Mitarbeiter> freieMitarbeiterListe = (List<Mitarbeiter>) mRepo.findAll();
		freieMitarbeiterListe.remove(m);
		for(Mitarbeiter mitarbeiter : freieMitarbeiterListe){
			assertThat(pVerwaltung.getFreieMitarbeiter(beginn, schluss)).contains(mitarbeiter);
		}
	}
	
	@Test
	public void aktiviereMitarbeiter(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		pVerwaltung.aktiviereMitarbeiter(m.getId());
		
		assertTrue(m.getUserAccount().isEnabled());	
	}
	
	@Test
	public void deaktiviereMitarbeiter(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		pVerwaltung.deaktiviereMitarbeiter(m.getId());
		
		assertFalse(m.getUserAccount().isEnabled());	
	}
	
	@Test
	public void findAll(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		assertEquals(3, pVerwaltung.findAll().size());
	}
	
	@Test
	public void findAllAktiv(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		
		assertEquals(4, pVerwaltung.findAllAktiv().size());
		pVerwaltung.deaktiviereMitarbeiter(m.getId());
		assertEquals(3, pVerwaltung.findAllAktiv().size());		
	}
	
	@Test
	public void findAllInaktiv(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		
		assertEquals(0, pVerwaltung.findAllInaktiv().size());
		pVerwaltung.deaktiviereMitarbeiter(m.getId());
		assertEquals("findAllAktiv soll 3 sein",3, pVerwaltung.findAllAktiv().size());		
	}
	
	@Test
	public void findAktivMitarbeiterSortNachId(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		List<Mitarbeiter> sortedList = pVerwaltung.findAktivMitarbeiterSortNachId();
		assertEquals("Länge der sortedList muss 3 sein", 3, sortedList.size());
		for(int i=0; i < sortedList.size()-1; i++ ){
			assertTrue(sortedList.get(i).getId() < sortedList.get(i+1).getId());
		}	
	}
	
	@Test
	public void findInaktivMitarbeiterSortNachId(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		for(Mitarbeiter mitarbeiter : mRepo.findAll()){
			mitarbeiter.getUserAccount().setEnabled(false);
		}
		
		List<Mitarbeiter> sortedList = pVerwaltung.findInaktivMitarbeiterSortNachId();
		
		for(int i=0; i < sortedList.size()-1; i++ ){
			assertTrue(sortedList.get(i).getId() < sortedList.get(i+1).getId());
		}	
	}
	
	@Test
	public void findAktivMitarbeiterSortNachVorname(){
	
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
			
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("x", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		Mitarbeiter m2 = mRepo.save(pVerwaltung.createMitarbeiter("y", "b", "c", "d", "e", "f2", "g", "ROLE_ADMIN", "h", "i", 100));
		
		List<Mitarbeiter> result = pVerwaltung.findAktivMitarbeiterSortNachVorname();
		
		assertEquals("MitarbeiterVorname 1", "x", result.get(3).getVorname());
		assertEquals("MitarbeiterVorname 2", "y", result.get(4).getVorname());
	}
	
	@Test
	public void findInaktivMitarbeiterSortNachVorname(){
	
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
			
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("x", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		Mitarbeiter m2 = mRepo.save(pVerwaltung.createMitarbeiter("y", "b", "c", "d", "e", "f2", "g", "ROLE_ADMIN", "h", "i", 100));
		m.getUserAccount().setEnabled(false);
		m2.getUserAccount().setEnabled(false);
		
		List<Mitarbeiter> result = pVerwaltung.findInaktivMitarbeiterSortNachVorname();
		
		assertEquals("MitarbeiterVorname 1", "x", result.get(0).getVorname());
		assertEquals("MitarbeiterVorname 2", "y", result.get(1).getVorname());
	}
	
	@Test
	public void findAktivMitarbeiterSortNachName(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "y", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		Mitarbeiter m2 = mRepo.save(pVerwaltung.createMitarbeiter("a", "x", "c", "d", "e", "f2", "g", "ROLE_ADMIN", "h", "i", 100));
			
		List<Mitarbeiter> result = pVerwaltung.findAktivMitarbeiterSortNachName();
		
		assertEquals("MitarbeiterNachname 1", "x", result.get(3).getNachname());
		assertEquals("MitarbeiterNachname 2", "y", result.get(4).getNachname());	
	}
	
	@Test
	public void findInaktivMitarbeiterSortNachName(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "y", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		Mitarbeiter m2 = mRepo.save(pVerwaltung.createMitarbeiter("a", "x", "c", "d", "e", "f2", "g", "ROLE_ADMIN", "h", "i", 100));
		m.getUserAccount().setEnabled(false);
		m2.getUserAccount().setEnabled(false);
		
		List<Mitarbeiter> result = pVerwaltung.findInaktivMitarbeiterSortNachName();
		
		assertEquals("MitarbeiterNachname 1", "x", result.get(0).getNachname());
		assertEquals("MitarbeiterNachname 2", "y", result.get(1).getNachname());	
	}
	
	@Test
	public void createKunde() {
		
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Kunde k = pVerwaltung.createKunde("a", "b", "c", "d", "e", "f", "g");
		
		assertNotNull("Kunde darf nicht null sein", k);
		assertEquals("KundenVorname soll a sein", "a", k.getVorname() );
		assertEquals("KundenNachname soll b sein", "b" , k.getNachname() );
		assertEquals("Straße soll c sein", "c", k.getAdresse().getStrasse() );
		assertEquals("Ort soll d sein", "d", k.getAdresse().getOrt() );
		assertEquals("PLZ soll e sein", "e", k.getAdresse().getPlz() );
		assertEquals("Email soll f sein", "f", k.getEmail() );
		assertEquals("Telefonnummer soll g sein", "g", k.getTelefon() );
	}
	
	@Test
	public void bearbeiteKunde() {
		
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
			
		Kunde k = kRepo.save(pVerwaltung.createKunde("a", "b", "c", "d", "e", "f", "g"));
		kRepo.save(pVerwaltung.bearbeiteKunde(k.getId(), "a2", "b2", "c2", "d2", "e2", "f2", "g2"));
		
		assertEquals("Kundenvorname muss a2 sein","a2" ,k.getVorname( ));
		assertEquals("b2" ,k.getNachname( ));
		assertEquals("c2" ,k.getAdresse().getStrasse());
		assertEquals("d2" ,k.getAdresse().getOrt());
		assertEquals("e2" ,k.getAdresse().getPlz());
		assertEquals("f2" ,k.getEmail());
		assertEquals("g2" ,k.getTelefon());
	}
	
	@Test
	public void sucheKunde(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Kunde k1 = kRepo.save(pVerwaltung.createKunde("Hans", "b", "c", "d", "e", "f", "g"));
		Kunde k2 = kRepo.save(pVerwaltung.createKunde("Tim", "b", "c", "d", "e", "f", "g"));
		Kunde k3 = kRepo.save(pVerwaltung.createKunde("Anton", "b", "c", "d", "e", "f", "g"));
		
		List<Kunde> result = pVerwaltung.sucheKunde("a");
		
		assertThat(result).contains(k1);
		assertThat(result).contains(k3);
	}
	
	@Test
	public void findKundeSortNachVorname(){
	
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
			
		kRepo.save(pVerwaltung.createKunde("y", "b", "c", "d", "e", "f", "g"));
		kRepo.save(pVerwaltung.createKunde("x", "b", "c", "d", "e", "f", "g"));
		
		List<Kunde> result = pVerwaltung.findKundeSortNachVorname();
		
		assertEquals("KundeVorname 1", "x", result.get(3).getVorname());
		assertEquals("KundeVorname 2", "y", result.get(4).getVorname());
	}
	
	@Test
	public void findKundeSortNachName(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		kRepo.save(pVerwaltung.createKunde("a", "y", "c", "d", "e", "f", "g"));
		kRepo.save(pVerwaltung.createKunde("a", "x", "c", "d", "e", "f", "g"));
		
		List<Kunde> result = pVerwaltung.findKundeSortNachName();
		
		assertEquals("KundeNachname 1", "x", result.get(3).getNachname());
		assertEquals("KundeNachname 2", "y", result.get(4).getNachname());	
	}
	
	@Test
	public void saveKunde(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Kunde k = kRepo.save(pVerwaltung.createKunde("a", "b", "c", "d", "e", "f", "g"));
		
		assertThat(kRepo.findAll()).contains(k);
	}
	
	@Test
	public void saveMitarbeiter(){
		PersonenVerwaltung pVerwaltung = new PersonenVerwaltung(uaManager, mRepo, kRepo, vRepo) ;
		
		Mitarbeiter m = mRepo.save(pVerwaltung.createMitarbeiter("a", "b", "c", "d", "e", "f", "g", "ROLE_ADMIN", "h", "i", 100));
		
		assertThat(mRepo.findAll()).contains(m);
	}
	
}	