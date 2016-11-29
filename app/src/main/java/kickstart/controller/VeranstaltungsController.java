package kickstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kickstart.veranstaltung.VeranstaltungsVerwaltung;

@Controller
public class VeranstaltungsController {
	
	private final VeranstaltungsVerwaltung vVerwaltung;
	
	// Konstruktor
	public VeranstaltungsController(VeranstaltungsVerwaltung vVerwaltung){
		this.vVerwaltung = vVerwaltung;
	}
	
	// Methoden
	@RequestMapping("/test")
	public String zeigeVeranstaltungen(Model model){
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findAll());
		return "/person/VeranstaltungsListe";
	}
	
	@RequestMapping("/veranstaltungen")
	public String veran(Model model) {
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findAll());
		return "veranstaltungen";
	}
	
	@RequestMapping("/veranstaltungBeginnSortiert")
	public String veranstaltungenNachBeginnSortiert(Model model){
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findByOrderByBeginnDatum());
		return "veranstaltungen";
	}
	
	@RequestMapping("/veranstaltungIdSortiert")
	public String veranstaltungenNachIdSortiert(Model model){
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findByOrderById());
		return "veranstaltungen";
	}
	
	@RequestMapping("/veranstaltungKundenIdSortiert")
	public String veranstaltungenNachKundenIdSortiert(Model model){
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findByOrderByKundenId());
		return "veranstaltungen";
	}
	
	@RequestMapping("/veranstaltungEventArtSortiert")
	public String veranstaltungenNachEventArtSortiert(Model model){
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findByOrderByEventArt());
		return "veranstaltungen";
	}
	
	@RequestMapping(path="/sucheKundenId", method=RequestMethod.POST)
	public String sucheNachVorname(Model model, @RequestParam("kundenId") long kundenId) {
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findByKundenId(kundenId));
		return "veranstaltungen";
	}	
	
	@RequestMapping(path="/zurBestellung", method=RequestMethod.POST)
	public String zurBestellung(Model model, @RequestParam("test") long kundenId) {
		model.addAttribute("kundenId", kundenId);
		return "bestellung";
	}
}
