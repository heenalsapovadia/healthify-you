package persistence.patient.model;

import java.util.Date;
import java.util.List;

public class BloodBankService {
    private String patientId;
    private String donationId;
    private String bloodGrp;
    private Date date;
    private int bloodDonationPoints;
    private static List<BloodTestReport> bloodtests;


    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
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

    public int getBloodDonationPoints() {
        return bloodDonationPoints;
    }

    public void setBloodDonationPoints(int bloodDonationPoints) {
        this.bloodDonationPoints = bloodDonationPoints;
    }

    // Added BloodTestReport methods
    public List<BloodTestReport> getBloodTests() {
        return bloodtests;
    }
    public void setBloodTests(List<BloodTestReport> tests) {
        bloodtests = tests;
    }
}
