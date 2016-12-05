package kickstart.buchhaltung;

import kickstart.person.KundenRepository;
import kickstart.person.MitarbeiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Buchhaltungs verwaltung.
 */
@Component
public class BuchhaltungsVerwaltung {

    private KundenRepository krepo;
    private MitarbeiterRepository mrepo;
    private KundenRechnungRepository crepo;
    private GehaltRechnungRepository grepo;

    /**
     * Instantiates a new Buchhaltungs verwaltung.
     *
     * @param krepo the krepo
     * @param mrepo the mrepo
     * @param crepo the crepo
     * @param grepo the grepo
     */
    @Autowired
    public BuchhaltungsVerwaltung (KundenRepository krepo, MitarbeiterRepository mrepo, KundenRechnungRepository crepo, GehaltRechnungRepository grepo){
        this.krepo = krepo;
        this.mrepo = mrepo;
        this.crepo = crepo;
        this.grepo = grepo;
    }

    /**
     * Gets KundenRepository krepo.
     *
     * @return the KundenRepository krepo
     */
    public KundenRepository getKrepo() {
        return krepo;
    }

    /**
     * Sets KundenRepository krepo.
     *
     * @param krepo the KundenRepository krepo
     */
    public void setKrepo(KundenRepository krepo) {
        this.krepo = krepo;
    }

    /**
     * Gets mrepo.
     *
     * @return the mrepo
     */
    public MitarbeiterRepository getMrepo() {
        return mrepo;
    }

    /**
     * Sets mrepo.
     *
     * @param mrepo the mrepo
     */
    public void setMrepo(MitarbeiterRepository mrepo) {
        this.mrepo = mrepo;
    }

    /**
     * Gets crepo.
     *
     * @return the crepo
     */
    public KundenRechnungRepository getCrepo() {
        return crepo;
    }

    /**
     * Sets crepo.
     *
     * @param crepo the KundenRechnungRepository crepo
     */
    public void setCrepo (KundenRechnungRepository crepo) {
        this.crepo = crepo;
    }

    /**
     * Gets grepo.
     *
     * @return the grepo
     */
    public GehaltRechnungRepository getGrepo() {
        return grepo;
    }

    /**
     * Sets grepo.
     *
     * @param grepo the grepo
     */
    public void setGrepo(GehaltRechnungRepository grepo) {
        this.grepo = grepo;
    }

    /**
     * Create kunden rechnung kunden rechnung.
     *
     * @param preis          the preis
     * @param kundenid       the kundenid
     * @param veranstalungid the veranstalungid
     * @param text           the text
     * @return the kunden rechnung
     */
    public KundenRechnung createKundenRechnung (double preis, long kundenid, long veranstalungid, String text) {
        KundenRechnung k = new KundenRechnung(preis, kundenid, veranstalungid, text);
        return k;

    }

    /**
     * Save kunden rechnung.
     *
     * @param kundenRechnung the kunden rechnung
     */
    public void saveKundenRechnung (KundenRechnung kundenRechnung){
        crepo.save(kundenRechnung);
    }

    /**
     * Create gehalt rechnung gehalt rechnung.
     *
     * @param mitarbeitername the mitarbeitername
     * @param mitarbeiterid   the mitarbeiterid
     * @param gehalt          the gehalt
     * @param text            the text
     * @return the gehalt rechnung
     */
    public GehaltRechnung createGehaltRechnung (String mitarbeitername, long mitarbeiterid, double gehalt, String text){
        GehaltRechnung g = new GehaltRechnung(mitarbeitername, mitarbeiterid, gehalt, text);
        return g;
    }

    /**
     * Save gehalt rechnung.
     *
     * @param gehaltRechnung the gehalt rechnung
     */
    public void saveGehaltRechnung (GehaltRechnung gehaltRechnung){
        grepo.save(gehaltRechnung);
    }
}