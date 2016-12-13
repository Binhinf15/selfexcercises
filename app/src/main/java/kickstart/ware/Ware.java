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
	private String beschreibung;
	private long menge;
	private double preis; 
	private String name;

    /**
     * Instantiates a new Ware.
     */
// Konstruktor
	public Ware(){
		//default Konstruktor wird für Hibernate benötigt
	}

    /**
     * Instantiates a new Ware.
     *
     * @param name  the name
     * @param menge the menge
     */
    public Ware(String name, long menge, double preis, String beschreibung){
		this.beschreibung = beschreibung;
		this.name = name;
		this.menge = menge;
		this.preis = preis;
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
    
	public String getBeschreibung(){
		return beschreibung;
	}
	
	/**
     * Sets beschreibung.
     *
     * @param warenBeschreibung the waren beschreibung
     */
	
	public void setBeschreibung(String warenBeschreibung) {
		this.beschreibung = warenBeschreibung;
	}

	public double getPreis(){
		return preis;
	}
	
	/**
     * Sets preis.
     *
     * @param warenPreis the waren preis
     */
	
	public void setPreis(double warenPreis) {
		this.preis = warenPreis;
	}
	
    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
		return id;
	}	
    
	@Override
	public String toString() {
		return "Ware [ name=" + name +", menge=" + menge +", preis=" + preis + ", beschreibung=" + beschreibung + "]";
	}
}