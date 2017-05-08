package kickstart.buchhaltung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.javamoney.moneta.FastMoney;
import org.junit.Test;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;

import kickstart.AbstractIntegrationTests;
import kickstart.person.KundenRepository;
import kickstart.person.MitarbeiterRepository;
import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.VeranstaltungsRepository;
import kickstart.ware.Ware;

public class BuchhaltungTest extends AbstractIntegrationTests  {
	@Autowired KundenRepository kRepo;
	@Autowired MitarbeiterRepository mRepo;
	@Autowired VeranstaltungsRepository vRepo;
	@Autowired KundenRechnungRepository kundenRechnungRepo;
	@Autowired GehaltRechnungRepository gehaltRepo;
	@Autowired PersonenVerwaltung pVerwaltung;
	
	//default Konstruktor
	public BuchhaltungTest(){		
	}
	
	@Test
	public void createGehaltRechnung(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		LocalDate date = LocalDate.of(2000, 01, 01);
		
		int i = 0;
		for(GehaltRechnung g : gehaltRepo.findAll()){
			i++;
		}		
		bVerwaltung.createGehaltRechnung(1L, date);	
		int a = 0;
		for(GehaltRechnung g : gehaltRepo.findAll()){
			a++;
		}	
		assertEquals(i+1, a);	
	}
	
	@Test
	public void getGehaltRechnungListFuerIntervall(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		LocalDate date = LocalDate.of(2000, 01, 01);
		LocalDate date2 = LocalDate.of(2000, 01, 20);
		
		int i = 0;
		for(GehaltRechnung g : gehaltRepo.findAll()){
			i++;
		}
		//Es werden keine doppelten Gehaltsrechnungen erstellt für einen Monat
		bVerwaltung.createGehaltRechnung(1L, date);
		bVerwaltung.createGehaltRechnungForAll(date);
		bVerwaltung.createGehaltRechnungForAll(date2);
		int a = 0;
		for(GehaltRechnung g : gehaltRepo.findAll()){
			a++;
		}	
		assertEquals(i+3, a);
	}
	
	@Test
	public void summeFuerGehaltRechnungIntervall(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		LocalDate date = LocalDate.of(2000, 01, 01);
		LocalDate date2 = LocalDate.of(2000, 01, 31);
		LocalDate date3 = LocalDate.of(2000, 02, 20);
		
		bVerwaltung.createGehaltRechnungForAll(date);
		bVerwaltung.createGehaltRechnungForAll(date3);
		BigDecimal result1 = bVerwaltung.summeFuerGehaltRechnungIntervall(date, date2);
		BigDecimal result2 = bVerwaltung.summeFuerGehaltRechnungIntervall(date, date3);

		assertEquals(4600.0, result1.doubleValue(), 0.001);
		assertEquals(9200.0, result2.doubleValue(), 0.001);		
	}
	
	@Test
	public void getOffeneGehaltRechnung(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		
		LocalDate date = LocalDate.of(2000, 01, 01);
		
		bVerwaltung.createGehaltRechnungForAll(date);
		int i = 0;
		for(GehaltRechnung g : gehaltRepo.findAll()){
			i++;
		}		
		assertEquals(i, bVerwaltung.getOffeneGehaltRechnung().size());
		gehaltRepo.findOne(1L).get().setBezahlt(true);
		assertEquals(i-1, bVerwaltung.getOffeneGehaltRechnung().size());
	}
	
	@Test
	public void deleteGehaltRechnung(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		LocalDate date = LocalDate.of(2000, 01, 01);
		
		bVerwaltung.createGehaltRechnungForAll(date);
		bVerwaltung.deleteGehaltRechnung(1L);
		
		assertFalse(gehaltRepo.exists(1L));
	}
	
