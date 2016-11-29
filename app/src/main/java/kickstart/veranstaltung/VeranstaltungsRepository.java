package kickstart.veranstaltung;

import java.util.List;

import org.salespointframework.core.SalespointRepository;

public interface VeranstaltungsRepository extends SalespointRepository<Veranstaltung, Long> {
	public List <Veranstaltung> findByKundenId(long kundenId);
	public List <Veranstaltung> findByOrderByBeginnDatum();
	public List <Veranstaltung> findByOrderByEventArt();
	public List <Veranstaltung> findByOrderById();
	public List <Veranstaltung> findByOrderByKundenId();
}
