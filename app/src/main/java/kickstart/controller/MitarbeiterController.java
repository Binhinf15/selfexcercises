package kickstart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.person.Mitarbeiter;
import kickstart.person.MitarbeiterFormular;
import kickstart.person.PersonenVerwaltung;


@Controller
public class MitarbeiterController {
	
	private final PersonenVerwaltung pVerwaltung;
	
	// Konstruktor
	@Autowired
	public MitarbeiterController(PersonenVerwaltung pVerwaltung){
		this.pVerwaltung = pVerwaltung;
	}
	
	// Methoden
	/*
	@RequestMapping("/mitarbeiterListe")
	public String mitarbeiter(Model model) {
		
		model.addAttribute("mitarbeiterListe", pVerwaltung.getMitarbeiterRepo().findAll());
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findAll());				// nur zum testen hier sollte eigentlich in den KundenController
		
		return "person/mitarbeiterListe";
	}
	
	@RequestMapping("/MitarbeiterFormular")
	public String registerMitarbeiter(Model model) {
		
		model.addAttribute("mitarbeiterDaten", new MitarbeiterFormular());
		
		return "person/MitarbeiterFormular";
	}
	
	@RequestMapping("/addMitarbeiter")
	public String newMitarbeiter(@ModelAttribute("mitarbeiterDaten") @Valid MitarbeiterFormular mitarbeiterDaten, BindingResult result) {
		
		if (result.hasErrors()) {
			return "person/MitarbeiterFormular";
		}
		
		Mitarbeiter m = pVerwaltung.createMitarbeiter(mitarbeiterDaten.getVorname(), mitarbeiterDaten.getNachname(), 
													mitarbeiterDaten.getOrt(), mitarbeiterDaten.getStrasse(), mitarbeiterDaten.getPlz(),
													mitarbeiterDaten.getUsername(), mitarbeiterDaten.getPassword(), mitarbeiterDaten.getRole(),
													mitarbeiterDaten.getEmail());
		pVerwaltung.saveMitarbeiter(m);
		
		return "redirect:/mitarbeiterListe";
	}*/
}
