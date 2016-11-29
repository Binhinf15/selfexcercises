package kickstart.buchhaltung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GehaltRechnung {

    @Id
    @GeneratedValue
    private long id;
    private double gehalt;
    private long mitarbeiter;

    public GehaltRechnung (long mitarbeiter, long id){
        this.mitarbeiter=mitarbeiter;
        this.id=id;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getGehalt() {
        return gehalt;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    public long getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(long mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

}
