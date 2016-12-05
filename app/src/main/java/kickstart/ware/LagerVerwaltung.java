package kickstart.ware;

import org.salespointframework.catalog.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
     * @return the ware
     */
// Methoden
    public Ware createWare(String name, int menge){
    	Ware ware = new Ware(name, menge); 	
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

}
