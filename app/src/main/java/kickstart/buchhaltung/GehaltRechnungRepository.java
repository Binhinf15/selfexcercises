package kickstart.buchhaltung;
import org.salespointframework.core.SalespointRepository;

/**
 * The interface Gehalt rechnung repository.
 */
public interface GehaltRechnungRepository extends SalespointRepository<GehaltRechnung, Long> {
    /**
     * Find by id gehalt rechnung.
     *
     * @param id the id
     * @return the gehalt rechnung
     */
    public GehaltRechnung findById(long id);
}