package kickstart.buchhaltung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The type Gehalt rechnung.
 */
@Entity
public class GehaltRechnung {

    private String mitarbeitername;
    private @Id @GeneratedValue long id;
    private long mitarbeiterid;
    private double gehalt;
    private String text;

    /**
     * Instantiates a new Gehalt rechnung.
     */
    public GehaltRechnung(){

    }

    /**
     * Instantiates a new Gehalt rechnung.
     *
     * @param mitarbeitername the mitarbeitername
     * @param mitarbeiterid   the mitarbeiterid
     * @param gehalt          the gehalt
     * @param text            the text
     */
    public GehaltRechnung (String mitarbeitername, long mitarbeiterid, double gehalt, String text){
        this.mitarbeitername = mitarbeitername;
        this.mitarbeiterid = mitarbeiterid;
        this.gehalt = gehalt;
        this.text = text;

    }

    /**
     * Get mitarbeitername string.
     *
     * @return the string
     */
    public String getMitarbeitername(){
        return mitarbeitername;
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
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets gehalt.
     *
     * @return the gehalt
     */
    public double getGehalt() {
        return gehalt;
    }

    /**
     * Sets gehalt.
     *
     * @param gehalt the gehalt
     */
    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    /**
     * Get text string.
     *
     * @return the string
     */
    public String getText(){
        return text; }

    /**
     * Sets text.
     *
     * @param text the text
     * @return the text
     */
    public String setText (String text) {
        this.text = text;
        return text;

    }

}
