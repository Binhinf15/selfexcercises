package kickstart.buchhaltung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The type Kunden rechnung.
 */
@Entity
public class KundenRechnung {

    private double preis;
    private @Id @GeneratedValue long id;
    private long kundenid;
    private long veranstaltungsid;
    private String text;

    /**
     * Instantiates a new Kunden rechnung.
     */
    public KundenRechnung(){

    }

    /**
     * Instantiates a new Kunden rechnung.
     *
     * @param preis            the preis
     * @param kundenid         the kundenid
     * @param veranstaltungsid the veranstaltungsid
     * @param text             the text
     */
    public KundenRechnung(double preis, long kundenid, long veranstaltungsid, String text){
        this.preis = preis;
        this.kundenid = kundenid;
        this.veranstaltungsid = veranstaltungsid;
        this.text = text;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Get preis double.
     *
     * @return the double
     */
    public double getPreis(){

        return preis;
    }

    /**
     * Sets preis.
     *
     * @param preis the preis
     * @return the preis
     */
    public double setPreis(double preis) {
        this.preis = preis;
        return preis;
    }

    /**
     * Get kundenid long.
     *
     * @return the long
     */
    public long getKundenid(){
        return kundenid; }

    /**
     * Set kundenid long.
     *
     * @param kundenid the kundenid
     * @return the long
     */
    public long setKundenid(long kundenid){
        this.kundenid = kundenid;
        return kundenid;
    }

    /**
     * Get veranstaltungid long.
     *
     * @return the long
     */
    public long getVeranstaltungid(){
        return veranstaltungsid; }

    /**
     * Sets veranstaltungsid.
     *
     * @param veranstalungsid the veranstalungsid
     * @return the veranstaltungsid
     */
    public long setVeranstaltungsid(long veranstalungsid) {
        this.veranstaltungsid = veranstalungsid;
        return veranstalungsid;
    }

    /**
     * Get text string.
     *
     * @return the string
     */
    public String getText(){
        return text; }

    /**
     * Set text string.
     *
     * @param text the text
     * @return the string
     */
    public String setText (String text){
        this.text = text;
        return text;
    }

}
