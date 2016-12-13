package kickstart.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsFormular;
import kickstart.veranstaltung.VeranstaltungsVerwaltung;
import kickstart.ware.LagerVerwaltung;

/**
 * The type Veranstaltungs controller.
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VERWALTUNG')")
public class VeranstaltungsController {
	
	private final VeranstaltungsVerwaltung vVerwaltung;
	private final LagerVerwaltung lVerwaltung;
	private final PersonenVerwaltung pVerwaltung;

    /**
     * Instantiates a new Veranstaltungs controller.
     *
     * @param vVerwaltung the v verwaltung
     * @param lVerwaltung the l verwaltung
     */
// Konstruktor
	public VeranstaltungsController(VeranstaltungsVerwaltung vVerwaltung, LagerVerwaltung lVerwaltung, PersonenVerwaltung pVerwaltung){
		this.vVerwaltung = vVerwaltung;
		this.lVerwaltung = lVerwaltung;
		this.pVerwaltung = pVerwaltung;
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
    @RequestMapping(path="/bestellung", method={RequestMethod.POST, RequestMethod.GET})
	public String zurBestellung(Model model, @RequestParam(value="kundenId", required=false, defaultValue="0") long kundenId) {
    	
    	if(kundenId != 0){
    		VeranstaltungsFormular vf = new VeranstaltungsFormular();
    		vf.setKundenId(kundenId);
    		model.addAttribute("veranstaltungsDaten", vf);
    	}else{
    		model.addAttribute("veranstaltungsDaten", new VeranstaltungsFormular());
    	}
    	
		model.addAttribute("kundenListe", vVerwaltung.getKundenRepo().findAll());
		model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAll());
		model.addAttribute("enumEventArtList", vVerwaltung.getEnumEventArtList());
		
		return "bestellung";
	}
    
    @RequestMapping("/neue-veranstaltung2")
    public String neueVeranstaltung2(Model model,RedirectAttributes redirectAttributes,  @ModelAttribute("veranstaltungsDaten") VeranstaltungsFormular verDaten){
    	
    	LocalDate beginnDate = LocalDate.parse(verDaten.getBeginnDatum());
        LocalTime beginnTime = LocalTime.parse(verDaten.getBeginnZeit());
        LocalDate schlussDate = LocalDate.parse(verDaten.getSchlussDatum());
        LocalTime schlussTime = LocalTime.parse(verDaten.getSchlussZeit());	
		LocalDateTime beginn = beginnDate.atTime(beginnTime); 
		LocalDateTime schluss = schlussDate.atTime(schlussTime);
				
//		model.addAttribute("veranstaltungsDaten", verDaten);
		redirectAttributes.addFlashAttribute("veranstaltungsDaten", verDaten);
		model.addAttribute("freieMitarbeiterListe", pVerwaltung.getFreieMitarbeiter(beginn, schluss));	
		
		
    	return "neue-veranstaltung2";
    }

    /**
     * Zur bestellung 2 string.
     *
     * @param model the model
     * @return the string
     *//*
    @RequestMapping("/bestellung2")
	public String zurBestellung2(Model model) {
		model.addAttribute("veranstaltungsDaten", new VeranstaltungsFormular());
		model.addAttribute("kundenListe", vVerwaltung.getKundenRepo().findAll());
		model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAll());
		model.addAttribute("mitarbeiterListe", vVerwaltung.getMitarbeiterRepo().findAll());
		model.addAttribute("enumEventArtList", vVerwaltung.getEnumEventArtList());
		return "bestellung";
	}*/

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
	public String addVeranstaltung(Model model,  @ModelAttribute("veranstaltungsDaten") VeranstaltungsFormular verDaten, BindingResult result) {
		
		if (result.hasErrors()) {
			return "bestellung";
		}
		System.out.println("point 1");	
		System.out.println(verDaten.getBemerkung());
		System.out.println(verDaten.getBeginnDatum());
		System.out.println(verDaten.toString());	
		Veranstaltung v = vVerwaltung.createVeranstaltung(verDaten.getBeginnDatum(), verDaten.getBeginnZeit(), verDaten.getSchlussDatum(), verDaten.getSchlussZeit() 
														, verDaten.getStrasse(), verDaten.getOrt(), verDaten.getPlz()
														, verDaten.getBemerkung(), verDaten.getKundenId(), verDaten.getEventArt());
		/*
		// ausgewählte Waren werden übergeben
		lVerwaltung.getWarenRepo().findById(warenId).get(0).setMenge(warenMenge);
		v.getWarenListe().add(lVerwaltung.getWarenRepo().findById(warenId).get(0));
		*/
		// MitarbeiterIdListe wird übergeben
		System.out.println("point 2");
		
		v.setMitarbeiterIdListe(verDaten.getMitarbeiterIdListe());
		System.out.println("point 3");
		// Verwaltung wird gespeichert
		vVerwaltung.saveVeranstaltung(v);
		
		System.out.println(v.toString());
		return "redirect:/veranstaltungen";

	}
   
}
