package kickstart.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kickstart.buchhaltung.BuchhaltungsVerwaltung;
import kickstart.buchhaltung.GehaltRechnung;
import kickstart.buchhaltung.KundenRechnung;
import kickstart.person.Kunde;
import kickstart.veranstaltung.Veranstaltung;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_BOSS') or hasRole('ROLE_VERWALTUNG')")
public class BuchhaltungController {

    @Autowired
    private final BuchhaltungsVerwaltung bVerwaltung;

        public BuchhaltungController(BuchhaltungsVerwaltung bVerwaltung){
        this.bVerwaltung = bVerwaltung; }


    @RequestMapping("/buchhaltung")
    public String buchhaltung (Model model){
        return "buchhaltung";
    }

    @RequestMapping("/gehaltRechnung")
    public String gehaltRechnungAnzeigen(Model model) {
        model.addAttribute("gehaltRechnungsList", bVerwaltung.getGehaltRepo().findAll());
        return "gehaltrechnung";
    }

    @RequestMapping("/kundenRechnung")
    public String kundenRechnungAnzeigen(Model model) {
        model.addAttribute("kundenRechnungsList", bVerwaltung.getKundenRechnungRepo().findAll());
        return "kundenrechnung";
    }
    
    @RequestMapping("/offeneGehaltRechnung")
    public String offeneGehaltRechnungAnzeigen(Model model) {
        model.addAttribute("gehaltRechnungsList", bVerwaltung.getOffeneGehaltRechnung());
        return "gehaltrechnung";
    }
    
    @RequestMapping("/offeneKundenRechnung")
    public String offeneKundenRechnungAnzeigen(Model model) {
        model.addAttribute("kundenRechnungsList", bVerwaltung.getOffeneKundenRechnung());
        return "kundenrechnung";
    }
    
    @RequestMapping("/gehaltRechnungFuerMitarbeiter")
    public String gehaltRechnungFuerMitarbeiter(Model model,@RequestParam(value="mitarbeiterId", required=false, defaultValue="0") long mitarbeiterId) {
        model.addAttribute("gehaltRechnungsList", bVerwaltung.getGehaltRepo().findByMitarbeiterId(mitarbeiterId));
        System.out.println(bVerwaltung.getGehaltRepo().findByMitarbeiterId(mitarbeiterId));
        return "gehaltrechnung";
    }

    @RequestMapping("/kundenRechnungFuerVeranstaltung")
    public String kundenRechnungFuerVeranstaltung(Model model,@RequestParam(value="veranstaltungsId", required=false, defaultValue="0") long veranstaltungsId) {
        model.addAttribute("kundenRechnungsList", bVerwaltung.getKundenRechnungRepo().findByVeranstaltungsId(veranstaltungsId));
        return "kundenrechnung";
    }
    
    @RequestMapping("/löscheGehaltRechnung")
    public String loescheGehaltRechnung(Model model,@RequestParam("gehaltRechnungId") long gehaltRechnungId) {
        bVerwaltung.deleteGehaltRechnung(gehaltRechnungId);
        return "redirect:/gehaltRechnung";
    }
    
    @RequestMapping("/löscheKundenRechnung")
    public String loescheKundenRechnung(Model model,@RequestParam("kundenRechnungId") long kundenRechnungId) {
        bVerwaltung.deleteKundenRechnung(kundenRechnungId);
        return "redirect:/kundenRechnung";
    }
    
    @RequestMapping("/zeigeBilanz")
    public String zeigeBilanz (Model model, @RequestParam("start")String start
                                ,  @RequestParam("ende")String ende){
    	
    	LocalDate beginn = LocalDate.parse(start);
    	LocalDate schluss = LocalDate.parse(ende);
    	
    	model.addAttribute("start", start);
    	model.addAttribute("ende", ende);
    	model.addAttribute("beginn", beginn.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) );
    	model.addAttribute("schluss", schluss.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        model.addAttribute("bilanzErgebnis", bVerwaltung.abrechnungssummeFuerIntervall(beginn, schluss));
        model.addAttribute("gehaltRechnungsListe", bVerwaltung.getGehaltRechnungListFuerIntervall(beginn, schluss));
        model.addAttribute("kundenRechnungsListe", bVerwaltung.getKundenRechnungListFuerIntervall(beginn, schluss));
        
        return "bilanz";
    }
    
    @RequestMapping("/bezahltGehalt")
    public String setBezahltGehalt(@RequestParam("gehaltRechnungId") long gehaltRechnungId){
    	GehaltRechnung gr = bVerwaltung.getGehaltRepo().findById(gehaltRechnungId);
    	gr.setBezahltSwap();
    	bVerwaltung.saveGehaltRechnung(gr);
	
    	return "redirect:/gehaltRechnung";
    }
    
    @RequestMapping("/bezahltKunde")
    public String setBezahltKunde(@RequestParam("kundenRechnungId") long kundenRechnungId){
    	KundenRechnung kr = bVerwaltung.getKundenRechnungRepo().findById(kundenRechnungId);
    	kr.setBezahltSwap();
    	bVerwaltung.saveKundenRechnung(kr);

    	return "redirect:/kundenRechnung";
    }
    
    @RequestMapping("/erstelleGehaltRechnungFuerAlle")
    public String createGehaltRechnung(@RequestParam("start")String start){
    	LocalDate datum = LocalDate.parse(start);
    	bVerwaltung.createGehaltRechnungForAll(datum);
    	return "redirect:/gehaltRechnung";
    }
    
    @RequestMapping("/sortiereGehaltRechnungDatum")
    public String sortiereGehaltRechnungDatum(Model model){
    	model.addAttribute("gehaltRechnungsList", bVerwaltung.getGehaltRepo().findAllByOrderByDatum());
    	return "gehaltRechnung";
    }
    
    @RequestMapping("/sortiereKundenRechnungDatum")
    public String sortiereKundenRechnungDatum(Model model){
    	model.addAttribute("kundenRechnungsList", bVerwaltung.getKundenRechnungRepo().findAllByOrderByDatum());
    	return "kundenRechnung";
    }
    
    @RequestMapping("/kundenRechnungDetail")
    public String zeigeKundenRechnungDetail(Model model,@RequestParam("kundenRechnungId")long kundenRechnungId){
    	KundenRechnung kundenRechnung = bVerwaltung.getKundenRechnungRepo().findById(kundenRechnungId);
    	Kunde kunde = bVerwaltung.getkRepo().findOne(kundenRechnung.getKundenId()).get();
    	Veranstaltung veranstaltung = bVerwaltung.getvRepo().findOne(kundenRechnung.getVeranstaltungsId()).get();
    	veranstaltung.aktualisiereGesammtPreis();
    	bVerwaltung.getvRepo().save(veranstaltung);
    	
    	model.addAttribute("kundenRechnung", kundenRechnung);
    	model.addAttribute("kunde", kunde);
    	model.addAttribute("veranstaltung", veranstaltung);
    	
		return "kundenRechnungDetail";
    }

}
