package kickstart.person;

import java.util.List;

import org.salespointframework.core.SalespointRepository;

/**
 * The interface Kunden repository.
 */
public interface KundenRepository extends SalespointRepository<Kunde, Long> {
	/**
	 * Find by vorname list .
	 *
	 * @param vorname the vorname
	 * @return the list
	 */
	public List <Kunde> findByVorname(String vorname);

	/**
	 * Find all by order by id list .
	 *
	 * @return the list
	 */
	public List <Kunde> findAllByOrderById();
}
