package kickstart.person;

import java.util.List;

import org.salespointframework.core.SalespointRepository;

public interface KundenRepository extends SalespointRepository<Kunde, Long> {
	public List <Kunde> findByVorname(String vorname);
	public List <Kunde> findAllByOrderByVorname();
	public List <Kunde> findAllByOrderById();
}
