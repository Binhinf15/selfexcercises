package kickstart.veranstaltung;

import java.util.List;

import org.salespointframework.core.SalespointRepository;

/**
 * The interface Veranstaltungs repository.
 */
public interface VeranstaltungsRepository extends SalespointRepository<Veranstaltung, Long> {
    /**
     * Find by id list .
     *
     * @param Id the id
     * @return the list
     */
    public List <Veranstaltung> findById(long Id);

    /**
     * Find by kunden id list .
     *
     * @param kundenId the kunden id
     * @return the list
     */
    public List <Veranstaltung> findByKundenId(long kundenId);

    /**
     * Find all by order by beginn datum list .
     *
     * @return the list
     */
    public List <Veranstaltung> findAllByOrderByBeginnDatum();

    /**
     * Find all by order by event art list .
     *
     * @return the list
     */
    public List <Veranstaltung> findAllByOrderByEventArt();

    /**
     * Find all by order by id list .
     *
     * @return the list
     */
    public List <Veranstaltung> findAllByOrderById();

    /**
     * Find all by order by kunden id list .
     *
     * @return the list
     */
    public List <Veranstaltung> findAllByOrderByKundenId();
}
