package kickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

import org.salespointframework.quantity.Quantity;

import kickstart.ware.LagerVerwaltung;
import kickstart.ware.WarenFormular;
import kickstart.ware.Ware;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VERWALTUNG') or hasRole('ROLE_KOCH')")
public class LagerController {

    private final LagerVerwaltung lVerwaltung;

    @Autowired
    public LagerController(LagerVerwaltung lVerwaltung){
        this.lVerwaltung = lVerwaltung;
    }
    
    @RequestMapping("/lager")
	public String lager(Model model) {
    	
    	model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAll());
    	
		return "lager";
	}
    
    @RequestMapping("/warenNameSortiert")
  	public String warenListeSortiertNachName(Model model) {
  		model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAllByOrderByName());
  		return "lager";
  	}
    
    @RequestMapping("/warenMengeSortiert")
  	public String warenListeSortiertNachMenge(Model model) {
  		model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAllByOrderById());
  		return "lager";
  	}
      
      @RequestMapping("/warenIdSortiert")
  	public String warenListeSortiertNachId(Model model) {
  		model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAllByOrderById());
  		return "lager";
      }
      
      /**
       * Suche nach name string.
       *
       * @param model   the model
       * @param name the name
       * @return the string
       */
      @RequestMapping(path="/sucheWareName", method=RequestMethod.POST)
  	public String sucheNachName(Model model, @RequestParam("wareName") String name) {
  		model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findByName(name));
  		return "lager";
  	}
      
      @RequestMapping("/neue-ware")
  	public String ser_nk(Model model) {
  		model.addAttribute("warenDaten", new WarenFormular());
  		return "neue-ware";
  	}
      
      @RequestMapping(value="/addWare", method=RequestMethod.POST)
  	public String addWare(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("warenDaten") @Valid WarenFormular warenDaten, BindingResult result) {
  		
  		if (result.hasErrors()) {
  			return "neue-ware";
  		}
  		Ware w = lVerwaltung.createWare(warenDaten.getName(), warenDaten.getMenge(), 
  				warenDaten.getPreis(), warenDaten.getBeschreibung());

  		lVerwaltung.saveWare(w);
  		redirectAttributes.addAttribute("warenId", w.getId());

  		return "lager";
  }
      
      @RequestMapping("/warenDetail")
  	public String warenDetail(Model model, @RequestParam("warenId") long warenId){
  		
  		Ware ware = lVerwaltung.getWarenRepo().findOne(warenId).get();
  		WarenFormular wf = new WarenFormular();
  		
  		wf.setName(ware.getName());
  		wf.setMenge(ware.getMenge());
  		wf.setPreis(ware.getPreis());
  		wf.setBeschreibung(ware.getBeschreibung());
  		
  		model.addAttribute("warenDaten", wf);
  		model.addAttribute("warenId", warenId);
  		
  		return "warenDetail";
  	}
      
      @RequestMapping("bearbeiteWare")
  	public String bearbeiteWare(@RequestParam("warenId") long warenId, @ModelAttribute("warenDaten") @Valid WarenFormular warenDaten, BindingResult result){
  		
  		if (result.hasErrors()) {
  			return "neue-ware";
  		}
  		
  		Ware ware = lVerwaltung.bearbeiteWare(warenId, warenDaten.getName(), warenDaten.getMenge(), warenDaten.getPreis(), warenDaten.getBeschreibung());
  		lVerwaltung.saveWare(ware);
  		
  		return "redirect:/lager";	
  	}
 
/*
      @RequestMapping("/warenListe")
      public String ware(Model model) {

          model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAll());

          return "ware/warenListe";
      }*/
      
    /*
    @RequestMapping("/warenListe")
    public String ware(Model model) {

        model.addAttribute("warenListe", lVerwaltung.getWarenRepo().findAll());

        return "ware/warenListe";
    }
    */
}
