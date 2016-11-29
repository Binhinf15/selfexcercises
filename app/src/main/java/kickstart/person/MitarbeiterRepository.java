package kickstart.person;

import org.salespointframework.core.SalespointRepository;

public interface MitarbeiterRepository extends SalespointRepository<Mitarbeiter, Long> {
	public Mitarbeiter findByVorname(String vorname);
}
