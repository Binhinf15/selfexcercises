package kickstart.ware;

import org.salespointframework.catalog.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kickstart.person.Kunde;

/**
 * The type Lager verwaltung.
 */
@Component
public class LagerVerwaltung {
    private final WarenRepository wRepo;

    /**
     * Instantiates a new Lager verwaltung.
     *
     * @param wRepo the w repo
     */
// Konstruktor
    @Autowired
    public LagerVerwaltung(WarenRepository wRepo){
        this.wRepo = wRepo;
    }

    /**
     * Create ware ware.
     *
     * @param name  the name
     * @param menge the menge
     * @param preis the preis
     * @param beschreibung the beschreibung
     * @return the ware
     */
// Methoden
    public Ware createWare(String name, long menge, double preis, String beschreibung){
    	Ware ware = new Ware(name, menge, preis, beschreibung); 	
        return ware;
    }

    /**
     * Save ware.
     *
     * @param ware the ware
     */
    public void saveWare(Ware ware){
    	wRepo.save(ware);
    }

    /**
     * Get waren repo waren repository.
     *
     * @return the waren repository
     */
    public WarenRepository getWarenRepo(){
        return wRepo;
    }
    
    public Ware bearbeiteWare(long id, String name, long menge, double preis, String beschreibung){
		Ware w = wRepo.findOne(id).get();
		w.setName(name);
		w.setMenge(menge);
		w.setPreis(preis);
		w.setBeschreibung(beschreibung);
		return w;
	}

}
