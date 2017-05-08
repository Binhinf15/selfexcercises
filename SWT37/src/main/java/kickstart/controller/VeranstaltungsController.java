package kickstart.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.salespointframework.order.Cart;
import org.salespointframework.order.CartItem;
import org.salespointframework.quantity.Quantity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kickstart.buchhaltung.BuchhaltungsVerwaltung;
import kickstart.buchhaltung.KundenRechnung;
import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.KalenderDaten;
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsFormular;
import kickstart.veranstaltung.VeranstaltungsVerwaltung;
import kickstart.ware.LagerVerwaltung;
import kickstart.ware.Ware;

/**
 * The type Veranstaltungs controller.
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_BOSS') or hasRole('ROLE_VERWALTUNG')")
@SessionAttributes("cart")
public class VeranstaltungsController {
	
	private final VeranstaltungsVerwaltung vVerwaltung;
	private final LagerVerwaltung lVerwaltung;
	private final PersonenVerwaltung pVerwaltung;
	private final BuchhaltungsVerwaltung bVerwaltung;

    /**
     * Instantiates a new Veranstaltungs controller.
     *
     * @param vVerwaltung the v verwaltung
     * @param lVerwaltung the l verwaltung
     */
// Konstruktor
	public VeranstaltungsController(VeranstaltungsVerwaltung vVerwaltung, LagerVerwaltung lVerwaltung, PersonenVerwaltung pVerwaltung, BuchhaltungsVerwaltung bVerwaltung){
		this.vVerwaltung = vVerwaltung;
		this.lVerwaltung = lVerwaltung;
		this.pVerwaltung = pVerwaltung;
		this.bVerwaltung = bVerwaltung;
	}

    /**
     * Veran string.
     *
     * @param model the model
     * @return the string
     */
