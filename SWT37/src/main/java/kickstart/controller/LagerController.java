package kickstart.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.inventory.Inventory;
import org.salespointframework.inventory.InventoryItem;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.salespointframework.quantity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kickstart.ware.LagerVerwaltung;
import kickstart.ware.Ware;
import kickstart.ware.WarenFormular;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VERWALTUNG') or hasRole('ROLE_KOCH') or hasRole('ROLE_BOSS')")
public class LagerController {

	private final LagerVerwaltung lVerwaltung;
	private final Inventory<InventoryItem> inventory;
	
	@Autowired
	public LagerController(LagerVerwaltung lVerwaltung, Inventory<InventoryItem> inventory){
	    this.lVerwaltung = lVerwaltung;
	    this.inventory = inventory;    
	}
	
	@RequestMapping("/lager")
	public String lager(Model model) {   	
		model.addAttribute("InventoryItemListe", lVerwaltung.getInventory().findAllByOrderByProductName());

		return "lager";
	}
	
	@RequestMapping(value = "/lager", method = RequestMethod.POST)
	public String mengeÄnderung(@RequestParam("pid") Ware ware, @RequestParam("menge") double menge) {
		Optional<InventoryItem> item = inventory.findByProductIdentifier(ware.getId());
		if (item.isPresent()) {
			InventoryItem inventoryItem = item.get();
			inventoryItem.increaseQuantity(Quantity.of(menge));
			inventory.save(inventoryItem); }

			return "redirect:/lager";

	}
	
	@RequestMapping("/warenNameSortiert")
	public String warenListeSortiertNachName(Model model) {
		model.addAttribute("InventoryItemListe", lVerwaltung.getInventory().findAllByOrderByProductName());
		return "lager";
	}
	
	@RequestMapping("/warenPreisSortiert")
	public String warenListeSortiertNachPreis(Model model) {
		model.addAttribute("InventoryItemListe", lVerwaltung.getInventory().findAllByOrderByProductPrice());
		return "lager";
	}

	@RequestMapping("/warenMengeSortiert")
	public String warenListeSortiertNachMenge(Model model) {
		model.addAttribute("InventoryItemListe", lVerwaltung.getInventory().findAllByOrderByQuantity());
		return "lager";
	}
		       
	@RequestMapping(path="/sucheWareName", method=RequestMethod.POST)
	public String sucheNachName(Model model, @RequestParam("wareName") String name) {
		model.addAttribute("InventoryItemListe", lVerwaltung.sucheItems(name));
		return "lager";
	}
	  
	@RequestMapping("/neue-ware")
	public String ser_nk(Model model) {
		model.addAttribute("warenDaten", new WarenFormular());
		return "neue-ware";
	}
	  
	@RequestMapping(value="/addWare", method=RequestMethod.POST)
	public String addWare(Model model, @ModelAttribute("warenDaten") @Valid WarenFormular warenDaten, BindingResult result) {
	
		if (result.hasErrors()) {
			return "neue-ware";
		}

		lVerwaltung.createInventoryItem(warenDaten.getName(), warenDaten.getPrice(), warenDaten.getMenge(), warenDaten.getBeschreibung());
	
		return "redirect:/lager";
	}

    @RequestMapping("/warenDetail")
  	public String warenDetail(Model model, @RequestParam("itemId") InventoryItemIdentifier itemId){
  		
    	InventoryItem item = lVerwaltung.getInventory().findOne(itemId).get();
    	Optional<Ware> ware = lVerwaltung.getWarenRepo().findOne(item.getProduct().getId());
    	WarenFormular wf = new WarenFormular();
    	wf.setBeschreibung(ware.get().getBeschreibung());
    	wf.setName(ware.get().getName()); 	
    	wf.setPrice(ware.get().getPrice().getNumber().doubleValue());
  				
  		model.addAttribute("warenDaten", wf);
  		model.addAttribute("itemId", itemId);
  		
  		return "warenDetail";
    }
  
	@RequestMapping("/bearbeiteWare")
	public String bearbeiteWare(@RequestParam("itemId") InventoryItemIdentifier itemId, @ModelAttribute("warenDaten") @Valid WarenFormular warenDaten, BindingResult result){
			
		if (result.hasErrors()) {
			return "warenDetail";
		}
		
		lVerwaltung.bearbeiteInventoryItem(itemId, warenDaten.getName(), warenDaten.getPrice(), warenDaten.getBeschreibung());
		
		return "redirect:/lager";	
	}
	
	@RequestMapping("/löscheWare")
	public String deleteWare(@RequestParam("itemId") InventoryItemIdentifier itemId,@RequestParam("wareId") ProductIdentifier wareId, RedirectAttributes reAdd){
		
		if(lVerwaltung.pruefeObWareInVeranstaltung(wareId)){
			reAdd.addFlashAttribute("fehler", true);
			return "redirect:/lager";
		}else{
			lVerwaltung.deleteWare(itemId, wareId);
			return "redirect:/lager";
		}
	}

}
