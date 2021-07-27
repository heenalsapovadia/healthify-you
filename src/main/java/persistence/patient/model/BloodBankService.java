package persistence.patient.model;

import java.util.Date;
/**
 * <pre>
 * Model class for Blood Bank Service..
 * </pre>
 *
 * @author Saloni Raythatha
 *
 */
public class BloodBankService {
    private int patientId;
    private String donationId;
    private String bloodGrp;
    private Date date;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
