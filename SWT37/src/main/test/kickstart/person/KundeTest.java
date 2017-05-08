package kickstart.person;

import static org.junit.Assert.*;

import org.junit.Test;

import kickstart.adresse.Adresse;

public class KundeTest {

	@Test
	public void test() {
		Adresse a =new Adresse("a","b","c");
		Kunde k = new Kunde("d","e",a,"f","g");
		
		assertEquals(k,k);
	}

}
