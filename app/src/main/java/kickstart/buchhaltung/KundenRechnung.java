package kickstart.buchhaltung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class KundenRechnung {

    private double preis;
    private @Id @GeneratedValue long id;
    private long kundenid;
    private long veranstaltungsid;
    private String text;

    public KundenRechnung(){

    }

    public KundenRechnung(double preis, long kundenid, long veranstaltungsid, String text){
        this.preis = preis;
        this.kundenid = kundenid;
        this.veranstaltungsid = veranstaltungsid;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public double getPreis(){

        return preis;
    }

    public double setPreis(double preis) {
        this.preis = preis;
        return preis;
    }

    public long getKundenid(){
        return kundenid; }

    public long setKundenid(long kundenid){
        this.kundenid = kundenid;
        return kundenid;
    }

    public long getVeranstaltungid(){
        return veranstaltungsid; }

    public long setVeranstaltungsid(long veranstalungsid) {
        this.veranstaltungsid = veranstalungsid;
        return veranstalungsid;
    }

    public String getText(){
        return text; }

    public String setText (String text){
        this.text = text;
        return text;
    }

}
