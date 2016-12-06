package kickstart.person;

import java.util.Arrays;
import java.util.List;

import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kickstart.adresse.Adresse;

/**
 * The type Personen verwaltung.
 */
@Component
public class PersonenVerwaltung {
	private final UserAccountManager uaManager;
	private final MitarbeiterRepository mRepo;
	private final KundenRepository kRepo;
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
	public PersonenVerwaltung(UserAccountManager uaManager, MitarbeiterRepository mRepo, KundenRepository kRepo){
		this.uaManager = uaManager;
		this.mRepo = mRepo;
		this.kRepo = kRepo;
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
	public Mitarbeiter createMitarbeiter(String vorname, String nachname, String username,String strasse, String ort, String plz
										, String passwort, String roles, String email, String telefon){

		UserAccount ua = uaManager.create(username, passwort, Role.of(roles));
		uaManager.save(ua);
		Adresse adresse = new Adresse(strasse, ort, plz);
		Mitarbeiter m = new Mitarbeiter(vorname, nachname, adresse, ua, email, telefon);
		
		return m;
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
		k.adresse.setStrasse(strasse);
		k.adresse.setOrt(ort);
		k.adresse.setPlz(plz);	
		return k;
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
}
