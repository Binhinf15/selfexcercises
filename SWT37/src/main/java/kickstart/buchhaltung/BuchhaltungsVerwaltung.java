package kickstart.buchhaltung;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.salespointframework.quantity.Quantity;
import org.salespointframework.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kickstart.person.KundenRepository;
import kickstart.person.Mitarbeiter;
import kickstart.person.MitarbeiterRepository;
import kickstart.person.PersonenVerwaltung;
import kickstart.veranstaltung.VeranstaltungsRepository;
import kickstart.ware.Ware;

/**
 * The type Buchhaltungs verwaltung.
 */
@Component
public class BuchhaltungsVerwaltung {

    private KundenRepository kRepo;
    private MitarbeiterRepository mRepo;
    private VeranstaltungsRepository vRepo;
    private KundenRechnungRepository kundenRechnungRepo;
    private GehaltRechnungRepository gehaltRepo;
    private PersonenVerwaltung pVerwaltung;

    @Autowired
    public BuchhaltungsVerwaltung (KundenRepository kRepo, MitarbeiterRepository mRepo, VeranstaltungsRepository vRepo
    								, GehaltRechnungRepository gehaltRepo, KundenRechnungRepository kundenRechnungRepo, PersonenVerwaltung pVerwaltung){
    	this.kRepo = kRepo;
    	this.mRepo = mRepo;
    	this.vRepo = vRepo;
    	this.kundenRechnungRepo = kundenRechnungRepo;
    	this.gehaltRepo = gehaltRepo;
        this.pVerwaltung = pVerwaltung;
    }
    
    // // //Methoden
    
    // // Gehaltsrechnung
    // erstellt eine Gehaltsrechnung in einem Monat
    public void createGehaltRechnung (long mitarbeiterId, LocalDate date ) {
        // Prüfen, ob Rechnung schon vorhanden ist
        boolean vorhanden = false;
        for (GehaltRechnung gR : gehaltRepo.findAll()) {
            LocalDate d = gR.getDatum();		// Datum der Gehaltsrechnung
            if ((d.getMonth() == date.getMonth()) && (d.getYear()) == date.getYear() && (gR.getMitarbeiterId() == mitarbeiterId)) {
                vorhanden = true;
            }
        }
        // Gehaltrechnung wird erstellt
        if (vorhanden == false) {
            double gehalt = mRepo.findById(mitarbeiterId).getGehalt();
            GehaltRechnung gehaltRechnung = new GehaltRechnung(mitarbeiterId, date, gehalt);
            saveGehaltRechnung(gehaltRechnung);
        }
    }
    
    // erstellt Rechnungen für alle aktiven Mitarbeiter in einem Monat
    public void createGehaltRechnungForAll(LocalDate date){
        for(Mitarbeiter m : pVerwaltung.findAllAktiv()){
            createGehaltRechnung(m.getId(), date);
        }
    }
    
    // gibt alle Gehaltsrechnungen aus einem gesuchten Intervall aus
    public List<GehaltRechnung> getGehaltRechnungListFuerIntervall (LocalDate start, LocalDate ende){
    	List<GehaltRechnung> gehaltRechnungList = new ArrayList<GehaltRechnung>(0);
        LocalDateTime beginn = start.atStartOfDay();
        LocalDateTime schluss = ende.atStartOfDay();
        Interval intervall = Interval.from(beginn).to(schluss);
        
        for(GehaltRechnung gehaltRechnung : gehaltRepo.findAll()){
        	LocalDateTime gehaltRechnungDatum = gehaltRechnung.getDatum().atStartOfDay();
        	if(intervall.contains(gehaltRechnungDatum)){
        		gehaltRechnungList.add(gehaltRechnung);
        	}
        }
        
        return gehaltRechnungList;
    }

    // gibt Summe der Gehaltsrechnungen aus einen bestimmten Intervall aus
    public BigDecimal summeFuerGehaltRechnungIntervall(LocalDate start, LocalDate ende){
        BigDecimal gesammtGehalt = BigDecimal.valueOf(0);
        // Gehalt einer jeden Gehaltsrechnung werden zum gesammtGehalt addiert
        for(GehaltRechnung gehaltRechnung : getGehaltRechnungListFuerIntervall(start, ende)){
            BigDecimal gehalt = BigDecimal.valueOf(gehaltRechnung.getPreis());
            gesammtGehalt = gesammtGehalt.add(gehalt);
        }

        return gesammtGehalt;
    }
    
