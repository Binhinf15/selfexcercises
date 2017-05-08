package kickstart.person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.salespointframework.time.Interval;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kickstart.adresse.Adresse;
import kickstart.veranstaltung.Veranstaltung;
import kickstart.veranstaltung.VeranstaltungsRepository;

/**
 * The type Personen verwaltung.
 */
@Component
public class PersonenVerwaltung {
	private final UserAccountManager uaManager;
	private final MitarbeiterRepository mRepo;
	private final KundenRepository kRepo;
	private final VeranstaltungsRepository vRepo;
	private final List<AccountRolle> enumAccountRolleList;

    /**
     * Instantiates a new Personen verwaltung.
     *
     * @param uaManager the ua manager
     * @param mRepo     the m repo
     * @param kRepo     the k repo
     */
// Konstruktor
	@Autowired
	public PersonenVerwaltung(UserAccountManager uaManager, MitarbeiterRepository mRepo, KundenRepository kRepo
							, VeranstaltungsRepository vRepo){
		this.uaManager = uaManager;
		this.mRepo = mRepo;
		this.kRepo = kRepo;
		this.vRepo = vRepo;
		this.enumAccountRolleList = Arrays.asList(AccountRolle.values());
	}
	
    /**
     * Create mitarbeiter mitarbeiter.
     *
     * @param vorname  the vorname
     * @param nachname the nachname
     * @param username the username
     * @param strasse  the strasse
     * @param ort      the ort
     * @param plz      the plz
     * @param passwort the passwort
     * @param roles    the roles
     * @param email    the email
     * @return the mitarbeiter
     */
// Methoden
	public Mitarbeiter createMitarbeiter(String vorname, String nachname, String strasse, String ort, String plz
										, String username, String passwort, String role, String email, String telefon, double gehalt){
		UserAccount ua = uaManager.create(username, passwort, Role.of(role));
		uaManager.save(ua);
		Adresse adresse = new Adresse(strasse, ort, plz);
		Mitarbeiter m = new Mitarbeiter(vorname, nachname, adresse, ua, email, telefon, gehalt);
		
		return m;
	}
	
	public void bearbeiteMitarbeiter(long id, String vorname, String nachname, String strasse, String ort, String plz
									, String role, String email, String telefon, double gehalt){
		Mitarbeiter m = mRepo.findById(id);
		m.setVorname(vorname);
		m.setNachname(nachname);
		m.setTelefon(telefon);
		m.setEmail(email);
		m.getAdresse().setOrt(ort);
		m.getAdresse().setPlz(plz);
		m.getAdresse().setStrasse(strasse);
		m.setGehalt(gehalt);
		
		Iterator<Role> r = m.getUserAccount().getRoles().iterator();
		while(r.hasNext()){							//alle Rollen vom UserAccount werden entfernt
			Role rolle = r.next();
			r.remove();
		}		
		m.getUserAccount().add(Role.of(role));		//neue Rolle wird gesetzt
		
		uaManager.save(m.getUserAccount());
	}

	private List<Long> getBeschaeftigteMitarbeiterIdListe(LocalDateTime beginn,LocalDateTime schluss){		
		
		List<Long> beschaeftigteMitarbeiterIdListe = new ArrayList<Long>();	
		Interval interval1 = Interval.from(beginn).to(schluss);	
		
		for(Veranstaltung v : vRepo.findAll()){					
			Interval interval2 = Interval.from(v.getBeginnDatum()).to(v.getSchlussDatum());			
			if(interval1.overlaps(interval2) == true){
				List<Long> x = v.getMitarbeiterIdListe();
				beschaeftigteMitarbeiterIdListe.removeAll(x);
				beschaeftigteMitarbeiterIdListe.addAll(x);
			}
		}
		Collections.sort(beschaeftigteMitarbeiterIdListe);
		return beschaeftigteMitarbeiterIdListe;		
	}
	
	public List<Mitarbeiter> getFreieMitarbeiter(LocalDateTime beginn, LocalDateTime schluss){
		List<Long> beschaeftigteMitIdListe = getBeschaeftigteMitarbeiterIdListe(beginn, schluss);
		List<Mitarbeiter> freieMitarbeiter;
	
		if(beschaeftigteMitIdListe.isEmpty()){
			freieMitarbeiter = findAllAktiv();
		}else{
			freieMitarbeiter = mRepo.findByIdNotIn(beschaeftigteMitIdListe);
			freieMitarbeiter.retainAll(findAllAktiv());
		}			
		return freieMitarbeiter;
	}
	
	public void aktiviereMitarbeiter(long id){
		Mitarbeiter m = mRepo.findById(id);
		uaManager.enable(m.getUserAccount().getId());	
	}
	
	public void deaktiviereMitarbeiter(long id){
		Mitarbeiter m = mRepo.findById(id);
		uaManager.disable(m.getUserAccount().getId());	
	}
	
	public List<Mitarbeiter> findAll(){
		List<Mitarbeiter> list = new ArrayList<Mitarbeiter>();
		mRepo.findAll().forEach(list::add);
		return list;
	}
	
