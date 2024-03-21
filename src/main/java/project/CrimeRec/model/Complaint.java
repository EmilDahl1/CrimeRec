package project.CrimeRec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long complaintid;

    private String complaintCreator;

    private String complaintReceiver;

    private String complaintText;

    public Complaint() {
    }

    public long getComplaintID() {
        return complaintid;
    }

    public void setComplaintID(long complaintid) {
        this.complaintid = complaintid;
    }

    public String getComplaintCreator() {
        return complaintCreator;
    }

    public void setComplaintCreator(String complaintCreator) {
        this.complaintCreator = complaintCreator;
    }

    public String getComplaintReceiver() {
        return complaintReceiver;
    }

    public void setComplaintReceiver(String complaintReceiver) {
        this.complaintReceiver = complaintReceiver;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }
}
