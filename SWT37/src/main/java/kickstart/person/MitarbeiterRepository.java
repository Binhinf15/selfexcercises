package kickstart.person;

import java.util.List;

import org.salespointframework.core.SalespointRepository;
import org.salespointframework.useraccount.UserAccount;

/**
 * The interface Mitarbeiter repository.
 */
public interface MitarbeiterRepository extends SalespointRepository<Mitarbeiter, Long> {
       
    /**
     * Find all by order by id list .
     *
     * @return the list
     */
    public List<Mitarbeiter> findAllByOrderById();

    /**
     * Find by vorname list .
     *
     * @param vorname the vorname
     * @return the list
     */
    public List<Mitarbeiter> findByVorname(String vorname);
    
    public List<Mitarbeiter> findByIdNotIn(List<Long> Id);   
    
    public Mitarbeiter findByUserAccount(UserAccount userAccount);

	public Mitarbeiter findById(long id);
	
}
