package kickstart.buchhaltung;

import kickstart.person.KundenRepository;
import kickstart.person.MitarbeiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuchhaltungsVerwaltung {

    private KundenRepository krepo;
    private MitarbeiterRepository mrepo;
    private KundenRechnungRepository crepo;

    @Autowired
    public BuchhaltungsVerwaltung (KundenRepository krepo, MitarbeiterRepository mrepo, KundenRechnungRepository crepo){
        this.krepo = krepo;
        this.mrepo = mrepo;
        this.crepo = crepo;
    }

    public KundenRepository getKrepo() {
        return krepo;
    }

    public void setKrepo(KundenRepository krepo) {
        this.krepo = krepo;
    }

    public MitarbeiterRepository getMrepo() {
        return mrepo;
    }

    public void setMrepo(MitarbeiterRepository mrepo) {
        this.mrepo = mrepo;
    }

    public KundenRechnungRepository getCrepo() {
        return crepo;
    }

    public KundenRechnung createKundenRechnung (double preis, long kundenid, long veranstalungid, String text) {
        KundenRechnung k = new KundenRechnung(preis, kundenid, veranstalungid, text);
        return k;

    }

    public void saveKundenRechnung (KundenRechnung kundenRechnung){
        crepo.save(kundenRechnung);
    }

}