	public List<Mitarbeiter> findAllAktiv(){
		List<Mitarbeiter> list = new ArrayList<Mitarbeiter>();
		for(UserAccount ua : uaManager.findEnabled()){
			list.add(mRepo.findByUserAccount(ua));
		}
		return list;
	}
	
	public List<Mitarbeiter> findAllInaktiv(){
		List<Mitarbeiter> list = new ArrayList<Mitarbeiter>();
		for(UserAccount ua : uaManager.findDisabled()){
			list.add(mRepo.findByUserAccount(ua));
		}
		return list;
	}
	
	public List<Mitarbeiter> findAktivMitarbeiterSortNachId(){
		List<Mitarbeiter> sortedList = mRepo.findAllByOrderById();
		List<Mitarbeiter> aktivList = findAllAktiv();
		sortedList.retainAll(aktivList);
		return sortedList;
	}
	public List<Mitarbeiter> findInaktivMitarbeiterSortNachId(){
		List<Mitarbeiter> sortedList = mRepo.findAllByOrderById();
		List<Mitarbeiter> inaktivList = findAllInaktiv();
		sortedList.retainAll(inaktivList);
		return sortedList;
	}
	
	public List<Mitarbeiter> findAktivMitarbeiterSortNachVorname(){
		List<Mitarbeiter> sortedList = findAllAktiv();
		//sortiert Liste ohne case sensitive
		Collections.sort(sortedList, new Comparator<Mitarbeiter>() {
		    public int compare(Mitarbeiter one, Mitarbeiter other) {
		    	String oneVorname = one.getVorname().toUpperCase();
		    	String otherVorname = other.getVorname().toUpperCase();
		        return oneVorname.compareTo(otherVorname);
		    }
		});
		
		return sortedList;
	}
	public List<Mitarbeiter> findInaktivMitarbeiterSortNachVorname(){
		List<Mitarbeiter> sortedList = findAllInaktiv();
		//sortiert Liste ohne case sensitive
		Collections.sort(sortedList, new Comparator<Mitarbeiter>() {
		    public int compare(Mitarbeiter one, Mitarbeiter other) {
		    	String oneVorname = one.getVorname().toUpperCase();
		    	String otherVorname = other.getVorname().toUpperCase();
		        return oneVorname.compareTo(otherVorname);
		    }
		});

		return sortedList;
	}
	
	public List<Mitarbeiter> findAktivMitarbeiterSortNachName(){
		List<Mitarbeiter> sortedList = findAllAktiv();
		//sortiert Liste ohne case sensitive
		Collections.sort(sortedList, new Comparator<Mitarbeiter>() {
		    public int compare(Mitarbeiter one, Mitarbeiter other) {
		    	String oneNachname = one.getNachname().toUpperCase();
		    	String otherNachname = other.getNachname().toUpperCase();
		        return oneNachname.compareTo(otherNachname);
		    }
		});

		return sortedList;
	}
	public List<Mitarbeiter> findInaktivMitarbeiterSortNachName(){
		List<Mitarbeiter> sortedList = findAllInaktiv();
		//sortiert Liste ohne case sensitive
		Collections.sort(sortedList, new Comparator<Mitarbeiter>() {
		    public int compare(Mitarbeiter one, Mitarbeiter other) {
		    	String oneNachname = one.getNachname().toUpperCase();
		    	String otherNachname = other.getNachname().toUpperCase();
		        return oneNachname.compareTo(otherNachname);
		    }
		});

		return sortedList;
	}
	
	
	public List<Mitarbeiter> findAktivMitarbeiterSortNachRolle(){
		List<Mitarbeiter> sortedList = findAllAktiv();
			
		Collections.sort(sortedList, new Comparator<Mitarbeiter>() {
			public int compare(Mitarbeiter one, Mitarbeiter other) {
		    	String oneRolle = "";
		    	String otherRolle = "";
		    	Iterator<Role> r = one.getUserAccount().getRoles().iterator();		    	
				while(r.hasNext()){							
					Role rolle = r.next();
					oneRolle = rolle.getName().toUpperCase();
				}
				Iterator<Role> l = other.getUserAccount().getRoles().iterator();		    	
				while(l.hasNext()){							
					Role rolle = l.next();
					otherRolle = rolle.getName().toUpperCase();
				}
				
		        return oneRolle.compareTo(otherRolle);
		    }
		});

		return sortedList;
	}
	public List<Mitarbeiter> findInaktivMitarbeiterSortNachRolle(){
		List<Mitarbeiter> sortedList = findAllAktiv();
		
		Collections.sort(sortedList, new Comparator<Mitarbeiter>() {
		    public int compare(Mitarbeiter one, Mitarbeiter other) {
		    	String oneRolle = "";
		    	String otherRolle = "";
		    	Iterator<Role> r = one.getUserAccount().getRoles().iterator();		    	
				while(r.hasNext()){							
					Role rolle = r.next();
					oneRolle = rolle.getName().toUpperCase();
				}
				Iterator<Role> l = other.getUserAccount().getRoles().iterator();		    	
				while(l.hasNext()){							
					Role rolle = l.next();
					otherRolle = rolle.getName().toUpperCase();					
				}
				
		        return oneRolle.compareTo(otherRolle);
		    }
		});
		
		return sortedList;
	}
	
