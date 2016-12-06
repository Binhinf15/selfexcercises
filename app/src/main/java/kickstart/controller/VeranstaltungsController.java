package kickstart.controller;

import javax.validation.Valid;

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
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsFormular;
import kickstart.veranstaltung.VeranstaltungsVerwaltung;
import kickstart.ware.LagerVerwaltung;

/**
 * The type Veranstaltungs controller.
 */
@Controller
public class VeranstaltungsController {
	
	private final VeranstaltungsVerwaltung vVerwaltung;
	private final LagerVerwaltung lVerwaltung;
	private long Idd;

    /**
     * Instantiates a new Veranstaltungs controller.
     *
     * @param vVerwaltung the v verwaltung
     * @param lVerwaltung the l verwaltung
     */
// Konstruktor
	public VeranstaltungsController(VeranstaltungsVerwaltung vVerwaltung, LagerVerwaltung lVerwaltung){
		this.vVerwaltung = vVerwaltung;
		this.lVerwaltung = lVerwaltung;
	}

    /**
     * Veran string.
     *
     * @param model the model
     * @return the string
     */
// Methoden
	@RequestMapping("/veranstaltungen")
	public String veran(Model model) {
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findAll());
		return "veranstaltungen";
	}

    /**
     * Veranstaltungen nach beginn sortiert string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/veranstaltungBeginnSortiert")
	public String veranstaltungenNachBeginnSortiert(Model model){
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findAllByOrderByBeginnDatum());
		return "veranstaltungen";
	}

    /**
     * Veranstaltungen nach id sortiert string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/veranstaltungIdSortiert")
	public String veranstaltungenNachIdSortiert(Model model){
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findAllByOrderById());
		return "veranstaltungen";
	}

    /**
     * Veranstaltungen nach kunden id sortiert string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/veranstaltungKundenIdSortiert")
	public String veranstaltungenNachKundenIdSortiert(Model model){
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findAllByOrderByKundenId());
		return "veranstaltungen";
	}

    /**
     * Veranstaltungen nach event art sortiert string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/veranstaltungEventArtSortiert")
	public String veranstaltungenNachEventArtSortiert(Model model){
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findAllByOrderByEventArt());
		return "veranstaltungen";
	}

    /**
     * Suche nach vorname string.
     *
     * @param model    the model
     * @param kundenId the kunden id
     * @return the string
     */
    @RequestMapping(path="/sucheKundenId", method=RequestMethod.POST)
	public String sucheNachVorname(Model model, @RequestParam("kundenId") long kundenId) {
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findByKundenId(kundenId));
		return "veranstaltungen";
	}

    /**
     * Zeige waren string.
     *
     * @param model the model
     * @param Id    the id
     * @return the string
     */
    @RequestMapping(path="/zeigeWaren", method=RequestMethod.POST)
	public String zeigeWaren(Model model, @RequestParam("veranId") long Id) {
		model.addAttribute("warenListe", vVerwaltung.getVeranstaltungsRepo().findById(Id).get(0).getWarenListe());
		return "person/warenListe";
	}

    /**
     * Zur bestellung string.
     *
     * @param model    the model
     * @param kundenId the kunden id
     * @return the string
     */
    @RequestMapping(path="/bestellung", method=RequestMethod.POST)
	public String zurBestellung(Model model, @RequestParam("kundenId") long kundenId) {
		VeranstaltungsFormular vf = new VeranstaltungsFormular();
		vf.setKundenId(kundenId);
		model.addAttribute("veranstaltungsDaten", vf);
		model.addAttribute("kundenListe", vVerwaltung.getKundenRepo().findAll());
		model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAll());
		model.addAttribute("enumEventArtList", vVerwaltung.getEnumEventArtList());
		return "bestellung";
	}

    /**
     * Zur bestellung 2 string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/bestellung2")
	public String zurBestellung2(Model model) {
		model.addAttribute("veranstaltungsDaten", new VeranstaltungsFormular());
		model.addAttribute("kundenListe", vVerwaltung.getKundenRepo().findAll());
		model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAll());
		model.addAttribute("mitarbeiterListe", vVerwaltung.getMitarbeiterRepo().findAll());
		model.addAttribute("enumEventArtList", vVerwaltung.getEnumEventArtList());
		return "bestellung";
	}

    /**
     * Add kunde string.
     *
     * @param model      the model
     * @param warenId    the waren id
     * @param warenMenge the waren menge
     * @param verDaten   the ver daten
     * @param result     the result
     * @return the string
     */
    @RequestMapping("/addVeranstaltung")
	public String addKunde(Model model, @RequestParam("warenId") long warenId,@RequestParam("warenMenge") long warenMenge, @ModelAttribute("veranstaltungsDaten") VeranstaltungsFormular verDaten, BindingResult result) {
		
		if (result.hasErrors()) {
			return "bestellung";
		}
		
		Veranstaltung v = vVerwaltung.createVeranstaltung(verDaten.getBeginnTag(), verDaten.getBeginnMonat(), verDaten.getBeginnJahr(), verDaten.getBeginnStunde(), verDaten.getBeginnMinute()
														, verDaten.getSchlussTag(), verDaten.getSchlussMonat(), verDaten.getSchlussJahr(), verDaten.getSchlussStunde(), verDaten.getSchlussMinute()
														, verDaten.getStrasse(), verDaten.getOrt(), verDaten.getPlz(), verDaten.getBemerkung(), verDaten.getKundenId(), verDaten.getEventArt());
		lVerwaltung.getWarenRepo().findById(warenId).get(0).setMenge(warenMenge);
		v.getWarenListe().add(lVerwaltung.getWarenRepo().findById(warenId).get(0));
		vVerwaltung.saveVeranstaltung(v);
		System.out.println(verDaten.getMitarbeiterIdListe());
		return "redirect:/veranstaltungen";
	}
}
