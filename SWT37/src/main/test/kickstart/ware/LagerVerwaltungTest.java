package kickstart.ware;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.javamoney.moneta.FastMoney;
import org.junit.Test;
import org.salespointframework.inventory.InventoryItem;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;

import kickstart.AbstractIntegrationTests;
import kickstart.adresse.Adresse;
import kickstart.veranstaltung.EventArt;
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsRepository;

public class LagerVerwaltungTest  extends AbstractIntegrationTests{
	@Autowired InventoryItemRepository inventory;
	@Autowired WarenRepository wRepo;
	@Autowired VeranstaltungsRepository vRepo;
	
	// default Konstruktor
	public LagerVerwaltungTest(){
	}
	
	@Test
	public void createWare(){
		LagerVerwaltung lVerwaltung = new LagerVerwaltung(inventory, wRepo, vRepo);
		
		InventoryItem item = lVerwaltung.createInventoryItem("wasser", 10, 100, "kaltes Wasser");
		
		assertTrue(inventory.findOne(item.getId()).get().equals(item));
	}
	
	@Test
	public void sucheItems(){
		LagerVerwaltung lVerwaltung = new LagerVerwaltung(inventory, wRepo, vRepo);
		
		InventoryItem item = lVerwaltung.createInventoryItem("wasser", 10, 100, "kaltes Wasser");
		InventoryItem item2 = lVerwaltung.createInventoryItem("Wassertopf", 10, 100, "20l");
		InventoryItem item3 = lVerwaltung.createInventoryItem("Toast", 10, 100, "500g");
		List<InventoryItem> list = lVerwaltung.sucheItems("wasser");
		
		assertTrue(list.contains(item) );
		assertTrue(list.contains(item2) );
		assertFalse(list.contains(item3) );
	}
	
	@Test
	public void bearbeiteInventoryItem(){
		LagerVerwaltung lVerwaltung = new LagerVerwaltung(inventory, wRepo, vRepo);
		
		InventoryItem item = lVerwaltung.createInventoryItem("wasser", 10, 100, "kaltes Wasser");
		lVerwaltung.bearbeiteInventoryItem(item.getId(), "saft", 5, "ohne Zusatzstoffe");
		
		assertEquals("saft", item.getProduct().getName());
		assertEquals(5, item.getProduct().getPrice().getNumber().doubleValue(), 0.001);
		assertEquals("ohne Zusatzstoffe", wRepo.findOne(item.getProduct().getId()).get().getBeschreibung());
	}
	
	@Test
	public void deleteWare(){
		LagerVerwaltung lVerwaltung = new LagerVerwaltung(inventory, wRepo, vRepo);
		
		InventoryItem item = lVerwaltung.createInventoryItem("wasser", 10, 100, "kaltes Wasser");
		
		assertTrue(inventory.exists(item.getId()));
		assertEquals(6 ,wRepo.findAllByOrderByName().size());
		lVerwaltung.deleteWare(item.getId(), item.getProduct().getId());
		assertEquals(5 ,wRepo.findAllByOrderByName().size());
		assertFalse(inventory.exists(item.getId()));
		assertFalse(wRepo.exists(item.getProduct().getId()));
	}
	
	@Test
	public void pruefeObWareInVeranstaltung(){
		LagerVerwaltung lVerwaltung = new LagerVerwaltung(inventory, wRepo, vRepo);
		Adresse a = new Adresse("a", "b" ,"c");
		Veranstaltung v = new Veranstaltung(LocalDateTime.of(2000, 01, 20, 10, 00), LocalDateTime.of(2000, 01, 20, 22, 00), a, "40Jahre", 2, EventArt.EVENTCATERING, "Geburtstagsfeier", "keins");		
		vRepo.save(v);
		InventoryItem item = lVerwaltung.createInventoryItem("wasser", 10, 100, "kaltes Wasser");
		
		Ware ware = wRepo.findOne(item.getProduct().getId()).get();
		Quantity value = Quantity.of(10);
		HashMap<Ware, Quantity> warenliste = new HashMap<Ware,Quantity>(0);
		warenliste.put(ware, value);
		
		assertFalse(lVerwaltung.pruefeObWareInVeranstaltung(item.getProduct().getId()));	
		v.setWarenliste(warenliste);
		assertTrue(lVerwaltung.pruefeObWareInVeranstaltung(item.getProduct().getId()));
	}
}
