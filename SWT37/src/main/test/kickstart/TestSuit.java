package kickstart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import kickstart.buchhaltung.BuchhaltungTest;
import kickstart.person.KundeTest;
import kickstart.person.PersonenVerwaltungTest;
import kickstart.veranstaltung.VeranstaltungTest;
import kickstart.veranstaltung.VeranstaltungsVerwaltungTest;
import kickstart.ware.LagerVerwaltungTest;

@RunWith(Suite.class)
@SuiteClasses({
                BuchhaltungTest.class,
                KundeTest.class,
                PersonenVerwaltungTest.class,
                VeranstaltungsVerwaltungTest.class,
                VeranstaltungTest.class,
                LagerVerwaltungTest.class})
public class TestSuit {

}
