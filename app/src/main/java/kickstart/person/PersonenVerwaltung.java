package kickstart.person;

import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kickstart.adresse.Adresse;

@Component
public class PersonenVerwaltung {
	private final UserAccountManager uaManager;
	private final MitarbeiterRepository mRepo;
	private final KundenRepository kRepo;
	
	// Konstruktor
	@Autowired
	public PersonenVerwaltung(UserAccountManager uaManager, MitarbeiterRepository mRepo, KundenRepository kRepo){
		this.uaManager = uaManager;
		this.mRepo = mRepo;
		this.kRepo = kRepo;
	}
	
	// Methoden
	public Mitarbeiter createMitarbeiter(String vorname, String nachname, String username,String strasse, String ort, String plz, String passwort, String roles, String email){
		UserAccount ua = uaManager.create(username, passwort, Role.of(roles));
		uaManager.save(ua);
		Adresse adresse = new Adresse(strasse, ort, plz);
		Mitarbeiter m = new Mitarbeiter(vorname, nachname, adresse, ua, email);
		return m;
	}
	
	public Kunde createKunde(String vorname, String nachname, String strasse, String ort, String plz, String email){
		Adresse adresse = new Adresse(strasse, ort, plz);
		Kunde k = new Kunde(vorname, nachname, adresse, email);
		return k;
	}
	
	public void saveMitarbeiter(Mitarbeiter mitarbeiter){
		mRepo.save(mitarbeiter);
	}
	
	public void saveKunde(Kunde kunde){
		kRepo.save(kunde);
	}
	
	public MitarbeiterRepository getMitarbeiterRepo(){
		return mRepo;
	}

	public KundenRepository getKundenRepo() {
		return kRepo;
	}
}
