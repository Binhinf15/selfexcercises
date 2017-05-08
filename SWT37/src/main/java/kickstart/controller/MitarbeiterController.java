package kickstart.controller;

import javax.validation.Valid;

import org.salespointframework.useraccount.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_BOSS')")
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

// Methoden
	
	@RequestMapping("/personal")
	public String mitarbeiterAktiv(Model model) {		
		model.addAttribute("mitarbeiterListe", pVerwaltung.findAllAktiv());		
		return "personal";
	}
	
	@RequestMapping("/personal-inaktiv")
	public String mitarbeiterInaktiv(Model model) {		
		model.addAttribute("mitarbeiterListe", pVerwaltung.findAllInaktiv());		
		return "personal-inaktiv";
	}

    @RequestMapping("/personalAktivVornameSortiert")
	public String mitarbeiterAktivListeSortiertNachVorname(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.findAktivMitarbeiterSortNachVorname());
		return "personal";
	}
    @RequestMapping("/personalInaktivVornameSortiert")
	public String mitarbeiterInaktivListeSortiertNachVorname(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.findInaktivMitarbeiterSortNachVorname());
		return "personal-inaktiv";
	}

    @RequestMapping("/personalAktivNachnameSortiert")
	public String mitarbeiterAktivListeSortiertNachNachname(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.findAktivMitarbeiterSortNachName());
		return "personal";
	}
    @RequestMapping("/personalInaktivNachnameSortiert")
	public String mitarbeiterInaktivListeSortiertNachNachname(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.findInaktivMitarbeiterSortNachName());
		return "personal-inaktiv";
	}

    @RequestMapping("/personalAktivIdSortiert")
	public String mitarbeiterAktivListeSortiertNachId(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.findAktivMitarbeiterSortNachId());
		return "personal";
	}
    @RequestMapping("/personalInaktivIdSortiert")
	public String mitarbeiterInaktivListeSortiertNachId(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.findInaktivMitarbeiterSortNachId());
		return "personal-inaktiv";
	}
    
    
    @RequestMapping("/personalAktivRolleSortiert")
	public String mitarbeiterAktivListeSortiertNachRole(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.findAktivMitarbeiterSortNachRolle());
		return "personal";
	}
    @RequestMapping("/personalInaktivRolleSortiert")
	public String mitarbeiterInaktivListeSortiertNachRole(Model model) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.findInaktivMitarbeiterSortNachRolle());
		return "personal-inaktiv";
	}
	
    
    /**
     * Suche nach vorname string.
     *
     * @param model   the model
     * @return the string
     */
    @RequestMapping(path="/suchePersonalAktivName", method=RequestMethod.POST)
	public String sucheNachMitarbeiterAktivName(Model model, @RequestParam("mitarbeiterName") String gesuchterName) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.sucheMitarbeiterAktiv(gesuchterName));
		return "personal";
	}
    @RequestMapping(path="/suchePersonalInaktivName", method=RequestMethod.POST)
	public String sucheNachMitarbeiterInaktivName(Model model, @RequestParam("mitarbeiterName") String gesuchterName) {
		model.addAttribute("mitarbeiterListe", pVerwaltung.sucheMitarbeiterInaktiv(gesuchterName));
		return "personal-inaktiv";
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
	public String addMitarbeiter(@ModelAttribute("mitarbeiterDaten") @Valid MitarbeiterFormular mitarbeiterDaten, BindingResult result) {
		
		if (result.hasErrors()) {
			return "neues-personal";
		}
		
		Mitarbeiter m = pVerwaltung.createMitarbeiter(mitarbeiterDaten.getVorname(), mitarbeiterDaten.getNachname(), 
													mitarbeiterDaten.getOrt(), mitarbeiterDaten.getStrasse(), mitarbeiterDaten.getPlz(),
													mitarbeiterDaten.getUsername(), mitarbeiterDaten.getPassword(), mitarbeiterDaten.getRole(),
													mitarbeiterDaten.getEmail(), mitarbeiterDaten.getTelefon(), mitarbeiterDaten.getGehalt());
		pVerwaltung.saveMitarbeiter(m);
		
		return "redirect:/personal";
	}
    
    @RequestMapping("/personalDetail")
    public String personalDetail(Model model, @RequestParam("mitarbeiterId") long mitarbeiterId){
    	
    	Mitarbeiter m = pVerwaltung.getMitarbeiterRepo().findById(mitarbeiterId);
    	MitarbeiterFormular mf = new MitarbeiterFormular();
    	
    	mf.setVorname(m.getVorname());
    	mf.setNachname(m.getNachname());
    	mf.setOrt(m.getAdresse().getOrt());
    	mf.setPlz(m.getAdresse().getPlz());
    	mf.setStrasse(m.getAdresse().getStrasse());
    	mf.setTelefon(m.getTelefon());
    	mf.setEmail(m.getEmail());
    	mf.setPassword("leer");
    	mf.setUsername(m.getUserAccount().getUsername());
    	for(Role r : m.getUserAccount().getRoles()){
    		String role = r.getName();
    		mf.setRole(role);
    	}
    	mf.setGehalt(m.getGehalt());   	
    	
    	model.addAttribute("accountStatus", m.getUserAccount().isEnabled());
    	model.addAttribute("mitarbeiterDaten", mf);
    	model.addAttribute("mitarbeiterId", mitarbeiterId);
    	model.addAttribute("enumAccountRolleList", pVerwaltung.getEnumAccountRolleList());
    	
		return "personalDetail";
    }
    
    @RequestMapping("/bearbeitePersonal")
    public String bearbeiteMitarbeiter(@RequestParam("mitarbeiterId") long mitarbeiterId, @ModelAttribute("mitarbeiterDaten") @Valid MitarbeiterFormular mitarbeiterDaten, BindingResult result){
		
		System.out.println(mitarbeiterDaten.toString());
		if (result.hasErrors()) {
			return "personalDetail";
		}
		
		pVerwaltung.bearbeiteMitarbeiter(mitarbeiterId, mitarbeiterDaten.getVorname(), mitarbeiterDaten.getNachname()
									, mitarbeiterDaten.getStrasse(), mitarbeiterDaten.getOrt(), mitarbeiterDaten.getPlz()
									, mitarbeiterDaten.getRole(), mitarbeiterDaten.getEmail(), mitarbeiterDaten.getTelefon(), mitarbeiterDaten.getGehalt());
		
		return "redirect:/personal";	
	}
    
    @RequestMapping("/aktiviereMitarbeiter")
    public String aktiviereMitarbeiter(@RequestParam("mitarbeiterId") long mitarbeiterId){
    	pVerwaltung.aktiviereMitarbeiter(mitarbeiterId);
    	return "redirect:/personal";
    }
    
    @RequestMapping("/deaktiviereMitarbeiter")
    public String deaktiviereMitarbeiter(@RequestParam("mitarbeiterId") long mitarbeiterId){
    	pVerwaltung.deaktiviereMitarbeiter(mitarbeiterId);
    	return "redirect:/personal";
    }
}
