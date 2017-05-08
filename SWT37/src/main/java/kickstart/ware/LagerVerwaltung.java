package kickstart.ware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;
import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.inventory.InventoryItem;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsRepository;

	@Component
	public class LagerVerwaltung {
		private final InventoryItemRepository inventory;
		private final WarenRepository wRepo;
		private final VeranstaltungsRepository vRepo;
		
		// Konstruktor
		@Autowired
		public LagerVerwaltung(InventoryItemRepository inventory, WarenRepository wRepo, VeranstaltungsRepository vRepo){
			this.inventory = inventory;
			this.wRepo = wRepo;
		    this.vRepo = vRepo;				
		}
		
		// Methoden
		private Ware createWare(String name, double preis, Metric metric){
			MonetaryAmount amount = FastMoney.of(preis, "EUR");
			Ware ware = new Ware(name, amount, metric);
			wRepo.save(ware);

			return ware;
		}
		
		public InventoryItem createInventoryItem(String name, double preis, double menge, String beschreibung){
			Metric metric = Metric.valueOf("UNIT");		
			Ware ware = createWare(name, preis, metric);						// Ware wird erstellt
			ware.setBeschreibung(beschreibung);
							
			Quantity quantity = Quantity.of(menge, metric);							
			InventoryItem inventoryItem = new InventoryItem(ware, quantity);	// InventoryItem wird erstellt mit einer Ware und Menge
			
			inventory.save(inventoryItem);										// InventoryItem wird in das inventory gespeichert
			return inventoryItem;
		}
		
		public List<InventoryItem> sucheItems(String gesuchterName){
			List<InventoryItem> gefundeneItems = new ArrayList<InventoryItem>();
			for(InventoryItem item : inventory.findAll()){
				if(item.getProduct().getName().toLowerCase().contains(gesuchterName.toLowerCase()) ){
					gefundeneItems.add(item);					
				}
			}
			return gefundeneItems;
		}
		
		public void bearbeiteInventoryItem(InventoryItemIdentifier id, String name, double preis, String beschreibung){
			InventoryItem item = inventory.findOne(id).get();
			Optional<Ware> ware = wRepo.findOne(item.getProduct().getId());
			MonetaryAmount price = FastMoney.of(preis, "EUR");
			ware.get().setName(name);
			ware.get().setBeschreibung(beschreibung);
			ware.get().setPrice(price);	
			wRepo.save(ware.get());
		}
		
		public InventoryItemRepository getInventory() {
			return inventory;
		}

		public WarenRepository getWarenRepo() {
			return wRepo;
		}
	
		public VeranstaltungsRepository getVeranstaltungsRepo() {
			return vRepo;
		}

		public void deleteWare(InventoryItemIdentifier itemId, ProductIdentifier wareId){
			inventory.delete(itemId);
			wRepo.delete(wareId);
		}
		
		public boolean pruefeObWareInVeranstaltung(ProductIdentifier wareId){
			boolean status = false;			
			mainloop:
			for(Veranstaltung veranstaltung : vRepo.findAll()){
				Map<Ware, Quantity> warenliste = veranstaltung.getWarenliste();
				for(Ware ware : warenliste.keySet()){				
					if(ware.getId().equals(wareId)){					
						status = true;
						break mainloop;
					}
				}
			}
			
			return status;
		}
		
	}
