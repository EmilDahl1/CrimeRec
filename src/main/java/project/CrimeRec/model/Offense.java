package project.CrimeRec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Offense {

    @Id
    private String name;

    private int durationDays;
    private String location;
    private String offenseDesc;

    public String getName() {
        return name;
    }

    public Offense() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOffenseDesc() {
        return offenseDesc;
    }

    public void setOffenseDesc(String offenseDesc) {
        this.offenseDesc = offenseDesc;
    }
}