// Methoden
	@RequestMapping("/veranstaltungen")
	public String veranstaltungen(Model model, SessionStatus status) {
		List<KalenderDaten> kalenderDatenListe = new ArrayList<KalenderDaten>();
        List<Veranstaltung> veranstaltungsListe = (List<Veranstaltung>) vVerwaltung.getVeranstaltungsRepo().findAll();
        for(Veranstaltung veranstaltung : veranstaltungsListe){
        	KalenderDaten kalenderDaten = new KalenderDaten(veranstaltung.getId(), veranstaltung.getTitel()
        													, veranstaltung.getBeginnDatum(), veranstaltung.getSchlussDatum());
        	kalenderDatenListe.add(kalenderDaten);
        }
        
        model.addAttribute("kalenderDatenListe", kalenderDatenListe);		
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findAll());
		
		status.setComplete();
		
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
	public String sucheNachVorname(Model model, @RequestParam(value="kundenId", required=false, defaultValue="0") long kundenId) {
		model.addAttribute("veranstaltungsListe", vVerwaltung.getVeranstaltungsRepo().findByKundenId(kundenId));
		return "veranstaltungen";
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
		model.addAttribute("enumEventArtList", vVerwaltung.getEnumEventArtList());
		
		return "bestellung";
	}
    
    @RequestMapping("/neue-veranstaltung2")
    public String neueVeranstaltung2(Model model,  @ModelAttribute("veranstaltungsDaten") VeranstaltungsFormular verDaten, @ModelAttribute Cart cart){
    	
    	LocalDate beginnDate = LocalDate.parse(verDaten.getBeginnDatum());
        LocalTime beginnTime = LocalTime.parse(verDaten.getBeginnZeit());
        LocalDate schlussDate = LocalDate.parse(verDaten.getSchlussDatum());
        LocalTime schlussTime = LocalTime.parse(verDaten.getSchlussZeit());	
		LocalDateTime beginn = beginnDate.atTime(beginnTime); 
		LocalDateTime schluss = schlussDate.atTime(schlussTime);
				
		model.addAttribute("veranstaltungsDaten", verDaten);
		model.addAttribute("warenRepo", lVerwaltung.getWarenRepo().findAllByOrderByName());
		model.addAttribute("freieMitarbeiterListe", pVerwaltung.getFreieMitarbeiter(beginn, schluss));	
		
    	return "neue-veranstaltung2";
    }
    
    @RequestMapping(value="/addVeranstaltung",method = RequestMethod.POST, params="action=hinzufügen")
	public String wareHinzufügen(Model model,RedirectAttributes reAdd ,@ModelAttribute("veranstaltungsDaten") VeranstaltungsFormular verDaten
								, @RequestParam(value="menge") long menge, @ModelAttribute Cart cart) {
    	
    	cart.addOrUpdateItem(verDaten.getWare(), Quantity.of(menge));   	
    	cart.forEach((CartItem c)-> System.out.println("point3 "+c));
    	
    	reAdd.addFlashAttribute("veranstaltungsDaten", verDaten);
    	reAdd.addFlashAttribute("autofokus", true);
    	
		return "redirect:/neue-veranstaltung2";
    }
    
    @RequestMapping(value="/addVeranstaltung",method = RequestMethod.POST, params="action=Ware löschen")
   	public String wareLöschen(Model model,RedirectAttributes reAdd ,@ModelAttribute("veranstaltungsDaten") VeranstaltungsFormular verDaten
   							, @RequestParam(value="zuLöschen", required=false, defaultValue="0") String cartItemId , @ModelAttribute Cart cart) {
    	   	
    	cart.removeItem(cartItemId);	
    	verDaten.setWare(new Ware());
    	
       	reAdd.addFlashAttribute("veranstaltungsDaten", verDaten);
       	reAdd.addFlashAttribute("autofokus", true);
       	
   		return "redirect:/neue-veranstaltung2";
       }


    /**
     * Add kunde string.
     *
     * @param model      the model
     * @param verDaten   the ver daten
     * @param result     the result
     * @return the string
     */
    @RequestMapping(value="/addVeranstaltung", params="action=Fertig")
	public String addVeranstaltung(Model model,  @ModelAttribute("veranstaltungsDaten") VeranstaltungsFormular verDaten, BindingResult result,@ModelAttribute Cart cart, SessionStatus status) {
		
		if (result.hasErrors()) {
			return "bestellung";
		}
		Veranstaltung v = vVerwaltung.createVeranstaltung(verDaten.getBeginnDatum(), verDaten.getBeginnZeit(), verDaten.getSchlussDatum(), verDaten.getSchlussZeit() 
														, verDaten.getStrasse(), verDaten.getOrt(), verDaten.getPlz()
														, verDaten.getBemerkung(), verDaten.getKundenId(), verDaten.getEventArt(), verDaten.getTitel(), verDaten.getZubehoer());

		// ausgewählte Waren werden übergeben
		HashMap<Ware, Quantity> warenliste = new HashMap<Ware, Quantity>();
		for(CartItem cartItem : cart){
			warenliste.put((Ware) cartItem.getProduct(), cartItem.getQuantity());
		}
		v.setWarenliste(warenliste);	
		
		// MitarbeiterIdListe wird übergeben
		v.setMitarbeiterIdListe(verDaten.getMitarbeiterIdListe());	
		
		// Verwaltung wird gespeichert
		vVerwaltung.saveVeranstaltung(v);		
		status.setComplete();		// Cart Session wird beendet
		
		// Kundenrechnung wird erstellt
		bVerwaltung.createKundenRechnung(verDaten.getKundenId(),v.getId(), warenliste);
	
		return "redirect:/veranstaltungen";

	}
    
    @ModelAttribute("cart")
	public Cart initializeCart() {
		return new Cart();
	}
    
    @RequestMapping("/veranstaltungsDetail")
    public String veranstaltungsDetail(Model model, @RequestParam("veranstaltungsId") long veranstaltungsId, @ModelAttribute Cart cart){
    	
    	Veranstaltung vDaten = vVerwaltung.getVeranstaltungsRepo().findOne(veranstaltungsId).get();
    	VeranstaltungsFormular vf = new VeranstaltungsFormular();
    	
    	vf.setBeginnDatum(vDaten.getBeginnDatum().toLocalDate().toString());
    	vf.setBeginnZeit(vDaten.getBeginnDatum().toLocalTime().toString());
    	vf.setSchlussDatum(vDaten.getSchlussDatum().toLocalDate().toString());
    	vf.setSchlussZeit(vDaten.getSchlussDatum().toLocalTime().toString());
    	vf.setBemerkung(vDaten.getBemerkung());
    	vf.setEventArt(vDaten.getEventArt().toString());
    	vf.setKundenId(vDaten.getKundenId());
    	vf.setMitarbeiterIdListe(vDaten.getMitarbeiterIdListe());
    	vf.setStrasse(vDaten.getAdresse().getStrasse());
    	vf.setOrt(vDaten.getAdresse().getOrt());
    	vf.setPlz(vDaten.getAdresse().getPlz());
    	vf.setPreis(vDaten.getPreis());
    	vf.setTitel(vDaten.getTitel());
    	vf.setZubehoer(vDaten.getZubehoer());
    	vf.setBeginnDatum(vDaten.getBeginnDatum().toLocalDate().toString());
    	vf.setBeginnZeit(vDaten.getBeginnDatum().toLocalTime().toString());
    	vf.setSchlussDatum(vDaten.getSchlussDatum().toLocalDate().toString());
    	vf.setSchlussZeit(vDaten.getSchlussDatum().toLocalTime().toString());
    	
    	//Cart wird gefüllt
    	Map<Ware, Quantity> set= vDaten.getWarenliste();
    	for(Ware ware : set.keySet()){
    		cart.addOrUpdateItem(ware, set.get(ware));   	
        	cart.forEach((CartItem c)-> System.out.println("point2 cart "+c));
    	}
    	
    	model.addAttribute("veranstaltungsDaten", vf);
    	model.addAttribute("veranstaltungsId", veranstaltungsId);
    	model.addAttribute("kundenListe", vVerwaltung.getKundenRepo().findAll());
		model.addAttribute("warenRepo", lVerwaltung.getWarenRepo().findAllByOrderByName());
		model.addAttribute("enumEventArtList", vVerwaltung.getEnumEventArtList());			
		model.addAttribute("freieMitarbeiterListe", pVerwaltung.getFreieMitarbeiter(vDaten.getBeginnDatum(), vDaten.getSchlussDatum()));
		model.addAttribute("zugeordneteMitarbeiter", vVerwaltung.getZugewieseneMitarbeiter(vDaten.getMitarbeiterIdListe()));
	
		return "veranstaltungsDetail"; 	
    }
    
    @RequestMapping(path="/bearbeiteVeranstaltung", method = RequestMethod.POST, params="action=hinzufügen")
    public String bearbeiteVeranstaltungWareHinzufuegen(Model model, @RequestParam("veranstaltungsId") long veranstaltungsId, @ModelAttribute("veranstaltungsDaten") @Valid VeranstaltungsFormular vDaten, BindingResult result
    												,@RequestParam(value="menge") long menge, @ModelAttribute Cart cart){
    	
    	cart.addOrUpdateItem(vDaten.getWare(), Quantity.of(menge));   	
    	cart.forEach((CartItem c)-> System.out.println("point3 "+c));
    	
    	model.addAttribute("veranstaltungsDaten", vDaten);
    	model.addAttribute("veranstaltungsId", veranstaltungsId);
    	model.addAttribute("kundenListe", vVerwaltung.getKundenRepo().findAll());
		model.addAttribute("warenRepo", lVerwaltung.getWarenRepo().findAllByOrderByName());
		model.addAttribute("enumEventArtList", vVerwaltung.getEnumEventArtList());
		LocalDate beginnDate = LocalDate.parse(vDaten.getBeginnDatum());
        LocalTime beginnTime = LocalTime.parse(vDaten.getBeginnZeit());
        LocalDate schlussDate = LocalDate.parse(vDaten.getSchlussDatum());
        LocalTime schlussTime = LocalTime.parse(vDaten.getSchlussZeit());	
		LocalDateTime beginn = beginnDate.atTime(beginnTime); 
		LocalDateTime schluss = schlussDate.atTime(schlussTime);
		model.addAttribute("freieMitarbeiterListe", pVerwaltung.getFreieMitarbeiter(beginn, schluss));
		model.addAttribute("zugeordneteMitarbeiter", vVerwaltung.getZugewieseneMitarbeiter(vDaten.getMitarbeiterIdListe()));
		model.addAttribute("autofokus", true);
		
    	return "veranstaltungsDetail";   	
    }
    
    @RequestMapping(path="/bearbeiteVeranstaltung", method = RequestMethod.POST, params="action=Ware löschen")
    public String bearbeiteVeranstaltungWareLöschen(Model model,  @RequestParam("veranstaltungsId") long veranstaltungsId, @ModelAttribute("veranstaltungsDaten") @Valid VeranstaltungsFormular vDaten, BindingResult result
    												, @RequestParam(value="menge") long menge, @RequestParam(value="zuLöschen", required=false, defaultValue="0") String cartItemId, @ModelAttribute Cart cart){
    	
    	cart.removeItem(cartItemId);	
    	vDaten.setWare(new Ware());
    	
    	model.addAttribute("veranstaltungsDaten", vDaten);
    	model.addAttribute("veranstaltungsId", veranstaltungsId);
    	model.addAttribute("kundenListe", vVerwaltung.getKundenRepo().findAll());
		model.addAttribute("warenRepo", lVerwaltung.getWarenRepo().findAllByOrderByName());
		model.addAttribute("enumEventArtList", vVerwaltung.getEnumEventArtList());
		LocalDate beginnDate = LocalDate.parse(vDaten.getBeginnDatum());
        LocalTime beginnTime = LocalTime.parse(vDaten.getBeginnZeit());
        LocalDate schlussDate = LocalDate.parse(vDaten.getSchlussDatum());
        LocalTime schlussTime = LocalTime.parse(vDaten.getSchlussZeit());	
		LocalDateTime beginn = beginnDate.atTime(beginnTime); 
		LocalDateTime schluss = schlussDate.atTime(schlussTime);
		model.addAttribute("freieMitarbeiterListe", pVerwaltung.getFreieMitarbeiter(beginn, schluss));
		model.addAttribute("zugeordneteMitarbeiter", vVerwaltung.getZugewieseneMitarbeiter(vDaten.getMitarbeiterIdListe()));
		model.addAttribute("autofokus", true);
		
    	return "veranstaltungsDetail";   	
    }
    
    @RequestMapping(path="/bearbeiteVeranstaltung", method = RequestMethod.POST, params="action=Änderung speichern")
    public String bearbeiteVeranstaltungSpeichern(@RequestParam("veranstaltungsId") long veranstaltungsId, @ModelAttribute("veranstaltungsDaten") @Valid VeranstaltungsFormular vDaten
    											,@ModelAttribute Cart cart, SessionStatus status, BindingResult result){
		 
		if (result.hasErrors()) {
			return "veranstaltungsDetail";
		}
    	
		Veranstaltung v = vVerwaltung.getVeranstaltungsRepo().findOne(veranstaltungsId).get();
    	vVerwaltung.bearbeiteVeranstaltung(veranstaltungsId, vDaten.getBeginnDatum(), vDaten.getBeginnZeit(), vDaten.getSchlussDatum(), vDaten.getSchlussZeit()
    									, vDaten.getStrasse(), vDaten.getOrt(), vDaten.getPlz()
    									, vDaten.getBemerkung(), vDaten.getKundenId(), vDaten.getEventArt(), vDaten.getTitel(), vDaten.getZubehoer());
    	// ausgewählte Waren werden übergeben
		HashMap<Ware, Quantity> warenliste = new HashMap<Ware, Quantity>();
		for(CartItem cartItem : cart){
			warenliste.put((Ware) cartItem.getProduct(), cartItem.getQuantity());
		}
		v.setWarenliste(warenliste);
		
		// MitarbeiterIdListe wird übergeben
		v.setMitarbeiterIdListe(vDaten.getMitarbeiterIdListe());	
		
		// Verwaltung wird gespeichert
		vVerwaltung.saveVeranstaltung(v);
		
		// Cart Session wird beendet
		status.setComplete();	
		
		// zugehörige Kundenrechnung bearbeiten
		if(bVerwaltung.getKundenRechnungRepo().findByVeranstaltungsId(v.getId()) == null){			// falls keine Kundenrechnung vorhanden ist wird eine erstellt
			bVerwaltung.createKundenRechnung(vDaten.getKundenId(), veranstaltungsId, warenliste);
		}else{																						// ansonsten bearbeitet
			KundenRechnung kundenRechnung = bVerwaltung.getKundenRechnungRepo().findByVeranstaltungsId(v.getId());			
			kundenRechnung.aktualisierePreis(warenliste);
			bVerwaltung.saveKundenRechnung(kundenRechnung);
		}
    	
    	return "redirect:/veranstaltungen";   	
    }
    
    @RequestMapping("/deleteVeranstaltung")
    public String deleteVeranstaltung(@RequestParam("veranstaltungsId") long veranstaltungsId){
    	vVerwaltung.deleteVeranstaltung(veranstaltungsId);
		return "redirect:/veranstaltungen";   	
    }
}
