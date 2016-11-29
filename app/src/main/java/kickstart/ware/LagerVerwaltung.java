package kickstart.ware;

import org.salespointframework.catalog.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LagerVerwaltung {
    private final WarenRepository wRepo;
    
    // Konstruktor
    @Autowired
    public LagerVerwaltung(WarenRepository wRepo){
        this.wRepo = wRepo;
    }

    // Methoden
    public Ware createWare(String name, int menge){
    	Ware ware = new Ware(name, menge); 	
        return ware;
    }

    public void saveWare(Ware ware){
    	wRepo.save(ware);
    }

    public WarenRepository getWarenRepo(){
        return wRepo;
    }

}
