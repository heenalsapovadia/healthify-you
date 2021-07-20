package persistence.patient.model;

import java.util.Date;
import java.util.List;

public class BloodBankService {
    private int patientId;
    private String donationId;
    private String bloodGrp;
    private Date date;
    private static List<BloodTestReport> bloodtests;


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

    // Added BloodTestReport methods
    public List<BloodTestReport> getBloodTests() {
        return bloodtests;
    }
    public void setBloodTests(List<BloodTestReport> tests) {
        bloodtests = tests;
    }
}
