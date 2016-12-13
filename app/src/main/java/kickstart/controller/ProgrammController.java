package kickstart.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.person.Mitarbeiter;
import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsVerwaltung;

/**
 * The type Programm controller.
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VERWALTUNG') or hasRole('ROLE_KOCH') or hasRole('ROLE_SERVICE')")
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
        
        Mitarbeiter mitarbeiter = pVerwaltung.getMitarbeiterRepo().findByUserAccount(pVerwaltung.getUserAccountManager().findByUsername(username));
//      List<Veranstaltung> v = vVerwaltung.getVeranstaltungsRepo().findByMitarbeiterIdListe(mitarbeiter.getId());
        
        model.addAttribute("username", username);
        model.addAttribute("meineVeranstaltungen", vVerwaltung.getVeranstaltungsRepo().findByMitarbeiterIdListe(mitarbeiter.getId()));
        return "main";
    }
}
