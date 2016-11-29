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

@Entity
@Table(name="WARE")
public class Ware {
	
	@Id @GeneratedValue
	private long id;
	private String name;
	private int menge;
	
	// Konstruktor
	public Ware(){
	}
	
	public Ware(String name, int menge){
		this.name = name;
		this.menge = menge;
	}
	
	// Methoden
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public long getId() {
		return id;
	}	
}
