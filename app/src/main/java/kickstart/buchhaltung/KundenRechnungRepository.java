package kickstart.buchhaltung;
import org.salespointframework.core.SalespointRepository;

/**
 * The interface Kunden rechnung repository.
 */
public interface KundenRechnungRepository extends SalespointRepository<KundenRechnung, Long> {
    /**
     * Find by id kunden rechnung.
     *
     * @param id the id
     * @return the kunden rechnung
     */
    public KundenRechnung findById(long id);
}