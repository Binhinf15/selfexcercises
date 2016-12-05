package kickstart.person;

import java.util.List;

import org.salespointframework.core.SalespointRepository;

/**
 * The interface Mitarbeiter repository.
 */
public interface MitarbeiterRepository extends SalespointRepository<Mitarbeiter, Long> {
    /**
     * Find by vorname list .
     *
     * @param vorname the vorname
     * @return the list
     */
    public List <Mitarbeiter> findByVorname(String vorname);

    /**
     * Find all by order by vorname list .
     *
     * @return the list
     */
    public List <Mitarbeiter> findAllByOrderByVorname();

    /**
     * Find all by order by nachname list .
     *
     * @return the list
     */
    public List <Mitarbeiter> findAllByOrderByNachname();

    /**
     * Find all by order by id list .
     *
     * @return the list
     */
    public List <Mitarbeiter> findAllByOrderById();
	
}
