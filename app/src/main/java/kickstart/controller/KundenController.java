package kickstart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kickstart.person.Kunde;
import kickstart.person.KundenFormular;
import kickstart.person.PersonenVerwaltung;

@Controller
public class KundenController {
	
	private final PersonenVerwaltung pVerwaltung;
	
	// Konstruktor
	@Autowired
	public KundenController(PersonenVerwaltung pVerwaltung){
		this.pVerwaltung = pVerwaltung;
	}
	
	// Methoden
	/*
	@RequestMapping("/kundenListe")
	public String kunden(Model model) {
		
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findAll());
		
		return "person/kundenListe";
	}
		
	@RequestMapping("/kundenFormular")
	public String registerKunde(Model model) {
		
		model.addAttribute("kundenDaten", new KundenFormular());
		
		return "person/KundenFormular";
	}
	*/
	
	@RequestMapping("/kunden")
	public String kundenListeAnzeigen(Model model) {
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findAll());
		return "kunden";
	}
	
	@RequestMapping("/kundenVornameSortiert")
	public String kundenListeSortiertNachVorname(Model model) {
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findAllByOrderByVorname());
		return "kunden";
	}
	
	@RequestMapping("/kundenIdSortiert")
	public String kundenListeSortiertNachId(Model model) {
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findAllByOrderById());
		return "kunden";
	}
	
	@RequestMapping(path="/sucheVornameKunde", method=RequestMethod.POST)
	public String sucheNachVorname(Model model, @RequestParam("vorname") String vorname) {
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findByVorname(vorname));
		return "kunden";
	}
	
	@RequestMapping("/neuer-kunde")
	public String ser_nk(Model model) {
		model.addAttribute("kundenDaten", new KundenFormular());
		return "neuer-kunde";
	}
	
	@RequestMapping("/addKunde")
	public String addKunde(Model model, @ModelAttribute("kundenDaten") @Valid KundenFormular kundenDaten, BindingResult result) {
		
		if (result.hasErrors()) {
			return "person/MitarbeiterFormular";
		}
		
		Kunde k = pVerwaltung.createKunde(kundenDaten.getVorname(), kundenDaten.getNachname(), 
										kundenDaten.getOrt(), kundenDaten.getStrasse(), kundenDaten.getPlz(),
										kundenDaten.getEmail());
		pVerwaltung.saveKunde(k);
		model.addAttribute("kundenId", k.getId());
		return "bestellung";
	}
}
