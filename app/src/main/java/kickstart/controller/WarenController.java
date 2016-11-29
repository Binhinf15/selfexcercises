/*package kickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.ware.LagerVerwaltung;

@Controller
public class WarenController {

    private final LagerVerwaltung lVerwaltung;

    @Autowired
    public WarenController(LagerVerwaltung lVerwaltung){
        this.lVerwaltung = lVerwaltung;
    }

    @RequestMapping("/warenListe")
    public String ware(Model model) {

        model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAll());

        return "ware/warenListe";
    }
}*/
