package kickstart.ware;

import java.util.List;

import org.salespointframework.core.SalespointRepository;

/**
 * The interface Waren repository.
 */
public interface WarenRepository extends SalespointRepository<Ware, Long> {
    /**
     * Find by id list .
     *
     * @param Id the id
     * @return the list
     */
    public List <Ware> findById(long Id);
}
