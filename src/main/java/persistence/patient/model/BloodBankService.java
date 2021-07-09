package persistence.patient.model;

import java.util.Date;

public class BloodBankService {

    private int patientId;
    private int DonationId;
    private String BloodGrp;
    private Date date;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDonationId() {
        return DonationId;
    }

    public void setDonationId(int donationId) {
        DonationId = donationId;
    }

    public String getBloodGrp() {
        return BloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        BloodGrp = bloodGrp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
