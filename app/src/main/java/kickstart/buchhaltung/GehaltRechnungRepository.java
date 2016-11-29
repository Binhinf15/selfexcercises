package kickstart.buchhaltung;
import org.salespointframework.core.SalespointRepository;

public interface GehaltRechnungRepository extends SalespointRepository<GehaltRechnung, Long> {
    public GehaltRechnung findById(long id);
}