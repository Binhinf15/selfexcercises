package kickstart.ware;

import javax.money.MonetaryAmount;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.salespointframework.catalog.Product;
import org.salespointframework.quantity.Metric;

@Entity
@Table(name="WARE")
public class Ware extends Product {
	
	private static final long serialVersionUID = -6183592825226273674L;	
	private String beschreibung;
	
	// Konstruktoren
	@SuppressWarnings("deprecation")
	public Ware(){
		//default Konstruktor wird für Hibernate benötigt
	}
	
	public Ware(String name, MonetaryAmount preis, Metric metric) {
		super(name, preis, metric);
		
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	@Override
	public String toString() {
		return "Ware [beschreibung=" + beschreibung + ", getId()=" + getId() + ", getCategories()=" + getCategories()
				+ ", getName()=" + getName() + ", getPrice()=" + getPrice() + "]";
	}

	
	
}
