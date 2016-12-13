package kickstart.controller;

import javax.validation.Valid;

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

import kickstart.person.Kunde;
import kickstart.person.KundenFormular;
import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.VeranstaltungsFormular;
import kickstart.veranstaltung.VeranstaltungsVerwaltung;
import kickstart.ware.LagerVerwaltung;

/**
 * The type Kunden controller.
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VERWALTUNG')")
public class KundenController {
	
	private final PersonenVerwaltung pVerwaltung;
	private final LagerVerwaltung lVerwaltung;
	private final VeranstaltungsVerwaltung vVerwaltung;

    /**
     * Instantiates a new Kunden controller.
     *
     * @param pVerwaltung the p verwaltung
     */
// Konstruktor
	@Autowired
	public KundenController(PersonenVerwaltung pVerwaltung, LagerVerwaltung lVerwaltung, VeranstaltungsVerwaltung vVerwaltung){
		this.pVerwaltung = pVerwaltung;
		this.lVerwaltung = lVerwaltung;
		this.vVerwaltung = vVerwaltung;
	}
	
// Methoden
    /**
     * Kunden liste anzeigen string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/kunden")
	public String kundenListeAnzeigen(Model model) {
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findAll());
		return "kunden";
	}

    /**
     * Kunden liste sortiert nach vorname string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/kundenVornameSortiert")
	public String kundenListeSortiertNachVorname(Model model) {
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findAllByOrderByVorname());
		return "kunden";
	}

    /**
     * Kunden liste sortiert nach nachname string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/kundenNachnameSortiert")
	public String kundenListeSortiertNachNachname(Model model) {
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findAllByOrderByNachname());
		return "kunden";
	}

    /**
     * Kunden liste sortiert nach id string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/kundenIdSortiert")
	public String kundenListeSortiertNachId(Model model) {
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findAllByOrderById());
		return "kunden";
	}

    /**
     * Suche nach vorname string.
     *
     * @param model   the model
     * @param vorname the vorname
     * @return the string
     */
    @RequestMapping(path="/sucheKundeVorname", method=RequestMethod.POST)
	public String sucheNachVorname(Model model, @RequestParam("vorname") String vorname) {
		model.addAttribute("kundenListe", pVerwaltung.getKundenRepo().findByVorname(vorname));
		return "kunden";
	}

    /**
     * Ser nk string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/neuer-kunde")
	public String neuerKunde(Model model) {
		model.addAttribute("kundenDaten", new KundenFormular());
		return "neuer-kunde";
	}

    /**
     * Add kunde string.
     *
     * @param model       the model
     * @param kundenDaten the kunden daten
     * @param result      the result
     * @return the string
     */
    @RequestMapping(value="/addKunde", method=RequestMethod.POST)
	public String addKunde(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("kundenDaten") @Valid KundenFormular kundenDaten, BindingResult result) {
		
		if (result.hasErrors()) {
			return "neuer-kunde";
		}
		
		Kunde k = pVerwaltung.createKunde(kundenDaten.getVorname(), kundenDaten.getNachname(), 
										kundenDaten.getOrt(), kundenDaten.getStrasse(), kundenDaten.getPlz(),
										kundenDaten.getEmail(),kundenDaten.getTelefon());
		pVerwaltung.saveKunde(k);	
		redirectAttributes.addAttribute("kundenId", k.getId());

		return "redirect:/bestellung";
	}
	
	@RequestMapping("/kundenDetail")
	public String kundenDetail(Model model, @RequestParam("kundenId") long kundenId){
		
		Kunde kunde = pVerwaltung.getKundenRepo().findOne(kundenId).get();
		KundenFormular kf = new KundenFormular();
		
		kf.setVorname(kunde.getVorname());
		kf.setNachname(kunde.getNachname());
		kf.setStrasse(kunde.getAdresse().getStrasse());
		kf.setOrt(kunde.getAdresse().getOrt());
		kf.setPlz(kunde.getAdresse().getPlz());
		kf.setTelefon(kunde.getTelefon());
		kf.setEmail(kunde.getEmail());
		
		model.addAttribute("kundenDaten", kf);
		model.addAttribute("kundenId", kundenId);
		
		return "kundenDetail";
	}
	
	@RequestMapping("bearbeiteKunde")
	public String bearbeiteKunde(@RequestParam("kundenId") long kundenId, @ModelAttribute("kundenDaten") @Valid KundenFormular kundenDaten, BindingResult result){
		
		if (result.hasErrors()) {
			return "neuer-kunde";
		}
		
		Kunde kunde = pVerwaltung.bearbeiteKunde(kundenId, kundenDaten.getVorname(), kundenDaten.getNachname()
									, kundenDaten.getStrasse(), kundenDaten.getOrt(), kundenDaten.getPlz()
									, kundenDaten.getEmail(), kundenDaten.getEmail());
		pVerwaltung.saveKunde(kunde);
		
		return "redirect:/kunden";	
	}
}
