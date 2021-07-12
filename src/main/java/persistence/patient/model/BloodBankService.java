package persistence.patient.model;

import java.util.Date;

public class BloodBankService {

    private String patientId;
    private String DonationId;
    private String BloodGrp;
    private Date date;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDonationId() {
        return DonationId;
    }

    public void setDonationId(String donationId) {
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
