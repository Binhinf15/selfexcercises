package kickstart.ware;
/*
import javax.persistence.Entity;
import org.salespointframework.catalog.Product;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.quantity.Metric;

@Entity

public class Ware extends Product {

    private String beschreibung;

    protected Ware(String name, javax.money.MonetaryAmount price, Metric metric) { }

    public String getBeschreibung() {
        return beschreibung;
    }
}*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Ware.
 */
@Entity
@Table(name="WARE")
public class Ware {
	
	@Id @GeneratedValue
	private long id;
	private String name;
	private long menge;

    /**
     * Instantiates a new Ware.
     */
// Konstruktor
	public Ware(){
	}

    /**
     * Instantiates a new Ware.
     *
     * @param name  the name
     * @param menge the menge
     */
    public Ware(String name, long menge){
		this.name = name;
		this.menge = menge;
	}

    /**
     * Gets name.
     *
     * @return the name
     */
// Methoden
	public String getName() {
		return name;
	}

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets menge.
     *
     * @return the menge
     */
    public long getMenge() {
		return menge;
	}

    /**
     * Sets menge.
     *
     * @param warenMenge the waren menge
     */
    public void setMenge(long warenMenge) {
		this.menge = warenMenge;
	}

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
		return id;
	}	
}
