package kickstart.veranstaltung;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.javamoney.moneta.FastMoney;
import org.junit.Test;
import org.salespointframework.quantity.Metric;
import org.salespointframework.quantity.Quantity;

import kickstart.AbstractIntegrationTests;
import kickstart.ware.Ware;

public class VeranstaltungTest extends AbstractIntegrationTests{
	
	// default Konstruktor
	public VeranstaltungTest(){
	}
	
	@Test
	public void berechnePreisWarenmenge(){
		Veranstaltung v = new Veranstaltung();
		
		Ware ware = new Ware("ware", FastMoney.of(100, "EUR"), Metric.valueOf("UNIT"));
		Quantity quantity = Quantity.of(10);
		
		assertEquals(1000, v.berechnePreisWarenmenge(ware, quantity), 0.001);
	}
	
	@Test
	public void aktualisiereGesammtPreis(){
		Veranstaltung v = new Veranstaltung();
		
		Ware key = new Ware("ware", FastMoney.of(100, "EUR"), Metric.valueOf("UNIT"));
		Quantity value = Quantity.of(10);
		HashMap<Ware, Quantity> warenliste = new HashMap<Ware,Quantity>(0);
		warenliste.put(key, value);
		v.setWarenliste(warenliste);
		
		assertEquals(0, v.getPreis(), 0.001);
		v.aktualisiereGesammtPreis();
		assertEquals(1000, v.getPreis(), 0.001);
	}
}