	public List<Mitarbeiter> sucheMitarbeiterAktiv(String gesuchterName){
		List<Mitarbeiter> gefundeneMitarbeiter = new ArrayList<Mitarbeiter>();
		for(Mitarbeiter m : findAllAktiv()){
			if(m.getVorname().toLowerCase().contains(gesuchterName.toLowerCase()) 
			   || m.getNachname().toLowerCase().contains(gesuchterName.toLowerCase()) ){
				gefundeneMitarbeiter.add(m);
			}
		}
		return gefundeneMitarbeiter;
	}
	public List<Mitarbeiter> sucheMitarbeiterInaktiv(String gesuchterName){
		List<Mitarbeiter> gefundeneMitarbeiter = new ArrayList<Mitarbeiter>();
		for(Mitarbeiter m : findAllInaktiv()){
			if(m.getVorname().toLowerCase().contains(gesuchterName.toLowerCase()) 
			   || m.getNachname().toLowerCase().contains(gesuchterName.toLowerCase()) ){
				gefundeneMitarbeiter.add(m);
			}
		}
		return gefundeneMitarbeiter;
	}
	
    /**
     * Create kunde kunde.
     *
     * @param vorname  the vorname
     * @param nachname the nachname
     * @param strasse  the strasse
     * @param ort      the ort
     * @param plz      the plz
     * @param email    the email
     * @return the kunde
     */
    public Kunde createKunde(String vorname, String nachname, String strasse, String ort, String plz, String email, String telefon){
		Adresse adresse = new Adresse(strasse, ort, plz);
		Kunde k = new Kunde(vorname, nachname, adresse, email, telefon);	
		return k;
	}
	
	public Kunde bearbeiteKunde(long id, String vorname, String nachname, String strasse, String ort, String plz, String email, String telefon){
		Kunde k = kRepo.findOne(id).get();
		k.setVorname(vorname);
		k.setNachname(nachname);
		k.setEmail(email);
		k.setTelefon(telefon);
		k.getAdresse().setStrasse(strasse);
		k.getAdresse().setOrt(ort);
		k.getAdresse().setPlz(plz);	
		return k;
	}
	
	public List<Kunde> sucheKunde(String gesuchterName){
		List<Kunde> gefundeneKunden = new ArrayList<Kunde>();
		for(Kunde k : kRepo.findAll()){
			if(k.getVorname().toLowerCase().contains(gesuchterName.toLowerCase()) 
			   || k.getNachname().toLowerCase().contains(gesuchterName.toLowerCase()) ){
				gefundeneKunden.add(k);
			}
		}
		return gefundeneKunden;
	}
	
	public List<Kunde> findKundeSortNachVorname(){
		List<Kunde> sortedList = (List<Kunde>) kRepo.findAll();
		//sortiert Liste ohne case sensitive
		Collections.sort(sortedList, new Comparator<Kunde>() {
		    public int compare(Kunde one, Kunde other) {
		    	String oneVorname = one.getVorname().toUpperCase();
		    	String otherVorname = other.getVorname().toUpperCase();
		        return oneVorname.compareTo(otherVorname);
		    }
		});
		return sortedList;
	}
	
	public List<Kunde> findKundeSortNachName(){
		List<Kunde> sortedList = (List<Kunde>) kRepo.findAll();
		//sortiert Liste ohne case sensitive
		Collections.sort(sortedList, new Comparator<Kunde>() {
		    public int compare(Kunde one, Kunde other) {
		    	String oneNachname = one.getNachname().toUpperCase();
		    	String otherNachname = other.getNachname().toUpperCase();
		        return oneNachname.compareTo(otherNachname);
		    }
		});
		return sortedList;
	}
	
    /**
     * Save mitarbeiter.
     *
     * @param mitarbeiter the mitarbeiter
     */
    public void saveMitarbeiter(Mitarbeiter mitarbeiter){
		mRepo.save(mitarbeiter);
	}

    /**
     * Save kunde.
     *
     * @param kunde the kunde
     */
    public void saveKunde(Kunde kunde){
		kRepo.save(kunde);
	}

    /**
     * Get mitarbeiter repo mitarbeiter repository.
     *
     * @return the mitarbeiter repository
     */
    public MitarbeiterRepository getMitarbeiterRepo(){
		return mRepo;
	}

	/**
     * Gets kunden repo.
     *
     * @return the kunden repo
     */
    public KundenRepository getKundenRepo() {
		return kRepo;
	}

	public List<AccountRolle> getEnumAccountRolleList() {
		return enumAccountRolleList;
	}
	
	public UserAccountManager getUserAccountManager(){
		return uaManager;
	}
}
