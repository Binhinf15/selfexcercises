package kickstart.buchhaltung;
import org.salespointframework.core.SalespointRepository;

public interface KundenRechnungRepository extends SalespointRepository<KundenRechnung, Long> {
    public KundenRechnung findById(long id);
}