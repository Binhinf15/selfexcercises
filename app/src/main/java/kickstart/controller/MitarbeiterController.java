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

import kickstart.person.Mitarbeiter;
import kickstart.person.MitarbeiterFormular;
import kickstart.person.PersonenVerwaltung;


/**
 * The type Mitarbeiter controller.
 */
@Controller
public class MitarbeiterController {
	
	private final PersonenVerwaltung pVerwaltung;

    /**
     * Instantiates a new Mitarbeiter controller.
     *
     * @param pVerwaltung the p verwaltung
     */
// Konstruktor
	@Autowired
	public MitarbeiterController(PersonenVerwaltung pVerwaltung){
		this.pVerwaltung = pVerwaltung;
	}


    /**
     * Mitarbeiter string.
     *
     * @param model the model
     * @return the string
     */
// Methoden
	@RequestMapping("/personal")
	public String mitarbeiter(Model model) {		
		model.addAttribute("mitarbeiterListe", pVerwaltung.getMitarbeiterRepo().findAll());		
		return "personal";
	}

    /**
     * Kunden liste sortiert nach vorname string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/personalVornameSortiert")
	public String kundenListeSortiertNachVorname(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.getMitarbeiterRepo().findAllByOrderByVorname());
		return "personal";
	}

    /**
     * Kunden liste sortiert nach nachname string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/personalNachnameSortiert")
	public String kundenListeSortiertNachNachname(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.getMitarbeiterRepo().findAllByOrderByNachname());
		return "personal";
	}

    /**
     * Kunden liste sortiert nach id string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/personalIdSortiert")
	public String kundenListeSortiertNachId(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.getMitarbeiterRepo().findAllByOrderById());
		return "personal";
	}

    /**
     * Suche nach vorname string.
     *
     * @param model   the model
     * @param vorname the vorname
     * @return the string
     */
    @RequestMapping(path="/suchePersonalVorname", method=RequestMethod.POST)
	public String sucheNachVorname(Model model, @RequestParam("mitarbeiterVorname") String vorname) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.getMitarbeiterRepo().findByVorname(vorname));
		return "personal";
	}

    /**
     * Register mitarbeiter string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/neues-personal")
	public String registerMitarbeiter(Model model) {	
		model.addAttribute("personalDaten", new MitarbeiterFormular());
		model.addAttribute("enumAccountRolleList", pVerwaltung.getEnumAccountRolleList());
		return "neues-personal";
	}

    /**
     * Add personal string.
     *
     * @param mitarbeiterDaten the mitarbeiter daten
     * @param result           the result
     * @return the string
     */
    @RequestMapping("/addPersonal")
	public String addPersonal(@ModelAttribute("mitarbeiterDaten") @Valid MitarbeiterFormular mitarbeiterDaten, BindingResult result) {
		
		if (result.hasErrors()) {
			return "neues-personal";
		}
		
		Mitarbeiter m = pVerwaltung.createMitarbeiter(mitarbeiterDaten.getVorname(), mitarbeiterDaten.getNachname(), 
													mitarbeiterDaten.getOrt(), mitarbeiterDaten.getStrasse(), mitarbeiterDaten.getPlz(),
													mitarbeiterDaten.getUsername(), mitarbeiterDaten.getPassword(), mitarbeiterDaten.getRole(),
													mitarbeiterDaten.getEmail(), mitarbeiterDaten.getTelefon());
		pVerwaltung.saveMitarbeiter(m);
		
		return "redirect:/personal";
	}
}