	@Test
	public void createKundenRechnung(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		
		Ware key = new Ware("ware", FastMoney.of(100, "EUR"), Metric.valueOf("UNIT"));
		Quantity value = Quantity.of(10);
		HashMap<Ware, Quantity> warenliste = new HashMap<Ware,Quantity>(0);
		warenliste.put(key, value);
				
		int i = 0;
		for(KundenRechnung kr : kundenRechnungRepo.findAll()){
			i++;
		}		
		bVerwaltung.createKundenRechnung(1L, 1L, warenliste);
		int a = 0;
		for(KundenRechnung kr : kundenRechnungRepo.findAll()){
			a++;
		}
		
		assertEquals(i+1,a);	
	}
	
	@Test
	public void getKundenRechnungListFuerIntervall(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		
		Ware key = new Ware("ware", FastMoney.of(100, "EUR"), Metric.valueOf("UNIT"));
		Quantity value = Quantity.of(10);
		HashMap<Ware, Quantity> warenliste = new HashMap<Ware,Quantity>(0);
		warenliste.put(key, value);
		bVerwaltung.createKundenRechnung(1L, 1L, warenliste);
		
		LocalDate date3 = LocalDate.now().minusDays(2);
		LocalDate date4 = LocalDate.now().minusDays(1);
		assertTrue(bVerwaltung.getKundenRechnungListFuerIntervall(date3, date4).isEmpty() );
	}
	
	@Test
	public void summeFuerKundenRechnungsIntervall(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		Ware key = new Ware("ware", FastMoney.of(100, "EUR"), Metric.valueOf("UNIT"));
		Quantity value = Quantity.of(10);
		HashMap<Ware, Quantity> warenliste = new HashMap<Ware,Quantity>(0);
		warenliste.put(key, value);
		bVerwaltung.createKundenRechnung(1L, 1L, warenliste);
		bVerwaltung.createKundenRechnung(1L, 2L, warenliste);
		
		LocalDate date = LocalDate.now().minusDays(1);
		LocalDate date2 = LocalDate.now().plusDays(1);
		assertEquals(2000, bVerwaltung.summeFuerKundenRechnungsIntervall(date, date2).doubleValue(), 0.001);
		LocalDate date3 = LocalDate.now().minusDays(1);
		LocalDate date4 = LocalDate.now().minusDays(1);
		assertEquals(0, bVerwaltung.summeFuerKundenRechnungsIntervall(date3, date4).doubleValue(), 0.001);	
	}
	
	@Test
	public void getOffeneKundenRechnung(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		Ware key = new Ware("ware", FastMoney.of(100, "EUR"), Metric.valueOf("UNIT"));
		Quantity value = Quantity.of(10);
		HashMap<Ware, Quantity> warenliste = new HashMap<Ware,Quantity>(0);
		warenliste.put(key, value);
		bVerwaltung.createKundenRechnung(1L, 1L, warenliste);
		
		assertEquals(3, bVerwaltung.getOffeneKundenRechnung().size());
		assertTrue(bVerwaltung.getOffeneKundenRechnung().contains(kundenRechnungRepo.findOne(1L).get()) );
		kundenRechnungRepo.findOne(1L).get().setBezahlt(true);
		assertFalse(bVerwaltung.getOffeneKundenRechnung().contains(kundenRechnungRepo.findOne(1L).get()) );
	}
	
	@Test
	public void deleteKundenRechnung(){
		BuchhaltungsVerwaltung bVerwaltung = new BuchhaltungsVerwaltung(kRepo, mRepo, vRepo, gehaltRepo, kundenRechnungRepo, pVerwaltung);
		Ware key = new Ware("ware", FastMoney.of(100, "EUR"), Metric.valueOf("UNIT"));
		Quantity value = Quantity.of(10);
		HashMap<Ware, Quantity> warenliste = new HashMap<Ware,Quantity>(0);
		warenliste.put(key, value);
		bVerwaltung.createKundenRechnung(1L, 1L, warenliste);
		
		assertTrue(kundenRechnungRepo.exists(1L));
		bVerwaltung.deleteKundenRechnung(1L);
		assertFalse(kundenRechnungRepo.exists(1L));
	}
	
}
