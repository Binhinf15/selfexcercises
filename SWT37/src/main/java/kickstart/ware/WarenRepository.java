package kickstart.ware;

import java.util.List;

import org.salespointframework.catalog.Catalog;

public interface WarenRepository extends Catalog<Ware>{
	
	public List<Ware> findAllByOrderByName();	
	
}