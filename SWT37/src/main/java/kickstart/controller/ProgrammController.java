package kickstart.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kickstart.person.Mitarbeiter;
import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.KalenderDaten;
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsVerwaltung;

/**
 * The type Programm controller.
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_BOSS') or hasRole('ROLE_VERWALTUNG') or hasRole('ROLE_KOCH') or hasRole('ROLE_SERVICE')")
public class ProgrammController {
	
	private final PersonenVerwaltung pVerwaltung;
	private final VeranstaltungsVerwaltung vVerwaltung;
	
	public ProgrammController( PersonenVerwaltung pVerwaltung, VeranstaltungsVerwaltung vVerwaltung){
		this.pVerwaltung = pVerwaltung;
		this.vVerwaltung = vVerwaltung;
	}

    /**
     * Main string.
     *
     * @return the string
     */
    @RequestMapping("/main")
    public String main(Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        
        Mitarbeiter mitarbeiter = pVerwaltung.getMitarbeiterRepo().findByUserAccount(pVerwaltung.getUserAccountManager().findByUsername(username).get());
        
        // Daten für den Kalender
        List<KalenderDaten> kalenderDatenListe = new ArrayList<KalenderDaten>();
        List<Veranstaltung> veranstaltungsListe = vVerwaltung.getVeranstaltungsRepo().findByMitarbeiterIdListe(mitarbeiter.getId());
        for(Veranstaltung veranstaltung : veranstaltungsListe){
        	KalenderDaten kalenderDaten = new KalenderDaten(veranstaltung.getId(), veranstaltung.getTitel()
        													, veranstaltung.getBeginnDatum(), veranstaltung.getSchlussDatum());
        	kalenderDatenListe.add(kalenderDaten);
        }
        
        // Prüfung, so dass keine Veranstaltungen in der Vergangenheit angezeigt werden; prüfung erfolgt in der html
        LocalDateTime currentTime = LocalDateTime.now();
        
        model.addAttribute("currentTime", currentTime);
        model.addAttribute("kalenderDatenListe", kalenderDatenListe);
        model.addAttribute("meineVeranstaltungen", vVerwaltung.getVeranstaltungsRepo().findByMitarbeiterIdListe(mitarbeiter.getId()) );
        model.addAttribute("username", username);
        return "main";
    }
    
    @RequestMapping("/meineVeranstaltungsDetail")
    public String veranstaltungsDetail(Model model, @RequestParam("veranstaltungsId") long veranstaltungsId){  	
    
    Veranstaltung vDaten = vVerwaltung.getVeranstaltungsRepo().findOne(veranstaltungsId).get();
    
	model.addAttribute("veranstaltungsDaten", vDaten);
	model.addAttribute("veranstaltungsId", veranstaltungsId);
	model.addAttribute("kunde", pVerwaltung.getKundenRepo().findOne(vDaten.getKundenId()).get());
	model.addAttribute("zugeordneteMitarbeiter", vVerwaltung.getZugewieseneMitarbeiter(vDaten.getMitarbeiterIdListe()));
	
	return "mainVeranstaltungsDetail";
    }
}