 // gibt offene Gehalstrechnungen aus
    public List<GehaltRechnung> getOffeneGehaltRechnung(){
    	List<GehaltRechnung> offeneGehaltRechnungList = new ArrayList<GehaltRechnung>(0);
    	for(GehaltRechnung gehaltrechnung : gehaltRepo.findAll()){
    		if(gehaltrechnung.isBezahlt() == false){
    			offeneGehaltRechnungList.add(gehaltrechnung);
    		}
    	}
    	
    	return offeneGehaltRechnungList;
    }
    
    public void deleteGehaltRechnung(long gehaltRechnungId){
    	gehaltRepo.delete(gehaltRepo.findById(gehaltRechnungId));
    }
    
    // // Kundenrechnung
    // eine Kundenrechnung erstellen
    public KundenRechnung createKundenRechnungTest(){
    	// Kundenrechnung wird erstellt
    	KundenRechnung kundenRechnung = new KundenRechnung();
    	saveKundenRechnung(kundenRechnung);
    	return kundenRechnung;
    }
    
    public void createKundenRechnung(long kundenId, long veranstaltungsId,HashMap<Ware,Quantity> warenliste){
    	// Kundenrechnung wird erstellt
    	KundenRechnung kundenRechnung = new KundenRechnung(kundenId, veranstaltungsId, warenliste);
    	saveKundenRechnung(kundenRechnung);
    }
    
    // gibt alle Kundenrechnungen aus einem gesuchten Intervall aus
    public List<KundenRechnung> getKundenRechnungListFuerIntervall(LocalDate start, LocalDate ende) {
        List<KundenRechnung> kundenRechnungList = new ArrayList<KundenRechnung>(0);
        LocalDateTime beginn = start.atStartOfDay();
        LocalDateTime schluss = ende.atStartOfDay();
        Interval interval = Interval.from(beginn).to(schluss);
        
        for(KundenRechnung kundenRechnung : kundenRechnungRepo.findAll()){
        	LocalDateTime kundenRechnungDatum = kundenRechnung.getDatum().atStartOfDay();
        	if(interval.contains(kundenRechnungDatum)){
        		kundenRechnungList.add(kundenRechnung);
        	}
        }
        
        return kundenRechnungList;
    }

    // gibt Summe der Kundenrechnungen aus einen bestimmten Intervall aus
    public BigDecimal summeFuerKundenRechnungsIntervall(LocalDate start, LocalDate ende){
        BigDecimal gesammtBetrag = new BigDecimal(0);
        // Preis einer jeden Kundenrechnung werden zum gesammt Betrag addiert
        for(KundenRechnung kundenRechnung : getKundenRechnungListFuerIntervall(start, ende)){
        	BigDecimal amount = BigDecimal.valueOf(kundenRechnung.getPreis());
            gesammtBetrag = gesammtBetrag.add(amount);
        }
        
        return gesammtBetrag;
    }
    
    // gibt offene Kundenrechnungen aus
    public List<KundenRechnung> getOffeneKundenRechnung(){
    	List<KundenRechnung> offeneKundenRechnungList = new ArrayList<KundenRechnung>(0);
    	for(KundenRechnung kundenrechnung : kundenRechnungRepo.findAll()){
    		if(kundenrechnung.isBezahlt() == false){
    			offeneKundenRechnungList.add(kundenrechnung);
    		}
    	}
    	return offeneKundenRechnungList;
    }
    
    public void deleteKundenRechnung(long kundenRechnungId){
    	kundenRechnungRepo.delete(kundenRechnungRepo.findById(kundenRechnungId));
    }
    
    // // Abrechnung
    // gibt Abrechnungssumme von einnahmen und ausgaben eines Intervalls aus
    public BigDecimal abrechnungssummeFuerIntervall(LocalDate start, LocalDate ende){
        BigDecimal ergebnis = summeFuerKundenRechnungsIntervall(start, ende).subtract(summeFuerGehaltRechnungIntervall(start, ende));
        
        return ergebnis;
    }

    
    // // Getter/Setter
    public void saveGehaltRechnung (GehaltRechnung gehaltRechnung){
        gehaltRepo.save(gehaltRechnung);
    }

    public void saveKundenRechnung (KundenRechnung kundenRechnung){
        kundenRechnungRepo.save(kundenRechnung);
    }

	public KundenRepository getkRepo() {
		return kRepo;
	}

	public MitarbeiterRepository getmRepo() {
		return mRepo;
	}

	public VeranstaltungsRepository getvRepo() {
		return vRepo;
	}

	public KundenRechnungRepository getKundenRechnungRepo() {
		return kundenRechnungRepo;
	}

	public GehaltRechnungRepository getGehaltRepo() {
		return gehaltRepo;
	}

	public PersonenVerwaltung getpVerwaltung() {
		return pVerwaltung;
	}
    
}